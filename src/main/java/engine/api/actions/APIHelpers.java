package engine.api.actions;

import engine.gui.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class APIHelpers {

    protected static int convertObjectIntoInt(Object data){
        int newData=0;
            try{
                newData= (Integer) data;
                CustomLogger.logger.info("Convert object into integer");
            }
            catch (Exception e){
                CustomLogger.logger.info("Can't Convert object into integer");
            }
        return newData;
    }

    /**
     first string is always the type of authorization
     **/
    protected static AuthenticationScheme setAuthorizationScheme(String... info){
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


    public static Response convertFutureResponseToResponse(CompletableFuture<Response> res){
        Response response;
        try {
            response = res.get();
            CustomLogger.logger.info("Cast future response into response type");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return response;
        }
}
