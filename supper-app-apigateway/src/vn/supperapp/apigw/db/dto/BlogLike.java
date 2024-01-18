package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BLOG_LIKE")
public class BlogLike implements java.io.Serializable {
    
    public static final String TABLE_NAME = "BLOG_LIKE";
    public static final String SEQ_NAME = "BLOG_LIKE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_like_generator")
    @SequenceGenerator(name = "blog_like_generator", allocationSize = 1, sequenceName = "BLOG_LIKE_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "BLOG_ID")
    private Long blogId;
    @Column(name = "OBJ_TYPE")
    private String objType;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "DATE_LIKE")
    private Date dateLike;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateLike() {
        return dateLike;
    }

    public void setDateLike(Date dateLike) {
        this.dateLike = dateLike;
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
}
