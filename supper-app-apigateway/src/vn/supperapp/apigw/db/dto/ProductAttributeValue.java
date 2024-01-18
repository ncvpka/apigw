package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT_ATTRIBUTE_VALUE", schema = "SUPPERAPP", catalog = "")
public class ProductAttributeValue {
    public static final String TABLE_NAME = "PRODUCT_ATTRIBUTE_VALUE";
    public static final String SEQ_NAME = "PRODUCT_ATTRIBUTE_VALUE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_attribute_value_generator")
    @SequenceGenerator(name = "product_attribute_value_generator", allocationSize = 1, sequenceName = "product_attribute_value_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Basic
    @Column(name = "ATTRIBUTE_VALUE_ID")
    private Long attributeValueId;
    @Basic
    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(Long attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductAttributeValue that = (ProductAttributeValue) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (attributeValueId != null ? !attributeValueId.equals(that.attributeValueId) : that.attributeValueId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (attributeValueId != null ? attributeValueId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
