package engine.pojo;

import java.util.ArrayList;
import java.util.List;

public class User {

    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    private List<UserData> data;
    public Support support;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<UserData> getData() { return data; }
    public void setData(List<UserData> data) { this.data = data; }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }






    public class Support{
        public String text ;
        public String url ;
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class UserData {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;

        // Getters & Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getFirst_name() { return first_name; }
        public void setFirst_name(String first_name) { this.first_name = first_name; }

        public String getLast_name() { return last_name; }
        public void setLast_name(String last_name) { this.last_name = last_name; }

        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
    }
}
