import com.google.gson.JsonObject;
import engine.api.actions.APIHelpers;
import engine.api.actions.APIRequestBuilder;
import engine.api.actions.ResponseActions;
import engine.api.constants.ReqresEndPoints;
import engine.api.enums.Headers;
import engine.api.enums.HttpMethods;
import engine.api.pojo.User;
import engine.gui.constants.FrameWorkConstants;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class APITests {

    private APIRequestBuilder apiRequestBuilder;

    @Test
    public void testGetRequestSingleUser(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint+"/2");
        apiRequestBuilder.addHeader("test","etsting");
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        ResponseActions.checkResponseHeader(res,Headers.CONTENTTYPE.getMethod(),
                Headers.APPJSONHEADERCONTENTTYPE.getMethod());
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
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res, Headers.APPJSONHEADER.getMethod());
        ResponseActions.checkResponseHeader(res,Headers.CONTENTTYPE.getMethod()
                ,Headers.APPJSONHEADERCONTENTTYPE.getMethod());
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

    @Test
    public void testNotFoundGetRequest(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint+"/23");
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,404);
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }
    @Test
    public void testUnknownNotFoundGetRequest(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.unknownEndPoint+"/23");
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,404);
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }

    @Test
    public void testUnknownListGetRequest(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.unknownEndPoint);
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,200);
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        ResponseActions.checkResponseHeader(res,Headers.CONTENTTYPE.getMethod(),
                Headers.APPJSONHEADERCONTENTTYPE.getMethod());
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
                System.out.println(userData.getId()+" "+userData.getYear()));
        user.getUserList().stream().forEach(userData -> {System.out.println(userData.getId() +" "+ userData.getName());
        } );
    }
    @Test
    public void testUnknownGetRequest(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.unknownEndPoint+"/2");
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,200);
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        ResponseActions.checkResponseHeader(res,Headers.CONTENTTYPE.getMethod(), Headers.APPJSONHEADERCONTENTTYPE.getMethod());
        int id=(Integer) ResponseActions.getValueByPath(res,"data.id");
        System.out.println(id+"test");
        System.out.println(ResponseActions.getValueByPath(res,"data"));
        System.out.println(ResponseActions.getAllHeaders(res));
        User user = (User)ResponseActions.deserializeResponse(res,User.class);
        System.out.println(user);
        System.out.println(user.getSupport().getUrl());
        System.out.println(user.getPage());
        System.out.println(user.getUserList());
        System.out.println(user.getSingleUserData());
        System.out.println(user.getSingleUserData().getColor());
    }

    @Test
    public void testUserPostRequestWithString(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.setBodyAsString("{\"name\": \"morpheus\", \"job\": \"leader\"}");
        apiRequestBuilder.setContentTypeAndAccept("application/json");
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,201);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }

    @Test
    public void testUserPostRequestWithFile(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.setBodyAsFile(FrameWorkConstants.payLoadPath+"/test.json");
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,201);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }

    @Test
    public void testUserPostRequestWithHashMap(){
        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        map.put("name","mor");
        map.put("job","leader");
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.setBody(map);
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,201);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }


    @Test
    public void testUserPostRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("name","mor");
        jo.addProperty("job","leader");
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,201);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }
    @Test
    public void testUserPutRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("name","mor");
        jo.addProperty("job","zion resident");
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint+"/2");
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.PUT);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }
    @Test
    public void testUserPatchRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("name","mor");
        jo.addProperty("job","zion resident");
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint+"/2");
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.PATCH);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }
    @Test
    public void testUserDeleteRequest(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint+"/2");
        Response res=apiRequestBuilder.performRequest(HttpMethods.DELETE);
        ResponseActions.checkResponseStatus(res,204);
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }

    @Test
    public void testRegisterPostRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("email","eve.holt@reqres.in");
        jo.addProperty("password","pistol");
        apiRequestBuilder.setPathParams(ReqresEndPoints.registerEndPoint);
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }

    @Test
    public void testRegisterUnsuccessfulPostRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("email","eve.holt@reqres.in");
        apiRequestBuilder.setPathParams(ReqresEndPoints.registerEndPoint);
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,400);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        System.out.println(ResponseActions.getValueByPath(res,"error"));
        ResponseActions.logResponse(res);
    }
    @Test
    public void testLoginPostRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("email","eve.holt@reqres.in");
        jo.addProperty("password","cityslicka");
        apiRequestBuilder.setPathParams(ReqresEndPoints.loginEndPoint);
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        ResponseActions.logResponse(res);
    }

    @Test
    public void testLoginUnsuccessfulPostRequestWithJacksonObject(){
        JsonObject jo=new JsonObject();
        jo.addProperty("email","peter@klaven");
        apiRequestBuilder.setPathParams(ReqresEndPoints.loginEndPoint);
        apiRequestBuilder.setBody(jo.toString());
        apiRequestBuilder.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        Response res=apiRequestBuilder.performRequest(HttpMethods.POST);
        ResponseActions.checkResponseStatus(res,400);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        System.out.println(ResponseActions.getResponse(res).statusCode());
        System.out.println(ResponseActions.getValueByPath(res,"error"));
        ResponseActions.logResponse(res);
    }

    @Test
    public void testGetDelayedResponse(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.addQueryParams("delay","10");
        apiRequestBuilder.addHeader("test","etsting");
        Response res=apiRequestBuilder.performRequest(HttpMethods.GET);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        ResponseActions.checkResponseHeader(res,Headers.CONTENTTYPE.getMethod(), Headers.APPJSONHEADERCONTENTTYPE.getMethod());
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

        APIRequestBuilder apiRequestBuilder2=new APIRequestBuilder();
        apiRequestBuilder2.setURL(ReqresEndPoints.mainURL);

        JsonObject jo=new JsonObject();
        jo.addProperty("email","peter@klaven");
        apiRequestBuilder2.setPathParams(ReqresEndPoints.loginEndPoint);
        apiRequestBuilder2.setBody(jo.toString());
        apiRequestBuilder2.setContentTypeAndAccept(Headers.APPJSONHEADER.getMethod());
        apiRequestBuilder2.performRequest(HttpMethods.POST);
    }

    @Test
    public void tests(){
        apiRequestBuilder.setPathParams(ReqresEndPoints.usersEndPoint);
        apiRequestBuilder.addQueryParams("delay","3");
        apiRequestBuilder.addHeader("test","etsting");
        CompletableFuture<Response> fures=apiRequestBuilder.performAsyncRequest("get",9,1,200);
        CompletableFuture<Response> res1=apiRequestBuilder.performAsyncRequest("get",9,1,200);
        CompletableFuture<Response> res2=apiRequestBuilder.performAsyncRequest("get",9,1,200);
        Response res= APIHelpers.convertFutureResponseToResponse(fures);
        Response fures2=APIHelpers.convertFutureResponseToResponse(res1);
        ResponseActions.checkResponseStatus(res,200);
        ResponseActions.checkResponseStatus(fures2,200);
        ResponseActions.checkResponseContent(res,Headers.APPJSONHEADER.getMethod());
        ResponseActions.checkResponseHeader(res,Headers.CONTENTTYPE.getMethod(),
                Headers.APPJSONHEADERCONTENTTYPE.getMethod());
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
        ResponseActions.logResponse(res);
        ResponseActions.validateJsonSchema(res, FrameWorkConstants.schemasPath+"/valid.json");
    }

    @BeforeMethod
    private void initClass(){
        apiRequestBuilder=new APIRequestBuilder();
        apiRequestBuilder.setURL(ReqresEndPoints.mainURL);
    }

}