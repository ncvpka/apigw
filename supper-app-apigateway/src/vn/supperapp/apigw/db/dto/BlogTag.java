package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.BlogTagInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BLOG_TAG")
@SqlResultSetMapping(name = "BLOG_TAG_MAPPING", classes = {@ConstructorResult(
        targetClass = BlogTagInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "userId", type = Long.class),
        @ColumnResult(name = "objId", type = Long.class),
        @ColumnResult(name = "fullName", type = String.class),
        @ColumnResult(name = "objType", type = String.class),
        @ColumnResult(name = "createAt", type = Date.class),
        @ColumnResult(name = "createBy", type = String.class),
})})
public class BlogTag implements java.io.Serializable {
    
    public static final String TABLE_NAME = "BLOG_TAG";
    public static final String SEQ_NAME = "BLOG_TAG_SEQ";
    public static final String BLOG_TAG_MAPPING = "BLOG_TAG_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_tag_generator")
    @SequenceGenerator(name = "blog_tag_generator", allocationSize = 1, sequenceName = "BLOG_TAG_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "OBJ_ID")
    private Long objId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "FULL_NAME")
    private String fullName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
