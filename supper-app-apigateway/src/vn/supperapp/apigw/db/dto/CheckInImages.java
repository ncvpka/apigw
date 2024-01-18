/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author taink
 */
@Entity
@Table(name = "CHECK_IN_IMAGES")
@SqlResultSetMapping(name = "CHECKIN_IMAGES_MAPPING", classes = {@ConstructorResult(
        targetClass = CheckInImages.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "language", type = String.class),
        @ColumnResult(name = "checkInId", type = Integer.class),
        @ColumnResult(name = "urlImage", type = Integer.class),
        @ColumnResult(name = "thumbUrl", type = Date.class),
        @ColumnResult(name = "createTime", type = Date.class),
        @ColumnResult(name = "updateTime", type = Date.class),
        @ColumnResult(name = "createBy", type = String.class),
        @ColumnResult(name = "updateBy", type = String.class),

})})
public class CheckInImages implements Serializable {

    public static final String TABLE_NAME = "CHECK_IN_IMAGES";
    public static final String SEQ_NAME = "CHECK_IN_IMAGES_SEQ";
    public static final String CHECKIN_IMAGES_MAPPING = "CHECKIN_IMAGES_MAPPING";

    public CheckInImages() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_in_images_generator")
    @SequenceGenerator(name="check_in_images_generator", allocationSize=1, sequenceName = "CHECK_IN_IMAGES_SEQ")
    private Long id;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "CHECK_IN_ID")
    private Long checkInId;
    @Column(name = "URL_IMAGE")
    private String urlImage;

    @Column(name = "THUMB_URL")
    private String thumbUrl;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ORG_ID")
    private Long orgId;

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

    public Long getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(Long checkInId) {
        this.checkInId = checkInId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
