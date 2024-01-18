package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "VOUCHER_PRODUCT", schema = "SUPPERAPP", catalog = "")
public class VoucherProduct {
    public static final String TABLE_NAME = "VOUCHER_PRODUCT";
    public static final String SEQ_NAME = "VOUCHER_PRODUCT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucher_product_generator")
    @SequenceGenerator(name = "voucher_product_generator", allocationSize = 1, sequenceName = "voucher_product_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "VOUCHER_ID")
    private Long voucherId;
    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherProduct that = (VoucherProduct) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (voucherId != null ? !voucherId.equals(that.voucherId) : that.voucherId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (voucherId != null ? voucherId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
