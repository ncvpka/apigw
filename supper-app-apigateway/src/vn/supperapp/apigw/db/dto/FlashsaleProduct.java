package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

@Entity
@Table(name = "FLASHSALE_PRODUCT", schema = "SUPPERAPP", catalog = "")
public class FlashsaleProduct {
    public static final String TABLE_NAME = "FLASHSALE_PRODUCT";
    public static final String SEQ_NAME = "FLASHSALE_PRODUCT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashsale_product_generator")
    @SequenceGenerator(name = "flashsale_product_generator", allocationSize = 1, sequenceName = "flashsale_product_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Basic
    @Column(name = "FLASHSALE_ATTRIBUTE_ID")
    private Long flashsaleAttributeId;
    @Basic
    @Column(name = "DISCOUNT_VALUE")
    private double discountValue;
    @Basic
    @Column(name = "QUANITY_PROMOTION")
    private Long quanityPromotion;
    @Basic
    @Column(name = "ODER_LIMIT")
    private Long oderLimit;
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

    public Long getFlashsaleAttributeId() {
        return flashsaleAttributeId;
    }

    public void setFlashsaleAttributeId(Long flashsaleAttributeId) {
        this.flashsaleAttributeId = flashsaleAttributeId;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Long getQuanityPromotion() {
        return quanityPromotion;
    }

    public void setQuanityPromotion(Long quanityPromotion) {
        this.quanityPromotion = quanityPromotion;
    }

    public Long getOderLimit() {
        return oderLimit;
    }

    public void setOderLimit(Long oderLimit) {
        this.oderLimit = oderLimit;
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

        FlashsaleProduct that = (FlashsaleProduct) o;

        if (Double.compare(that.discountValue, discountValue) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (flashsaleAttributeId != null ? !flashsaleAttributeId.equals(that.flashsaleAttributeId) : that.flashsaleAttributeId != null)
            return false;
        if (quanityPromotion != null ? !quanityPromotion.equals(that.quanityPromotion) : that.quanityPromotion != null)
            return false;
        if (oderLimit != null ? !oderLimit.equals(that.oderLimit) : that.oderLimit != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (flashsaleAttributeId != null ? flashsaleAttributeId.hashCode() : 0);
        temp = Double.doubleToLongBits(discountValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (quanityPromotion != null ? quanityPromotion.hashCode() : 0);
        result = 31 * result + (oderLimit != null ? oderLimit.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
