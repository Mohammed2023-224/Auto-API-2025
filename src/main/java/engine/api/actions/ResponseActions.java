package engine.api.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.gui.reporter.CustomLogger;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class ResponseActions {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper=new ObjectMapper();
    }
    public static void checkResponseStatus(Response res,int status){
        res.then().statusCode(status);
        CustomLogger.logger.info("Validate that status code = {}",status);
    }

    public static void checkResponseContent(Response res, String contentType){
        res.then().contentType(contentType);
        CustomLogger.logger.info("Validate that contentType = {}",contentType);
    }

    public static void checkResponseCookie( Response res,String cookieName, String value){
        res.then().cookie(cookieName,value);
        CustomLogger.logger.info("Validate that cookie  {} == {}",cookieName,value);
    }

    public static void checkResponseHeader( Response res,String headerName, String value){
       res.then().header(headerName,value);
        CustomLogger.logger.info("Validate that header  {} == {}",headerName,value);
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
    public static void validateJsonSchema(Response res,String filePath){
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(filePath)));
    }
}
