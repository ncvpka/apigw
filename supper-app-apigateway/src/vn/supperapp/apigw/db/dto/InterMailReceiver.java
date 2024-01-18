package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "INTER_MAIL_RECEIVER", schema = "SUPPERAPP", catalog = "")
public class InterMailReceiver {
    public static final String TABLE_NAME = "INTER_MAIL_RECEIVER";
    public static final String SEQ_NAME = "INTER_MAIL_RECEIVER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inter_mail_receiver_generator")
    @SequenceGenerator(name = "inter_mail_receiver_generator", allocationSize = 1, sequenceName = "inter_mail_receiver_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "MAIL_ID")
    private Long mailId;
    @Basic
    @Column(name = "USER_ID")
    private Long userId;
    @Basic
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "TYPE_RECEIVER")
    private String typeReceiver;
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

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeReceiver() {
        return typeReceiver;
    }

    public void setTypeReceiver(String typeReceiver) {
        this.typeReceiver = typeReceiver;
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

        InterMailReceiver that = (InterMailReceiver) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mailId != null ? !mailId.equals(that.mailId) : that.mailId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (typeReceiver != null ? !typeReceiver.equals(that.typeReceiver) : that.typeReceiver != null) return false;
        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mailId != null ? mailId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (typeReceiver != null ? typeReceiver.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
