package engine.api.actions;

import com.sun.jna.platform.win32.DdemlUtil;
import engine.api.enums.HttpMethods;
import engine.gui.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.ListenableFuture;
import org.awaitility.Awaitility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

import static engine.api.actions.APIHelpers.awaitHelper;

public class APIRequestBuilder {

    RequestSpecBuilder requestSpecBuilder =new RequestSpecBuilder();

    public Response performRequest(HttpMethods requestType){
        Response res = null;
        switch (requestType.getMethod()){
            case "get":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().get();
                CustomLogger.logger.info("Start executing get request");
                break;
            case "post":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().post();
                CustomLogger.logger.info("Start executing post request");
                break;
                case "put":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().put();
                CustomLogger.logger.info("Start executing put request");
                break;
                case "patch":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().patch();
                CustomLogger.logger.info("Start executing patch request");
                break;
                case "delete":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().delete();
                CustomLogger.logger.info("Start executing delete request");
                break;
        }
        return res;
    }
    public CompletableFuture<Response> performAsyncRequest(String requestType,int waitTime,int pollTime, int statusCode){
        CompletableFuture<Response> futureResponse = null;
        switch (requestType){
            case "get":
                futureResponse=  CompletableFuture.supplyAsync(() ->
                        RestAssured.given().spec(requestSpecBuilder.build()).when().get());
                CustomLogger.logger.info("Start executing async get request");
                    CompletableFuture.runAsync(()->awaitHelper(requestType,waitTime,pollTime,statusCode,requestSpecBuilder)
                   );
                break;
            case "post":
                futureResponse=  CompletableFuture.supplyAsync(() ->
                        RestAssured.given().spec(requestSpecBuilder.build()).when().post());
                CustomLogger.logger.info("Start executing async post request");
                CompletableFuture.runAsync(()->
                        awaitHelper(requestType,waitTime,pollTime,statusCode,requestSpecBuilder));
                break;
                case "put":
                    futureResponse=  CompletableFuture.supplyAsync(() ->
                            RestAssured.given().spec(requestSpecBuilder.build()).when().put());
                    CustomLogger.logger.info("Start executing async put request");
                    CompletableFuture.runAsync(()->
                            awaitHelper(requestType,waitTime,pollTime,statusCode,requestSpecBuilder));
                break;
                case "patch":
                    futureResponse=  CompletableFuture.supplyAsync(() ->
                            RestAssured.given().spec(requestSpecBuilder.build()).when().patch());
                    CustomLogger.logger.info("Start executing async patch request");
                    CompletableFuture.runAsync(()->
                            awaitHelper(requestType,waitTime,pollTime,statusCode,requestSpecBuilder));
                break;
                case "delete":
                    futureResponse=  CompletableFuture.supplyAsync(() ->
                            RestAssured.given().spec(requestSpecBuilder.build()).when().delete());
                    CustomLogger.logger.info("Start executing async delete request");
                    CompletableFuture.runAsync(()->
                            awaitHelper(requestType,waitTime,pollTime,statusCode,requestSpecBuilder));
                break;
        }

            return futureResponse;

    }




    public void setURL(String url){
        requestSpecBuilder.setBaseUri(url);
        CustomLogger.logger.info("Set base URl to [{}]",url);
    }

    public void setAuthorization(String... info){
        requestSpecBuilder.setAuth(APIHelpers.setAuthorizationScheme(info));
    }

    public void setHeaders(Map<String,String> headers){
        requestSpecBuilder.addHeaders(headers);
        CustomLogger.logger.info("set headers: {} ",headers.toString());
    }

    public void setRequestAsAsynchronous(Map<String,String> headers){
    }

    public void addQueryParams(Map<String,String> queryParams){
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
            CustomLogger.logger.info("set query parameters: {} -> {}",entry.getKey(), entry.getValue());
        }
    }

    public void addQueryParams(String queryName,String value){
            requestSpecBuilder.addQueryParam(queryName,value);
            CustomLogger.logger.info("add query parameters: {} -> {}",queryName,value);
        }

    public void setPathParams(String path){
                requestSpecBuilder.setBasePath(path);
        CustomLogger.logger.info("Set base path parameter to to [{}]",path);
    }

    public void setProxy(int proxy){
        requestSpecBuilder.setProxy(proxy);
        CustomLogger.logger.info("Set proxy to [{}]",proxy);
    }

    public void setCookies(LinkedHashMap<String,String> cookies){
        requestSpecBuilder.addCookies(cookies);
        CustomLogger.logger.info("Set cookies to [{}]",cookies.entrySet());
    }

    public void setCookies(String cookies){
        requestSpecBuilder.addCookie(cookies);
        CustomLogger.logger.info("Add cookie to [{}]",cookies);
    }

    public void addHeader(String headerName,String headerValue){
        requestSpecBuilder.addHeader(headerName,headerValue);
        CustomLogger.logger.info("Add header [{}] -> [{}]",headerName,headerValue);
    }

    public void setContentTypeAndAccept(String  contentType){
        requestSpecBuilder.setContentType(contentType).setAccept(contentType);
        CustomLogger.logger.info("Set content type to [{}]",contentType);
    }
    public void setBodyAsFile(String filePath){
        try {
            requestSpecBuilder.setBody(Files.readAllBytes(Paths.get(filePath)));
            CustomLogger.logger.info("Set body as file located at [{}]", filePath);
        } catch (IOException e) {
            CustomLogger.logger.info("couldn't set body as file located at [{}]", filePath);
            throw new RuntimeException(e);
        }

    }

    public void setBodyAsString(String body){
        requestSpecBuilder.setBody(body);
        CustomLogger.logger.info("Set body as string: [{}]",body);
    }
    public void setBody(Object body){
        requestSpecBuilder.setBody(body);
        CustomLogger.logger.info("Set body as current object [{}]",body);
}


}
