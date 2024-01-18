package vn.supperapp.apigw.restful.models;

public class HomeRequest extends BaseRequest {
    private int page = 0;

    private String type;
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
