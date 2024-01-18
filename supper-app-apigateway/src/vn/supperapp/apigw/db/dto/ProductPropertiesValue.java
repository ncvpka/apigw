package vn.supperapp.apigw.db.dto;


import org.hibernate.Session;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_PROPERTIES_VALUE", schema = "SUPPERAPP", catalog = "")
public class ProductPropertiesValue  {

    public static final String TABLE_NAME = "PRODUCT_PROPERTIES_VALUE";
    public static final String SEQ_NAME = "PRODUCT_PROPERTIES_VALUE_SEQ";




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_properties_value_generator")
    @SequenceGenerator(name = "product_properties_value_generator", allocationSize = 1, sequenceName = "product_properties_value_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PROPERTIES_ID")
    private Long propertiesId;
    @Basic
    @Column(name = "VALUE")
    private String value;
    @Basic
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "CODE")
    private String code;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void save(Session session, ProductPropertiesValue productPropertiesValue) {
    }


    public void setName(String name) {

    }

    public void setParentId(String parentId) {

    }

}
