package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "HR_EMPLOYEE_RANK", schema = "SUPPERAPP", catalog = "")
public class HrEmployeeRank {
    public static final String TABLE_NAME = "HR_EMPLOYEE_RANK";
    public static final String SEQ_NAME = "HR_EMPLOYEE_RANK_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_employee_rank_generator")
    @SequenceGenerator(name = "hr_employee_rank_generator", allocationSize = 1, sequenceName = "HR_EMPLOYEE_RANK_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "ID_USER_MANAGER")
    private Long idUserManager;
    @Basic
    @Column(name = "ID_USER")
    private Long idUser;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdUserManager() {
        return idUserManager;
    }

    public void setIdUserManager(Long idUserManager) {
        this.idUserManager = idUserManager;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
        HrEmployeeRank that = (HrEmployeeRank) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(idUserManager, that.idUserManager) && Objects.equals(idUser, that.idUser) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, idUserManager, idUser, orgId, createBy, createAt, updateBy, updateAt);
    }
}
