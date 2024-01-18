package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.Branch;

import java.util.List;

public class OrganizationInfo {
    private Long id;
    private String code;
    private String name;
    private String phone;
    private String address;
    List<Branch> branchList;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
