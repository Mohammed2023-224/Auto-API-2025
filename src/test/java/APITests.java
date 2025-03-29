import engine.actions.APIActions;
import engine.pojo.User;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class APITests {

    @Test
    public void tyial(){
        Map<String,String> map=new HashMap<>();
        map.put("id","2");

        Response res=APIActions.performGetRequest("https://reqres.in/api/users/{id}",null,
          null,  null,map);
        res.then().spec(APIActions.checkResponseStatus(200));
        res.then().spec(APIActions.checkResponseContent("application/json;"));
        for(Header header: APIActions.getAllHeaders(res)){
            System.out.println(header);
        }
        for(Map.Entry<String, String> header: APIActions.getAllCookies(res).entrySet()){
            System.out.println(header.getKey());
            System.out.println(header.getValue());
        }

        APIActions.assertTrue( APIActions.convertObjectIntoInt(APIActions.getValueByPath(res, "page"))
                , 2);

       User user= (User) APIActions.deserializeResponse(res, User.class);
       System.out.println(user.getPage());

       System.out.println(user.getUserList());
    if(user.getSingleUserData()!=null && user.getUserList()==null){
               System.out.println(user.getSingleUserData().getAvatar());
               System.out.println(user.getSingleUserData().getEmail());
    }
    if(user.getSingleUserData()==null && user.getUserList()!=null){
        System.out.println(user.getUserList().size());
        user.getUserList().forEach(userData -> System.out.println(userData.getEmail()));

    }
        System.out.println(user.getSupport().getUrl());
    }

    }
