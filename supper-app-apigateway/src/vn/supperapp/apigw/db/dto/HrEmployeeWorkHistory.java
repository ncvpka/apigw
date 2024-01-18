package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "HR_EMPLOYEE_WORK_HISTORY", schema = "SUPPERAPP", catalog = "")
public class HrEmployeeWorkHistory {
    public static final String TABLE_NAME = "HR_EMPLOYEE_WORK_HISTORY";
    public static final String SEQ_NAME = "HR_EMPLOYEE_WORK_HISTORY_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_employee_work_history_generator")
    @SequenceGenerator(name = "hr_employee_work_history_generator", allocationSize = 1, sequenceName = "HR_EMPLOYEE_WORK_HISTORY_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "ID_USER")
    private Long idUser;
    @Basic
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Basic
    @Column(name = "START_DATE")
    private Date startDate;
    @Basic
    @Column(name = "END_DATE")
    private Date endDate;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "SALARY")
    private Long salary;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrEmployeeWorkHistory that = (HrEmployeeWorkHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(idUser, that.idUser) && Objects.equals(companyName, that.companyName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(address, that.address) && Objects.equals(salary, that.salary) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, companyName, startDate, endDate, address, salary, orgId, createBy, createAt, updateBy, updateAt);
    }
}
