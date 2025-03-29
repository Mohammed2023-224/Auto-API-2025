package engine.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Headers;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.poi.ss.formula.functions.T;
import io.restassured.path.json.JsonPath;
import org.testng.asserts.SoftAssert;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class APIActions {
    static ResponseSpecification  responseSpecification=RestAssured.expect();
    private static final ObjectMapper objectMapper;
    private static final SoftAssert softAssert;
    static {
        objectMapper=new ObjectMapper();
        softAssert=new SoftAssert();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
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


    public static ResponseSpecification checkResponseStatus(int status){
        responseSpecification.statusCode(status);
        CustomLogger.logger.info("Validate that status code = {}",status);

        return responseSpecification;
    }

    public static ResponseSpecification checkResponseContent( String contentType){
        responseSpecification.contentType(contentType);
        CustomLogger.logger.info("Validate that contentType = {}",contentType);
        return responseSpecification;
    }

    public static ResponseSpecification checkResponseCookie( String cookieName, String value){
        responseSpecification.cookie(cookieName,value);
        CustomLogger.logger.info("Validate that cookie  {} == {}",cookieName,value);
        return responseSpecification;
    }

    public static ResponseSpecification checkResponseHeader( String headerName, String value){
        responseSpecification.header(headerName,value);
        CustomLogger.logger.info("Validate that header  {} == {}",headerName,value);
        return responseSpecification;
    }

    public static void logResponse(Response response){
        CustomLogger.logger.info("log all response");
        response.then().log().all();
    }

    public static void logHeaders(Response response){
        CustomLogger.logger.info("log all headers");
        response.then().log().headers();
    }

    public static void logCookies(Response response){
        CustomLogger.logger.info("log all cookies");
        response.then().log().cookies();
    }

    public static Headers getAllHeaders(Response response){
        CustomLogger.logger.info("get all headers");
        return response.getHeaders();
    }

    public static Map<String, String> getAllCookies(Response response){
        CustomLogger.logger.info("get all cookies");
        return response.getCookies();
    }

    public static Object getValueByPath(Response response, String path){
        CustomLogger.logger.info("get value in path [{}]",path);
        return getResponse(response).path(path);
    }

    public static ArrayList<T> getArrayValueByPath(Response response, String path){
        CustomLogger.logger.info("get array value in path [{}]",path);
        return getResponse(response).path(path);
    }

    public static ExtractableResponse<Response> getResponse(Response response){
        CustomLogger.logger.info("get full response");
        return response.then().extract();
    }


    public static JsonPath getFullJsonPath(Response response){
        CustomLogger.logger.info("get only json path");
        return response.body().jsonPath();
    }

    public static Object deserializeResponse(Response response, Class className){
        Object user=null ;
        try{
            user = objectMapper.readValue(response.getBody().asString(), className);
            CustomLogger.logger.info("read json from current response and deserialize it into class [{}]",className );
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }



    public static <T> void assertEquals(T expected, T actual ){
        Boolean flag = false;
            flag= expected.equals(actual);
        if(flag ){
            CustomLogger.logger.info("Value {} is equal to actual {}",expected,actual);
        }
        else {
            CustomLogger.logger.info("Value {} is not equal to actual {}",expected,actual);
        }
    }

    public static int convertObjectIntoInt(Object data){
        if(data !=null){
            CustomLogger.logger.info("Convert object into integer");
            return (Integer) data;
        }
        return 0;
    }
}
