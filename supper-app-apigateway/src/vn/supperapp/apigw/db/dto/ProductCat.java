package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "PRODUCT_CAT", schema = "SUPPERAPP", catalog = "")
public class ProductCat {
    public static final String TABLE_NAME = "PRODUCT_CAT";
    public static final String SEQ_NAME = "PRODUCT_CAT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_cat_generator")
    @SequenceGenerator(name = "product_cat_generator", allocationSize = 1, sequenceName = "product_cat_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_CAT_NAME")
    private String productCatName;
    @Basic
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "PROMOTION")
    private String promotion;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "IMAGE")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCat that = (ProductCat) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productCatName != null ? !productCatName.equals(that.productCatName) : that.productCatName != null)
            return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (promotion != null ? !promotion.equals(that.promotion) : that.promotion != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productCatName != null ? productCatName.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (promotion != null ? promotion.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
