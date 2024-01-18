package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "APP_IMAGE")
public class AppImage implements java.io.Serializable {
    
    public static final String TABLE_NAME = "APP_IMAGE";
    public static final String SEQ_NAME = "APP_IMAGE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_image_generator")
    @SequenceGenerator(name = "app_image_generator", allocationSize = 1, sequenceName = "APP_IMAGE_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "OBJ_TYPE")
    private String objType;
    @Column(name = "OBJ_ID")
    private Long objId;
    @Column(name = "OBJ_VALUE")
    private String objValue;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "IMAGE_TYPE")
    private String imageType;
    @Column(name = "URL")
    private String url;
    @Column(name = "THUMB_URL")
    private String thumbUrl;
    @Column(name = "DESCRIPTION")
    private Long description;
    @Column(name = "CREATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "UPDATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateTIme;

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

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getObjValue() {
        return objValue;
    }

    public void setObjValue(String objValue) {
        this.objValue = objValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTIme() {
        return updateTIme;
    }

    public void setUpdateTIme(Date updateTIme) {
        this.updateTIme = updateTIme;
    }
}
