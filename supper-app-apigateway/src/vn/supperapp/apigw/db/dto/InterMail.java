package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "INTER_MAIL", schema = "SUPPERAPP", catalog = "")
public class InterMail {
    public static final String TABLE_NAME = "INTER_MAIL";
    public static final String SEQ_NAME = "INTER_MAIL_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inter_mail_generator")
    @SequenceGenerator(name = "inter_mail_generator", allocationSize = 1, sequenceName = "INTER_MAIL_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "SEND_USER_ID")
    private Long sendUserId;
    @Basic
    @Column(name = "SEND_DATE")
    private Date sendDate;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "CONTENT")
    private String content;
    @Basic
    @Column(name = "MAIL_BEFORE")
    private Long mailBefore;
    @Basic
    @Column(name = "MAIL_FIRST")
    private Long mailFirst;
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

    public Long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMailBefore() {
        return mailBefore;
    }

    public void setMailBefore(Long mailBefore) {
        this.mailBefore = mailBefore;
    }

    public Long getMailFirst() {
        return mailFirst;
    }

    public void setMailFirst(Long mailFirst) {
        this.mailFirst = mailFirst;
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
        InterMail that = (InterMail) o;
        return Objects.equals(id, that.id) && Objects.equals(sendUserId, that.sendUserId) && Objects.equals(sendDate, that.sendDate) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(mailBefore, that.mailBefore) && Objects.equals(mailFirst, that.mailFirst) && Objects.equals(status, that.status) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sendUserId, sendDate, title, content, mailBefore, mailFirst, status, orgId, createBy, createAt, updateBy, updateAt);
    }
}
