package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.ProductProperties;
import java.util.Date;

public class ProductPropertiesInfo extends ProductProperties  {

    private Long id;
    private String name;
    private String image;
    private Date createAt;
    private Date updateAt;
    private String code;
    private Long propertiesId;
    private String value;


    public ProductPropertiesInfo(Long id, String name, String image, Date createAt, Date updateAt, String code, Long propertiesId, String value) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.code = code;
        this.propertiesId = propertiesId;
        this.value = value;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
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

    public Long getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(Long propertiesId) {
        this.propertiesId = propertiesId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
