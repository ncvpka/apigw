package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.Account;

import java.util.Date;

public class EmployeeInfo {
    private Long id;
    private String code;
    private String fullName;
    private Long gender;
    private Long userId;
    private Date birthDay;
    private String email;
    private String address;
    private String personNumber;
    private String avatarUrl;
    private String phone;
    private Long timeKeepingType;
    private Long orgId;
    private String orgName;
    private Long branchId;
    private String branchName;
    private Long status;
    private Long departmentId;
    private String departmentName;
    private String typeWork;
    private String managerName;
    private Long managerId;
    private String positionName;
    private Long positionId;

    public EmployeeInfo() {
    }

    public EmployeeInfo(Long id, String code, String fullName, Long gender, Long userId, Date birthDay, String email, String address, String personNumber, String avatarUrl, String phone, Long timeKeepingType, Long orgId, String orgName, Long branchId, String branchName, Long status, Long departmentId, String departmentName, String typeWork, String managerName, Long managerId, String positionName, Long positionId) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.gender = gender;
        this.userId = userId;
        this.birthDay = birthDay;
        this.email = email;
        this.address = address;
        this.personNumber = personNumber;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.timeKeepingType = timeKeepingType;
        this.orgId = orgId;
        this.orgName = orgName;
        this.branchId = branchId;
        this.branchName = branchName;
        this.status = status;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.typeWork = typeWork;
        this.managerName = managerName;
        this.managerId = managerId;
        this.positionName = positionName;
        this.positionId = positionId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getTimeKeepingType() {
        return timeKeepingType;
    }

    public void setTimeKeepingType(Long timeKeepingType) {
        this.timeKeepingType = timeKeepingType;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(String typeWork) {
        this.typeWork = typeWork;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
