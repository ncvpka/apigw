package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

public class AgentLocation {

    @SerializedName("CHANNEL_ID")
    private Long channelId;

    @SerializedName("DISTANCE")
    private double distance;

    @SerializedName("CHANNEL_NAME")
    private String channelName;

    @SerializedName("AGENT_CODE")
    private String agentCode;

    @SerializedName("PHONE")
    private String phone;

    @SerializedName("LATITUDE")
    private double latitude;

    @SerializedName("LONGTITUDE")
    private double longtidure;

    @SerializedName("COUNTRY")
    private String country;

    @SerializedName("PROVINCE")
    private String province;

    @SerializedName("DISTRICT")
    private String district;

    @SerializedName("ADDRESS")
    private String address;

    @SerializedName("CONTRACT_NO ")
    private String contractNo;

    @SerializedName("AREA_CODE")
    private String areaCode;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtidure() {
        return longtidure;
    }

    public void setLongtidure(double longtidure) {
        this.longtidure = longtidure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
