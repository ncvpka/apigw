package vn.supperapp.apigw.restful.models.hr;

import vn.supperapp.apigw.restful.models.BaseRequest;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Transient;
import java.sql.Date;

public class HrEmployeeRequest extends BaseRequest {

    private String image;
    private String code;
    private String fullName;
    private String employeeType;
    private String dateStartWork;
    private String birthDay;
    private Long gender;
    private String placeOfBirth;
    private String homeTown;
    private Long userIdManager;
    private Long idTypeWork;
    private Long idNationnality;
    private Long idReligion;
    private Long idMaritalStatus;
    private Long idPosition;
    private String personNumber;
    private String datePersonNumber;
    private String placePersonNumber;
    private String currentAddress;
    private String phone;
    private String email;
    private String emailCompany;
    private Long branchId;
    private Long departmentId;
    private Long timekeepingType;
    private Long typeSalary;
    private Long salary;
    private Long id;
    private Long status;
    private String endDate;
    private String dateSinging;
    private Long isFullSaturday;
    private String mst;
    private Long bankId;
    private String note;
    private String rank;
    private String bankNumber;
    private Long idEthnic;
    private String homePhone;
    private String permanentResidence;
    private Long insurancePremium;
    private String avatar;
    private String userName;
    private int page = 0;
    private Long month;
    private String workType;
    private String keyWord;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
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

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public Long getUserIdManager() {
        return userIdManager;
    }

    public void setUserIdManager(Long userIdManager) {
        this.userIdManager = userIdManager;
    }

    public Long getIdTypeWork() {
        return idTypeWork;
    }

    public void setIdTypeWork(Long idTypeWork) {
        this.idTypeWork = idTypeWork;
    }

    public Long getIdNationnality() {
        return idNationnality;
    }

    public void setIdNationnality(Long idNationnality) {
        this.idNationnality = idNationnality;
    }

    public Long getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(Long idReligion) {
        this.idReligion = idReligion;
    }

    public Long getIdMaritalStatus() {
        return idMaritalStatus;
    }

    public void setIdMaritalStatus(Long idMaritalStatus) {
        this.idMaritalStatus = idMaritalStatus;
    }

    public Long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }


    public String getPlacePersonNumber() {
        return placePersonNumber;
    }

    public void setPlacePersonNumber(String placePersonNumber) {
        this.placePersonNumber = placePersonNumber;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getTimekeepingType() {
        return timekeepingType;
    }

    public void setTimekeepingType(Long timekeepingType) {
        this.timekeepingType = timekeepingType;
    }

    public Long getTypeSalary() {
        return typeSalary;
    }

    public void setTypeSalary(Long typeSalary) {
        this.typeSalary = typeSalary;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(String dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDatePersonNumber() {
        return datePersonNumber;
    }

    public void setDatePersonNumber(String datePersonNumber) {
        this.datePersonNumber = datePersonNumber;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDateSinging() {
        return dateSinging;
    }

    public void setDateSinging(String dateSinging) {
        this.dateSinging = dateSinging;
    }

    public Long getIsFullSaturday() {
        return isFullSaturday;
    }

    public void setIsFullSaturday(Long isFullSaturday) {
        this.isFullSaturday = isFullSaturday;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Long getIdEthnic() {
        return idEthnic;
    }

    public void setIdEthnic(Long idEthnic) {
        this.idEthnic = idEthnic;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getPermanentResidence() {
        return permanentResidence;
    }

    public void setPermanentResidence(String permanentResidence) {
        this.permanentResidence = permanentResidence;
    }

    public Long getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Long insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getKeyWord() {
        return keyWord;
    }

    @Override
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
