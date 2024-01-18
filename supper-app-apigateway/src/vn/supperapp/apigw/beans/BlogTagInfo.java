package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.BlogTag;

import java.util.Date;
import java.util.List;

public class BlogTagInfo {
    private Long id;
    private Long userId;
    private Long objId;
    private String fullName;
    private String objType;
    private Date createAt;
    private String createBy;

    public BlogTagInfo(Long id, Long userId, Long objId, String fullName, String objType, Date createAt, String createBy) {
        this.id = id;
        this.userId = userId;
        this.objId = objId;
        this.fullName = fullName;
        this.objType = objType;
        this.createAt = createAt;
        this.createBy = createBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
