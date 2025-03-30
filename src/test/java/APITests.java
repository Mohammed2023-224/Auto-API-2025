import engine.api.APIRequestBuilder;
import engine.api.GetRequestOldWay;
import engine.api.ResponseActions;
import engine.constants.ReqresEndPoints;
import engine.pojo.User;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class APITests {

    private APIRequestBuilder apiRequestBuilder;

    @Test
    public void testGetRequestSingleUser(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint+"/2");
        apiRequestBuilder.addHeader("test","etsting");
        Response res=apiRequestBuilder.performRequest("get");
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,"Application/json;");
        ResponseActions.checkResponseHeader(res,"Content-Type","application/json; charset=utf-8");
        int id=(Integer) ResponseActions.getValueByPath(res,"data.id");
        System.out.println(id);
        System.out.println(ResponseActions.getValueByPath(res,"data"));
        System.out.println(ResponseActions.getAllHeaders(res));
        User user = (User)ResponseActions.deserializeResponse(res,User.class);
        System.out.println(user);
        System.out.println(user.getSupport().getUrl());
        System.out.println(user.getPage());
        System.out.println(user.getUserList());
        System.out.println(user.getSingleUserData());
        System.out.println(user.getSingleUserData().getEmail());
    }

    @Test
    public void testGetRequestListUsers(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.addQueryParams("page","2");
        apiRequestBuilder.addHeader("test","etsting");
        Response res=apiRequestBuilder.performRequest("get");
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,"Application/json;");
        ResponseActions.checkResponseHeader(res,"Content-Type","application/json; charset=utf-8");
        int id=(Integer) ResponseActions.getValueByPath(res,"data.id[0]");
        System.out.println(id+"test");
        System.out.println(ResponseActions.getValueByPath(res,"data"));
        System.out.println(ResponseActions.getAllHeaders(res));
        User user = (User)ResponseActions.deserializeResponse(res,User.class);
        System.out.println(user);
        System.out.println(user.getSupport().getUrl());
        System.out.println(user.getPage());
        System.out.println(user.getUserList().size());
        user.getUserList().stream().forEach(userData ->
                System.out.println(userData.getId()+" "+userData.getEmail()));

        user.getUserList().stream().forEach(userData -> {System.out.println(userData.getId() +" "+ userData.getEmail());
        } );

    }

    @BeforeMethod
    private void initClass(){
        apiRequestBuilder=new APIRequestBuilder();
        apiRequestBuilder.setURL(ReqresEndPoints.mainURL);
    }

}