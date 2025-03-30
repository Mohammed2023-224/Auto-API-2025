package engine.api;

import engine.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class APIRequestBuilder {

    RequestSpecBuilder requestSpecBuilder =new RequestSpecBuilder();

    public Response performRequest(String requestType){
        Response res = null;
        switch (requestType){
            case "get":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().get();
                CustomLogger.logger.info("Start executing get request");
                break;
            case "post":
                res= RestAssured.given().spec(requestSpecBuilder.build()).when().post();
                CustomLogger.logger.info("Start executing post request");
                break;
        }
        return res;
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
        File file=new File(filePath);
        requestSpecBuilder.setBody(file);
        CustomLogger.logger.info("Set body as file located at [{}]",filePath);
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
