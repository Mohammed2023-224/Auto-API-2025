package engine.api.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class User {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private Object data;
    private Support support;

    public UserData getSingleUserData() {
        return (UserData) PojoHelpers.getSingleData(data,UserData.class);
    }

    @SuppressWarnings("unchecked")
    public List<UserData> getUserList() {
        return (List<UserData>) PojoHelpers.getDataInList(data, UserData.class);
    }

    @Getter
    @Setter
    public static class Support{
        private String text ;
        private String url ;
    }

    @Setter
    @Getter
    public static class UserData {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    private String pantone_value;
    private String color;
    private String name;
    private int year;
    }

}
