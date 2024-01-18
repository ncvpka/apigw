package vn.supperapp.apigw.db.dto;


import org.hibernate.Session;
import vn.supperapp.apigw.beans.ProductPropertiesInfo;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_PROPERTIES", schema = "SUPPERAPP", catalog = "")
@SqlResultSetMapping( name = "PRODUCT_PROPERTIES_MAPPING", classes = {@ConstructorResult(
        targetClass = ProductPropertiesInfo.class, columns = {
                @ColumnResult(name = "id", type = Long.class),
                @ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "image", type = String.class),
                @ColumnResult(name = "createAt", type = Date.class),
                @ColumnResult(name = "updateAt", type = Date.class),
                @ColumnResult(name = "code", type = String.class),
                @ColumnResult(name = "propertiesId", type = Long.class),
                @ColumnResult(name = "value", type = String.class)
})})

public class ProductProperties implements java.io.Serializable {
    public static final String TABLE_NAME = "PRODUCT_PROPERTIES";
    public static final String SEQ_NAME = "PRODUCT_PROPERTIES_SEQ";
    public static final String PRODUCT_PROPERTIES_MAPPING = "PRODUCT_PROPERTIES_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_properties_generator")
    @SequenceGenerator(name = "product_properties_generator", allocationSize = 1, sequenceName = "PRODUCT_PROPERTIES_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "TYPE")
    private Long type;
    @Basic
    @Column(name = "CODE")
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void save(Session session, ProductProperties productProperties) {
    }

}
