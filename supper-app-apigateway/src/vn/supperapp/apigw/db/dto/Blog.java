package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.BlogInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BLOG")
@SqlResultSetMapping(name = "BLOG_MAPPING", classes = {@ConstructorResult(
        targetClass = BlogInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "userId", type = Long.class),
        @ColumnResult(name = "fullName", type = String.class),
        @ColumnResult(name = "avatarUrl", type = String.class),
        @ColumnResult(name = "category", type = Long.class),
        @ColumnResult(name = "type", type = String.class),
        @ColumnResult(name = "title", type = String.class),
        @ColumnResult(name = "content", type = String.class),
        @ColumnResult(name = "status", type = String.class),
        @ColumnResult(name = "createAt", type = String.class),
        @ColumnResult(name = "countView", type = Long.class),
        @ColumnResult(name = "countLike", type = Long.class),
        @ColumnResult(name = "countComment", type = Long.class)
})})
public class Blog implements java.io.Serializable {

    public static final String TABLE_NAME = "BLOG";
    public static final String SEQ_NAME = "BLOG_SEQ";
    public static final String BLOG_MAPPING = "BLOG_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_generator")
    @SequenceGenerator(name = "blog_generator", allocationSize = 1, sequenceName = "BLOG_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "CATEGORY")
    private Long category;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "COUNT_VIEW")
    private Long countView;
    @Column(name = "COUNT_LIKE")
    private Long countLike;
    @Column(name = "COUNT_COMMENT")
    private Long countComment;
    @Column(name = "BRANCH_ID")
    private Long branchId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "APPROVE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveAt;
    @Column(name = "APPROVE_BY")
    private String approveBy;
    @Column(name = "QUOTE")
    private String quote;
    @Column(name = "DISPATCH")
    private String dispatch;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "APPLY_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyAt;
    @Column(name = "ISSUANCE")
    private Long issuance;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Column(name = "USER_ID")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
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

    public Long getCountView() {
        return countView;
    }

    public void setCountView(Long countView) {
        this.countView = countView;
    }

    public Long getCountLike() {
        return countLike;
    }

    public void setCountLike(Long countLike) {
        this.countLike = countLike;
    }

    public Long getCountComment() {
        return countComment;
    }

    public void setCountComment(Long countComment) {
        this.countComment = countComment;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApproveAt() {
        return approveAt;
    }

    public void setApproveAt(Date approveAt) {
        this.approveAt = approveAt;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getApplyAt() {
        return applyAt;
    }

    public void setApplyAt(Date applyAt) {
        this.applyAt = applyAt;
    }

    public Long getIssuance() {
        return issuance;
    }

    public void setIssuance(Long issuance) {
        this.issuance = issuance;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
