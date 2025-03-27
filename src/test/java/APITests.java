import engine.actions.APIActions;
import engine.pojo.User;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class APITests {

    @Test
    public void tyial(){
        Map<String,String> map=new HashMap<>();
        map.put("page","2");

        Response res=APIActions.performGetRequest("https://reqres.in/api/users",null,
          null,  map,null);
        res.then().spec(APIActions.checkResponseStatus(200));
        res.then().spec(APIActions.checkResponseContent("application/json;"));
        for(Header header: APIActions.getAllHeaders(res)){
            System.out.println(header);
        }
        for(Map.Entry<String, String> header: APIActions.getAllCookies(res).entrySet()){
            System.out.println(header.getKey());
            System.out.println(header.getValue());
        }
        Assert.assertEquals((int) (Integer) APIActions.getValueByPath(res, "page"), 2);

       User user= (User) APIActions.deserializeUser(res, User.class);
       System.out.println(user.getPage());
         user.getData().forEach(userData -> System.out.println(userData.getId() +" "+ userData.getEmail()));
        System.out.println(user.getSupport().getUrl());

    }
}
