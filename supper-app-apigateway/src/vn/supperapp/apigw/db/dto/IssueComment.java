package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ISSUE_COMMENT", schema = "SUPPERAPP")
public class IssueComment {
    public static final String TABLE_NAME = "ISSUE_COMMENT";
    public static final String SEQ_NAME = "ISSUE_COMMENT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_comment_generator")
    @SequenceGenerator(name = "issue_comment_generator", allocationSize = 1, sequenceName = "ISSUE_COMMENT_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "LANGUAGE")
    private String language;
    @Basic
    @Column(name = "ISSUE_ID")
    private Long issueId;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "CONTENT")
    private String content;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
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
        IssueComment that = (IssueComment) o;
        return Objects.equals(id, that.id) && Objects.equals(language, that.language) && Objects.equals(issueId, that.issueId) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(orgId, that.orgId) && Objects.equals(createBy, that.createBy) && Objects.equals(createAt, that.createAt) && Objects.equals(updateBy, that.updateBy) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, language, issueId, title, content, orgId, createBy, createAt, updateBy, updateAt);
    }
}
