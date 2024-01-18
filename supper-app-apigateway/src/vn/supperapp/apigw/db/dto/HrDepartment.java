package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.HREmployeeInfoDetail;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "HR_DEPARTMENT", schema = "SUPPERAPP", catalog = "")
@SqlResultSetMapping(name = "EMPLOYEE_DETAIL", classes = {@ConstructorResult(
        targetClass = HREmployeeInfoDetail.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "code", type = String.class),
        @ColumnResult(name = "fullName", type = String.class),
        @ColumnResult(name = "birthDay", type = Date.class),
        @ColumnResult(name = "dateStartWork", type = Date.class),
        @ColumnResult(name = "gender", type = Long.class),
        @ColumnResult(name = "placeOfBirth", type = String.class),
        @ColumnResult(name = "homeTown", type = String.class),
        @ColumnResult(name = "typeWork", type = String.class),
        @ColumnResult(name = "idTypeWork", type = Long.class),
        @ColumnResult(name = "idNationality", type = Long.class),
        @ColumnResult(name = "nationality", type = String.class),
        @ColumnResult(name = "idReligion", type = Long.class),
        @ColumnResult(name = "religion", type = String.class),
        @ColumnResult(name = "idMaritalStatus", type = Long.class),
        @ColumnResult(name = "maritalStatus", type = String.class),
        @ColumnResult(name = "positionName", type = String.class),
        @ColumnResult(name = "positionId", type = Long.class),
        @ColumnResult(name = "personNumber", type = String.class),
        @ColumnResult(name = "datePersonNumber", type = Date.class),
        @ColumnResult(name = "placePersonNumber", type = String.class),
        @ColumnResult(name = "address", type = String.class),
        @ColumnResult(name = "phone", type = String.class),
        @ColumnResult(name = "email", type = String.class),
        @ColumnResult(name = "emailCompany", type = String.class),
        @ColumnResult(name = "branchId", type = Long.class),
        @ColumnResult(name = "branchName", type = String.class),
        @ColumnResult(name = "departmentId", type = Long.class),
        @ColumnResult(name = "departmentName", type = String.class),
        @ColumnResult(name = "timeKeepingType", type = Long.class),
        @ColumnResult(name = "typeSalary", type = Long.class),
        @ColumnResult(name = "salary", type = Long.class),
        @ColumnResult(name = "status", type = Long.class),
        @ColumnResult(name = "orgId", type = Long.class),
        @ColumnResult(name = "orgName", type = String.class),
        @ColumnResult(name = "createAt", type = Date.class),
        @ColumnResult(name = "userId", type = Long.class),
        @ColumnResult(name = "isFullSartuday", type = Long.class),
        @ColumnResult(name = "bankId", type = Long.class),
        @ColumnResult(name = "bankName", type = String.class),
        @ColumnResult(name = "bankNumber", type = String.class),
        @ColumnResult(name = "note", type = String.class),
        @ColumnResult(name = "mst", type = String.class),
        @ColumnResult(name = "dateSinging", type = Date.class),
        @ColumnResult(name = "endDate", type = Date.class),
        @ColumnResult(name = "homePhone", type = String.class),
        @ColumnResult(name = "idEthnic", type = Long.class),
        @ColumnResult(name = "nameEthnic", type = String.class),
        @ColumnResult(name = "permanentResidence", type = String.class),
        @ColumnResult(name = "insurancePremium", type = Long.class),
        @ColumnResult(name = "avatar", type = String.class),
        @ColumnResult(name = "username", type = String.class),
        @ColumnResult(name = "rank", type = String.class),
        @ColumnResult(name = "managerName", type = String.class),
        @ColumnResult(name = "managerId", type = Long.class),
})})
public class HrDepartment {
    public static final String TABLE_NAME = "HR_DEPARTMENT";
    public static final String SEQ_NAME = "HR_DEPARTMENT_SEQ";
    public static final String EMPLOYEE_MAPPING = "EMPLOYEE_DETAIL";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_department_generator")
    @SequenceGenerator(name = "hr_department_generator", allocationSize = 1, sequenceName = "HR_DEPARTMENT_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "ID_PARENT")
    private Long idParent;
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
    @Column(name = "STATUS")
    private Long status;

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

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrDepartment that = (HrDepartment) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(idParent, that.idParent) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, idParent, orgId, createBy, createAt, updateBy, updateAt);
    }
}
