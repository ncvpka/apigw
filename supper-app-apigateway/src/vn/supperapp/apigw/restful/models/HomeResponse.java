package vn.supperapp.apigw.restful.models;

import vn.supperapp.apigw.beans.*;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;
import java.util.Map;

public class HomeResponse extends BaseShopResponse {

    private PagingResult data;
    private List<Map<String, Map<String, String>>> dataWidget;
    private String widgetType;
    private MobileAppVersionInfo newAppVersion;

    public HomeResponse() {
    }

    public HomeResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public PagingResult getData() {
        return data;
    }

    public void setData(PagingResult data) {
        this.data = data;
    }

    public List<Map<String, Map<String, String>>> getDataWidget() {
        return dataWidget;
    }

    public void setDataWidget(List<Map<String, Map<String, String>>> dataWidget) {
        this.dataWidget = dataWidget;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public MobileAppVersionInfo getNewAppVersion() {
        return newAppVersion;
    }

    public void setNewAppVersion(MobileAppVersionInfo newAppVersion) {
        this.newAppVersion = newAppVersion;
    }
}
