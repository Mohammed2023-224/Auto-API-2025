package engine.api.actions;

import engine.gui.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.asserts.SoftAssert;
import java.util.Map;
import java.util.stream.Collectors;


public class GetRequestOldWay {
    private static final SoftAssert softAssert;
    static {
        softAssert=new SoftAssert();
    }
    // Get Actions
    public static Response performGetRequest(String url, Integer proxy,
                                             Map<String,String> headers, Map<String,String> queryParam
            , Map<String, String> pathParam,String... auth){
        Response res= RestAssured.given().spec(assignVariables(url,proxy,headers,queryParam,pathParam,auth
        )).when().get();
        CustomLogger.logger.info("Perform the get request");
        return res;
    }

    private static RequestSpecification assignVariables(String url, Integer proxy,
                                                        Map<String,String> headers,  Map<String,String> queryParam,
                                                        Map<String,String> pathParam,
                                                        String... auth){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        CustomLogger.logger.info("set base URL: {}",url);
        if(!(proxy ==null)){
            requestSpecBuilder.setProxy(proxy);
            CustomLogger.logger.info("set proxy: {}",url);
        }
        if (queryParam != null) {
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
                CustomLogger.logger.info("set query parameters: {} -> {}",entry.getKey(), entry.getValue());
            }
        }
        if(!(headers ==null)){
            requestSpecBuilder.addHeaders(headers);
            CustomLogger.logger.info("set headers: {} ",headers.toString());
        }
        if (auth != null && auth.length > 0) {
            requestSpecBuilder.setAuth(setAuthorization(auth));
            CustomLogger.logger.info("set authorization");
        }
        if (!(pathParam ==null)) {
            for (Map.Entry<String, String> entry : pathParam.entrySet()) {
                String params = pathParam.values().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("/"));
                url = url.endsWith("/") ? url + params : url + "/" + params;
                CustomLogger.logger.info("set path parameters: {} -> {}",entry.getKey(), entry.getValue());
            }
        }
        requestSpecBuilder.setBaseUri(url);
        return  requestSpecBuilder.build();
    }

    private static AuthenticationScheme setAuthorization(String... info){
        if(info[0].equalsIgnoreCase("basic")){
            CustomLogger.logger.info("set basic authorization with user name {} and password {}",info[1],info[2]);
            return RestAssured.basic(info[1],info[2]);
        }
        else if(info[0].equalsIgnoreCase("bearer")){
            CustomLogger.logger.info("set bearer authorization with token {}",info[1]);
            return RestAssured.oauth2(info[1]);
        }
        else if(info[0].equalsIgnoreCase("digest")){
            return RestAssured.digest(info[1],info[2]);
        }
        else if(info[0].equalsIgnoreCase("outh1")){
            return RestAssured.oauth(info[1],info[2],info[3],info[4]);
        }
        return null;
    }

}
