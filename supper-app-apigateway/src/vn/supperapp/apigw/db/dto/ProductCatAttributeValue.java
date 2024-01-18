package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT_CAT_ATTRIBUTE_VALUE", schema = "SUPPERAPP", catalog = "")
public class ProductCatAttributeValue {
    public static final String TABLE_NAME = "PRODUCT_CAT_ATTRIBUTE_VALUE";
    public static final String SEQ_NAME = "PRODUCT_CAT_ATTRIBUTE_VALUE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_cat_attribute_value_generator")
    @SequenceGenerator(name = "product_cat_attribute_value_generator", allocationSize = 1, sequenceName = "product_cat_attribute_value_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_CAT_ATTRIBUTE_ID")
    private Long productCatAttributeId;
    @Basic
    @Column(name = "VALUE")
    private String value;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCatAttributeId() {
        return productCatAttributeId;
    }

    public void setProductCatAttributeId(Long productCatAttributeId) {
        this.productCatAttributeId = productCatAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCatAttributeValue that = (ProductCatAttributeValue) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productCatAttributeId != null ? !productCatAttributeId.equals(that.productCatAttributeId) : that.productCatAttributeId != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productCatAttributeId != null ? productCatAttributeId.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
