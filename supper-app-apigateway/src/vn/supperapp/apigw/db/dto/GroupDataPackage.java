package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.GroupDataPackageInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GROUP_DATA_PACKAGE")
@SqlResultSetMapping(name = "GROUP_DATA_PACKAGE_MAPPING", classes = {@ConstructorResult(
        targetClass = GroupDataPackageInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "code", type = String.class),
        @ColumnResult(name = "title", type = String.class),
        @ColumnResult(name = "subTitle", type = String.class),
        @ColumnResult(name = "description", type = String.class),
        @ColumnResult(name = "value", type = Double.class),
        @ColumnResult(name = "name", type = String.class),
        @ColumnResult(name = "shortName", type = String.class),
        @ColumnResult(name = "groupDataId", type = Long.class),
        @ColumnResult(name = "url", type = String.class),
})})

public class GroupDataPackage implements java.io.Serializable {
    public static final String GROUP_DATA_PACKAGE_MAPPING = "GROUP_DATA_PACKAGE_MAPPING";

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUB_TITLE")
    private String subTitle;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private double value;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "GROUP_DATA_ID")
    private Long groupDataId;

    @Column(name = "URL")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getGroupDataId() {
        return groupDataId;
    }

    public void setGroupDataId(Long groupDataId) {
        this.groupDataId = groupDataId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

