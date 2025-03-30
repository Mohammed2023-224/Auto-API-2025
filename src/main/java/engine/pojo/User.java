package engine.pojo;

import java.util.List;

public class User {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private Object data;
    private Support support;

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

    private Object getData() { return data; }  // Can be a single object or a list
    public void setData(Object data) { this.data = data; }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public UserData getSingleUserData() {
        return (UserData) PojoHelpers.getSingleData(data,UserData.class);
    }

    public List<UserData> getUserList() {
        return (List<UserData>) PojoHelpers.getDataInList(data, UserData.class);
    }


    public class Support{
        private String text ;
        private String url ;
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
