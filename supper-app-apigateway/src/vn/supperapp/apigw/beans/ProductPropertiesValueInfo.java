package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.ProductProperties;
import vn.supperapp.apigw.db.dto.ProductPropertiesValue;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Date;

public class ProductPropertiesValueInfo extends ProductPropertiesValue {

    private Long id;
    private Long propertiesId;
    private String value;
    private Long parentId;
    private Date createAt;
    private Date updateAt;
    private String code;

    public ProductPropertiesValueInfo(Long id, Long propertiesId, String value, Long parentId, Date createAt, Date updateAt, String code) {
        this.id = id;
        this.propertiesId = propertiesId;
        this.value = value;
        this.parentId = parentId;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPropertiesId() {
        return propertiesId;
    }

    public  void setPropertiesId(Long propertiesId) {
        this.propertiesId = propertiesId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


