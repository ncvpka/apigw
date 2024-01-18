package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "VOUCHERNATSHOP_PRODUCTCAT", schema = "SUPPERAPP", catalog = "")
public class VouchernatshopProductcat {
    public static final String TABLE_NAME = "VOUCHERNATSHOP_PRODUCTCAT";
    public static final String SEQ_NAME = "VOUCHERNATSHOP_PRODUCTCAT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vouchernatshop_productcat_generator")
    @SequenceGenerator(name = "vouchernatshop_productcat_generator", allocationSize = 1, sequenceName = "vouchernatshop_productcat_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "VOUCHER_NATSHOP_ID")
    private Long voucherNatshopId;
    @Basic
    @Column(name = "PRODUCT_CAT_ID")
    private Long productCatId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucherNatshopId() {
        return voucherNatshopId;
    }

    public void setVoucherNatshopId(Long voucherNatshopId) {
        this.voucherNatshopId = voucherNatshopId;
    }

    public Long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(Long productCatId) {
        this.productCatId = productCatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VouchernatshopProductcat that = (VouchernatshopProductcat) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (voucherNatshopId != null ? !voucherNatshopId.equals(that.voucherNatshopId) : that.voucherNatshopId != null)
            return false;
        if (productCatId != null ? !productCatId.equals(that.productCatId) : that.productCatId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (voucherNatshopId != null ? voucherNatshopId.hashCode() : 0);
        result = 31 * result + (productCatId != null ? productCatId.hashCode() : 0);
        return result;
    }
}
