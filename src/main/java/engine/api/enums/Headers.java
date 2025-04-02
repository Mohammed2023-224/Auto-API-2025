package engine.api.enums;

public enum Headers {
    APPJSONHEADER ("application/json"),
    APPJSONHEADERCONTENTTYPE ("application/json; charset=utf-8"),
    CONTENTTYPE ("Content-Type");

    private final String header;
    public String getMethod() {
        return header;
    }
    Headers(String header) {
        this.header=header;
    }
}
