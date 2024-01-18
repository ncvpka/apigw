package vn.supperapp.apigw.restful.models;

import java.util.Map;

public class FieldInformation {
    private String nameTitle;
    private Map<String, String> listField;

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public Map<String, String> getListField() {
        return listField;
    }

    public void setListField(Map<String, String> listField) {
        this.listField = listField;
    }
}
