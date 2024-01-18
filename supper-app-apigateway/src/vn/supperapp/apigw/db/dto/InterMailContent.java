package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "INTER_MAIL_CONTENT", schema = "SUPPERAPP", catalog = "")
public class InterMailContent {
    public static final String TABLE_NAME = "INTER_MAIL_CONTENT";
    public static final String SEQ_NAME = "INTER_MAIL_CONTENT_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inter_mail_content_generator")
    @SequenceGenerator(name = "inter_mail_content_generator", allocationSize = 1, sequenceName = "INTER_MAIL_CONTENT_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "MAIL_ID")
    private Long mailId;
    @Basic
    @Column(name = "RECEIVE_USER_ID")
    private Long receiveUserId;
    @Basic
    @Column(name = "VIEW_STATUS")
    private Long viewStatus;
    @Basic
    @Column(name = "DELETE_STATUS")
    private Long deleteStatus;
    @Basic
    @Column(name = "PERMANENTLY_DELETED")
    private Long permanentlyDeleted;
    @Basic
    @Column(name = "DATE_VIEW")
    private Date dateView;
    @Basic
    @Column(name = "DATE_DELETE")
    private Date dateDelete;
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

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Long getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(Long viewStatus) {
        this.viewStatus = viewStatus;
    }

    public Long getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Long deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Long getPermanentlyDeleted() {
        return permanentlyDeleted;
    }

    public void setPermanentlyDeleted(Long permanentlyDeleted) {
        this.permanentlyDeleted = permanentlyDeleted;
    }

    public Date getDateView() {
        return dateView;
    }

    public void setDateView(Date dateView) {
        this.dateView = dateView;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
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
        InterMailContent that = (InterMailContent) o;
        return Objects.equals(id, that.id) && Objects.equals(mailId, that.mailId) && Objects.equals(receiveUserId, that.receiveUserId) && Objects.equals(viewStatus, that.viewStatus) && Objects.equals(deleteStatus, that.deleteStatus) && Objects.equals(permanentlyDeleted, that.permanentlyDeleted) && Objects.equals(dateView, that.dateView) && Objects.equals(dateDelete, that.dateDelete) && Objects.equals(status, that.status) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mailId, receiveUserId, viewStatus, deleteStatus, permanentlyDeleted, dateView, dateDelete, status, orgId, createBy, createAt, updateBy, updateAt);
    }
}
