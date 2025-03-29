import engine.actions.APIActions;
import engine.constants.ReqresEndPoints;
import engine.pojo.User;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

public class APITests {

    @Test
    public void getRequest(){
        Map<String,String> map=new HashMap<>();
        map.put("id","2");

        Response res=APIActions.performGetRequest(ReqresEndPoints.mainURL+ReqresEndPoints.usersEndPoint
                ,null,
          null,  null,map);

        res.then().spec(APIActions.checkResponseStatus(200));
        System.out.println(APIActions.getValueByPath(res,"data.id"));

        User user= (User) APIActions.deserializeResponse(res, User.class);
        System.out.println(user.getSingleUserData().getAvatar());
//        user.getUserList().forEach(userData -> System.out.println(userData.getEmail()));

    }
}