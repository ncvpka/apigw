package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class TransactionType {

    @SerializedName("value")
    private String value;

    @SerializedName("name")
    private String name;

    private String title;

    public TransactionType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
