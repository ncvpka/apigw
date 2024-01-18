package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "VOUCHER_ATTRIBUTE", schema = "SUPPERAPP", catalog = "")
public class VoucherAttribute {
    public static final String TABLE_NAME = "VOUCHER_ATTRIBUTE";
    public static final String SEQ_NAME = "VOUCHER_ATTRIBUTE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucher_attribute_generator")
    @SequenceGenerator(name = "voucher_attribute_generator", allocationSize = 1, sequenceName = "voucher_attribute_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "VOUCHER_ID")
    private Long voucherId;
    @Basic
    @Column(name = "START_AT")
    private Date startAt;
    @Basic
    @Column(name = "END_AT")
    private Date endAt;
    @Basic
    @Column(name = "BUYER_TYPE")
    private String buyerType;
    @Basic
    @Column(name = "DISCOUNT_VALUE")
    private double discountValue;
    @Basic
    @Column(name = "MAX_DISCOUNT")
    private Double maxDiscount;
    @Basic
    @Column(name = "TYPE_DISCOUNT")
    private String typeDiscount;
    @Basic
    @Column(name = "MIN_ODER_VALUE")
    private double minOderValue;
    @Basic
    @Column(name = "MAX_USAGE")
    private Long maxUsage;

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

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getTypeDiscount() {
        return typeDiscount;
    }

    public void setTypeDiscount(String typeDiscount) {
        this.typeDiscount = typeDiscount;
    }

    public double getMinOderValue() {
        return minOderValue;
    }

    public void setMinOderValue(double minOderValue) {
        this.minOderValue = minOderValue;
    }

    public Long getMaxUsage() {
        return maxUsage;
    }

    public void setMaxUsage(Long maxUsage) {
        this.maxUsage = maxUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherAttribute that = (VoucherAttribute) o;

        if (Double.compare(that.discountValue, discountValue) != 0) return false;
        if (Double.compare(that.minOderValue, minOderValue) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (voucherId != null ? !voucherId.equals(that.voucherId) : that.voucherId != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;
        if (buyerType != null ? !buyerType.equals(that.buyerType) : that.buyerType != null) return false;
        if (maxDiscount != null ? !maxDiscount.equals(that.maxDiscount) : that.maxDiscount != null) return false;
        if (typeDiscount != null ? !typeDiscount.equals(that.typeDiscount) : that.typeDiscount != null) return false;
        if (maxUsage != null ? !maxUsage.equals(that.maxUsage) : that.maxUsage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (voucherId != null ? voucherId.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (buyerType != null ? buyerType.hashCode() : 0);
        temp = Double.doubleToLongBits(discountValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (maxDiscount != null ? maxDiscount.hashCode() : 0);
        result = 31 * result + (typeDiscount != null ? typeDiscount.hashCode() : 0);
        temp = Double.doubleToLongBits(minOderValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (maxUsage != null ? maxUsage.hashCode() : 0);
        return result;
    }
}
