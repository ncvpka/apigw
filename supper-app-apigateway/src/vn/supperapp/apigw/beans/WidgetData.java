package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppImage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WidgetData {
    Map<String, Map<String, String>> dataWidget;

    public Map<String, Map<String, String>> getDataWidget() {
        return dataWidget;
    }

    public void setDataWidget(Map<String, Map<String, String>> dataWidget) {
        this.dataWidget = dataWidget;
    }
}
