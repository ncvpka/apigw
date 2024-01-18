package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.DropDown;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "HR_PARAM_VALUE", schema = "SUPPERAPP", catalog = "")
@SqlResultSetMapping(name = "DROPDOWN_MAPPING", classes = {@ConstructorResult(
        targetClass = DropDown.class, columns = {
        @ColumnResult(name = "value", type = Long.class),
        @ColumnResult(name = "title", type = String.class)
})})
public class HrParamValue {
    public static final String TABLE_NAME = "HR_PARAM_VALUE";
    public static final String SEQ_NAME = "HR_PARAM_VALUE_SEQ";
    public static final String DROPDOWN_MAPPING = "DROPDOWN_MAPPING";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_param_value_generator")
    @SequenceGenerator(name = "hr_param_value_generator", allocationSize = 1, sequenceName = "HR_PARAM_VALUE_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "PARAM_ID")
    private Long paramId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CODE")
    private String code;
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
    @Column(name = "LANG_VI")
    private String langVi;
    @Basic
    @Column(name = "LANG_EN")
    private String langEn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLangVi() {
        return langVi;
    }

    public void setLangVi(String langVi) {
        this.langVi = langVi;
    }

    public String getLangEn() {
        return langEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrParamValue that = (HrParamValue) o;
        return Objects.equals(id, that.id) && Objects.equals(paramId, that.paramId) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(status, that.status) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paramId, name, code, status, orgId, createBy, createAt, updateBy, updateAt);
    }
}
