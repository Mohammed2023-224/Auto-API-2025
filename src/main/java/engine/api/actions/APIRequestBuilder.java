package engine.api.actions;

import engine.api.enums.HttpMethods;
import engine.gui.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.awaitility.Awaitility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

public class APIRequestBuilder {

    RequestSpecBuilder requestSpecBuilder =new RequestSpecBuilder();

    private Response sendRequest(String requestType) {
        switch (requestType.toLowerCase()) {
            case "get":
                return RestAssured.given().spec(requestSpecBuilder.build()).when().get();
            case "post":
                return RestAssured.given().spec(requestSpecBuilder.build()).when().post();
            case "put":
                return RestAssured.given().spec(requestSpecBuilder.build()).when().put();
            case "patch":
                return RestAssured.given().spec(requestSpecBuilder.build()).when().patch();
            case "delete":
                return RestAssured.given().spec(requestSpecBuilder.build()).when().delete();
            default:
                throw new IllegalArgumentException("Unsupported request type: " + requestType);
        }
    }

    public Response performRequest(HttpMethods requestType) {
        CustomLogger.logger.info("Start executing sync {} request", requestType.getMethod());
        return sendRequest(requestType.getMethod());
    }


    public CompletableFuture<Response> performAsyncRequest(String requestType, int waitTime, int pollTime, int statusCode) {
        return CompletableFuture.supplyAsync(() -> {
            CustomLogger.logger.info("Start executing async {} request", requestType);
            final Response[] finalResponse = {null};
            Awaitility.await()
                    .atMost(waitTime, TimeUnit.SECONDS)
                    .pollInterval(pollTime, TimeUnit.SECONDS)
                    .until(() -> {
                        Response res = sendRequest(requestType);
                        finalResponse[0] = res;
                        return res.getStatusCode() == statusCode;
                    });

            return finalResponse[0];
        });
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

    public void addQueryParams(Map<String,String> queryParams){
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
            CustomLogger.logger.info("set query parameters: {} -> {}",entry.getKey(), entry.getValue());
        }
    }

    public void addQueryParam(String queryName, String value){
            requestSpecBuilder.addQueryParam(queryName,value);
            CustomLogger.logger.info("add query parameters: {} -> {}",queryName,value);
        }

    public void setPathParams(String path){
                requestSpecBuilder.setBasePath(path);
        CustomLogger.logger.info("Set base path parameter to [{}]",path);
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

    public void setContentTypeAndAccept(String contentType){
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
    public void setBodyAsObject(Object body){
        requestSpecBuilder.setBody(body);
        CustomLogger.logger.info("Set body as current object [{}]",body);
}


}
