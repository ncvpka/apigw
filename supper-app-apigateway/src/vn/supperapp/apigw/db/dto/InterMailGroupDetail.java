package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "INTER_MAIL_GROUP_DETAIL", schema = "SUPPERAPP", catalog = "")
public class InterMailGroupDetail {
    public static final String TABLE_NAME = "INTER_MAIL_GROUP_DETAIL";
    public static final String SEQ_NAME = "INTER_MAIL_GROUP_DETAIL_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inter_mail_group_detail_generator")
    @SequenceGenerator(name = "inter_mail_group_detail_generator", allocationSize = 1, sequenceName = "INTER_MAIL_GROUP_DETAIL_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Basic
    @Column(name = "OBJ_ID")
    private Long objId;
    @Basic
    @Column(name = "OBJ_TYPE")
    private String objType;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
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
        InterMailGroupDetail that = (InterMailGroupDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(groupId, that.groupId) && Objects.equals(objId, that.objId) && Objects.equals(objType, that.objType) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupId, objId, objType, orgId, createBy, createAt, updateBy, updateAt);
    }
}
