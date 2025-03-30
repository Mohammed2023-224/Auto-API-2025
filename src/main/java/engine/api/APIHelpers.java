package engine.api;

import engine.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;

public class APIHelpers {

    protected static int convertObjectIntoInt(Object data){
        if(data !=null){
            CustomLogger.logger.info("Convert object into integer");
            return (Integer) data;
        }
        return 0;
    }

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
}
