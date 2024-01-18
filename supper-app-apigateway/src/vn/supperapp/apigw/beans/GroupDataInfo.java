package vn.supperapp.apigw.beans;

import java.util.List;

public class GroupDataInfo {

    private Long id;
    private String code;
    private String name;
    private String serviceCode;
    private String partnerCode;
    private int status;
    private String createDate;
    private String updateDate;
    private List<GroupDataPackageInfo> groupDataPackageInfoList;

    public GroupDataInfo(Long id, String code, String name, String serviceCode, String partnerCode, int status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.serviceCode = serviceCode;
        this.partnerCode = partnerCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<GroupDataPackageInfo> getGroupDataPackageInfoList() {
        return groupDataPackageInfoList;
    }

    public void setGroupDataPackageInfoList(List<GroupDataPackageInfo> groupDataPackageInfoList) {
        this.groupDataPackageInfoList = groupDataPackageInfoList;
    }
}
