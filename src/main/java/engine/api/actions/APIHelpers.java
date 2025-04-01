package engine.api.actions;

import engine.gui.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.awaitility.Awaitility;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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

    protected static void awaitHelper(String requestType,int waitTime, int pollTime,int statusCode, RequestSpecBuilder req){
        Awaitility.await()
                .atMost(waitTime, TimeUnit.SECONDS)
                .pollInterval(pollTime, TimeUnit.SECONDS)
                .until(() -> {
                    Response asyncRes;
                    switch (requestType.toLowerCase()) {
                        case "get":
                            asyncRes = RestAssured.given()
                                    .spec(req.build())
                                    .when()
                                    .get()
                                    .then()
                                    .extract()
                                    .response();
                            break;
                        case "post":
                            asyncRes = RestAssured.given()
                                    .spec(req.build())
                                    .when()
                                    .post()
                                    .then()
                                    .extract()
                                    .response();
                            break;
                        case "put":
                            asyncRes = RestAssured.given()
                                    .spec(req.build())
                                    .when()
                                    .put()
                                    .then()
                                    .extract()
                                    .response();
                            break;
                        case "patch":
                            asyncRes = RestAssured.given()
                                    .spec(req.build())
                                    .when()
                                    .patch()
                                    .then()
                                    .extract()
                                    .response();
                            break;
                        case "delete":
                            asyncRes = RestAssured.given()
                                    .spec(req.build())
                                    .when()
                                    .delete()
                                    .then()
                                    .extract()
                                    .response();
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported request type: " + requestType);
                    }
                    return asyncRes.getStatusCode() == statusCode;
                });
    }

    public static Response convertFutureResponseToResponse(CompletableFuture<Response> res){
        Response response;
        try {
            response = res.get();
            CustomLogger.logger.info("Cast type into response type");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    return response;
        }
}
