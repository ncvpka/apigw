package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT_CAT_ATTRIBUTE", schema = "SUPPERAPP", catalog = "")
public class ProductCatAttribute {
    public static final String TABLE_NAME = "PRODUCT_CAT_ATTRIBUTE";
    public static final String SEQ_NAME = "PRODUCT_CAT_ATTRIBUTE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_cat_attribute_generator")
    @SequenceGenerator(name = "product_cat_attribute_generator", allocationSize = 1, sequenceName = "product_cat_attribute_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_CAT_ID")
    private Long productCatId;
    @Basic
    @Column(name = "NAME_ATTRIBUTE")
    private String nameAttribute;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(Long productCatId) {
        this.productCatId = productCatId;
    }

    public String getNameAttribute() {
        return nameAttribute;
    }

    public void setNameAttribute(String nameAttribute) {
        this.nameAttribute = nameAttribute;
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

        ProductCatAttribute that = (ProductCatAttribute) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productCatId != null ? !productCatId.equals(that.productCatId) : that.productCatId != null) return false;
        if (nameAttribute != null ? !nameAttribute.equals(that.nameAttribute) : that.nameAttribute != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productCatId != null ? productCatId.hashCode() : 0);
        result = 31 * result + (nameAttribute != null ? nameAttribute.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
