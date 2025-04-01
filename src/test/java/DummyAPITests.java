import engine.gui.reporter.CustomLogger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class DummyAPITests {

    @Test
    public void test(){
        String url="https://reqres.in/api/";
        Response response=RestAssured.given().when().get(url+"users?page=2");
        CustomLogger.logger.info(response.then());
        CustomLogger.logger.info(response.jsonPath());
        CustomLogger.logger.info(response.statusCode());
        CustomLogger.logger.info(response.then().statusCode(200)
                .and().body("data.id[1]",equalTo(8)));

        JSONObject obj=new JSONObject();
        obj.put("name","salah");
        obj.put("job","plauer");
   RestAssured.given().body(obj.toJSONString()).contentType(ContentType.JSON).accept(ContentType.JSON)
           .when().post(url+"users").then().statusCode(201).log().all();


    }


}
