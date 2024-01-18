package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.EmployeeInfo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "HR_EMPLOYEE", schema = "SUPPERAPP", catalog = "")
@SqlResultSetMapping(name = "EMPLOYEE_MAPPING", classes = {@ConstructorResult(
        targetClass = EmployeeInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "code", type = String.class),
        @ColumnResult(name = "fullName", type = String.class),
        @ColumnResult(name = "gender", type = Long.class),
        @ColumnResult(name = "userId", type = Long.class),
        @ColumnResult(name = "birthDay", type = Date.class),
        @ColumnResult(name = "email", type = String.class),
        @ColumnResult(name = "address", type = String.class),
        @ColumnResult(name = "personNumber", type = String.class),
        @ColumnResult(name = "avatarUrl", type = String.class),
        @ColumnResult(name = "phone", type = String.class),
        @ColumnResult(name = "timeKeepingType", type = Long.class),
        @ColumnResult(name = "orgId", type = Long.class),
        @ColumnResult(name = "orgName", type = String.class),
        @ColumnResult(name = "branchId", type = Long.class),
        @ColumnResult(name = "branchName", type = String.class),
        @ColumnResult(name = "status", type = Long.class),
        @ColumnResult(name = "departmentId", type = Long.class),
        @ColumnResult(name = "departmentName", type = String.class),
        @ColumnResult(name = "typeWork", type = String.class),
        @ColumnResult(name = "managerName", type = String.class),
        @ColumnResult(name = "managerId", type = Long.class),
        @ColumnResult(name = "positionName", type = String.class),
        @ColumnResult(name = "positionId", type = Long.class)
})})
public class HrEmployee {
    public static final String TABLE_NAME = "HR_EMPLOYEE";
    public static final String SEQ_NAME = "HR_EMPLOYEE_SEQ";
    public static final String EMPLOYEE_MAPPING = "EMPLOYEE_MAPPING";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_employee_generator")
    @SequenceGenerator(name = "hr_employee_generator", allocationSize = 1, sequenceName = "HR_EMPLOYEE_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "FULL_NAME")
    private String fullName;
    @Basic
    @Column(name = "EMPLOYEE_TYPE")
    private String employeeType;
    @Basic
    @Column(name = "DATE_START_WORK")
    private Date dateStartWork;
    @Basic
    @Column(name = "GENDER")
    private Long gender;
    @Basic
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Basic
    @Column(name = "PLACE_OF_BIRTH")
    private String placeOfBirth;
    @Basic
    @Column(name = "HOME_TOWN")
    private String homeTown;
    @Basic
    @Column(name = "USER_ID_MANAGER")
    private Long userIdManager;
    @Basic
    @Column(name = "ID_TYPE_WORK")
    private Long idTypeWork;
    @Basic
    @Column(name = "ID_NATIONNALITY")
    private Long idNationnality;
    @Basic
    @Column(name = "ID_RELIGION")
    private Long idReligion;
    @Basic
    @Column(name = "ID_MARITAL_STATUS")
    private Long idMaritalStatus;
    @Basic
    @Column(name = "ID_POSITION")
    private Long idPosition;
    @Basic
    @Column(name = "PERSON_NUMBER")
    private String personNumber;
    @Basic
    @Column(name = "DATE_PERSON_NUMBER")
    private Date datePersonNumber;
    @Basic
    @Column(name = "PLACE_PERSON_NUMBER")
    private String placePersonNumber;
    @Basic
    @Column(name = "CURRENT_ADDRESS")
    private String currentAddress;
    @Basic
    @Column(name = "PHONE")
    private String phone;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "EMAIL_COMPANY")
    private String emailCompany;
    @Basic
    @Column(name = "BRANCH_ID")
    private Long branchId;
    @Basic
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;
    @Basic
    @Column(name = "TIMEKEEPING_TYPE")
    private Long timekeepingType;
    @Basic
    @Column(name = "TYPE_SALARY")
    private Long typeSalary;
    @Basic
    @Column(name = "SALARY")
    private Long salary;
    @Basic
    @Column(name = "STATUS")
    private Long status;
    @Basic
    @Column(name = "ORG_ID")
    private Long orgId;
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "APP_USER_ID")
    private Long appUserId;
    @Basic
    @Column(name = "IS_FULL_SATURDAY")
    private Long isFullSaturday;
    @Basic
    @Column(name = "BANK_ID")
    private Long bankId;
    @Basic
    @Column(name = "BANK_NUMBER")
    private String bankNumber;
    @Basic
    @Column(name = "NOTE")
    private String note;
    @Basic
    @Column(name = "MST")
    private String mst;
    @Basic
    @Column(name = "DATE_SINGING")
    private Date dateSinging;
    @Basic
    @Column(name = "END_DATE")
    private Date endDate;
    @Basic
    @Column(name = "HOME_PHONE")
    private String homePhone;

    @Basic
    @Column(name = "ETHNIC_ID")
    private Long ethnicId;
    @Basic
    @Column(name = "PERMANENT_RESIDENCE")
    private String permanentResidence;

    @Basic
    @Column(name = "INSURANCE_PREMIUM")
    private Long insurancePremium;
    @Basic
    @Column(name = "AVATAR")
    private String avatar;
    @Basic
    @Column(name = "USERNAME")
    private String userName;
    @Basic
    @Column(name = "RANK")
    private String rank;
    @Transient
    private String position;

    @Transient
    private String department;

    @Transient
    private String manager;

    @Transient
    private String typeWork;


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

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Date getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(Date dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Date getDatePersonNumber() {
        return datePersonNumber;
    }

    public void setDatePersonNumber(Date datePersonNumber) {
        this.datePersonNumber = datePersonNumber;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(String typeWork) {
        this.typeWork = typeWork;
    }

    public Long getIsFullSaturday() {
        return isFullSaturday;
    }

    public void setIsFullSaturday(Long isFullSaturday) {
        this.isFullSaturday = isFullSaturday;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public Date getDateSinging() {
        return dateSinging;
    }

    public void setDateSinging(Date dateSinging) {
        this.dateSinging = dateSinging;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public Long getEthnicId() {
        return ethnicId;
    }

    public void setEthnicId(Long ethnicId) {
        this.ethnicId = ethnicId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrEmployee that = (HrEmployee) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(fullName, that.fullName) && Objects.equals(employeeType, that.employeeType) && Objects.equals(dateStartWork, that.dateStartWork) && Objects.equals(gender, that.gender) && Objects.equals(birthday, that.birthday) && Objects.equals(placeOfBirth, that.placeOfBirth) && Objects.equals(homeTown, that.homeTown) && Objects.equals(userIdManager, that.userIdManager) && Objects.equals(idTypeWork, that.idTypeWork) && Objects.equals(idNationnality, that.idNationnality) && Objects.equals(idReligion, that.idReligion) && Objects.equals(idMaritalStatus, that.idMaritalStatus) && Objects.equals(idPosition, that.idPosition) && Objects.equals(personNumber, that.personNumber) && Objects.equals(datePersonNumber, that.datePersonNumber) && Objects.equals(placePersonNumber, that.placePersonNumber) && Objects.equals(currentAddress, that.currentAddress) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(emailCompany, that.emailCompany) && Objects.equals(branchId, that.branchId) && Objects.equals(departmentId, that.departmentId) && Objects.equals(timekeepingType, that.timekeepingType) && Objects.equals(typeSalary, that.typeSalary) && Objects.equals(salary, that.salary) && Objects.equals(status, that.status) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, fullName, employeeType, dateStartWork, gender, birthday, placeOfBirth, homeTown, userIdManager, idTypeWork, idNationnality, idReligion, idMaritalStatus, idPosition, personNumber, datePersonNumber, placePersonNumber, currentAddress, phone, email, emailCompany, branchId, departmentId, timekeepingType, typeSalary, salary, status, orgId, createBy, createAt, updateBy, updateAt);
    }
}
