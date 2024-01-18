package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "HR_POSITION", schema = "SUPPERAPP", catalog = "")
public class HrPosition {
    public static final String TABLE_NAME = "HR_POSITION";
    public static final String SEQ_NAME = "HR_POSITION_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_position_generator")
    @SequenceGenerator(name = "hr_position_generator", allocationSize = 1, sequenceName = "HR_POSITION_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "NAME")
    private String name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrPosition that = (HrPosition) o;
        return Objects.equals(id, that.id) && Objects.equals(departmentId, that.departmentId) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(status, that.status) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentId, code, name, status, orgId, createBy, createAt, updateBy, updateAt);
    }
}
