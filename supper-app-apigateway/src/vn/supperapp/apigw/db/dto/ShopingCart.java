package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "SHOPING_CART", schema = "SUPPERAPP", catalog = "")
public class ShopingCart {
    public static final String TABLE_NAME = "SHOPING_CART";
    public static final String SEQ_NAME = "SHOPING_CART_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoping_cart_generator")
    @SequenceGenerator(name = "shoping_cart_generator", allocationSize = 1, sequenceName = "shoping_cart_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Basic
    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;
    @Basic
    @Column(name = "DISCOUNT")
    private Long discount;
    @Basic
    @Column(name = "TOTAL_AMOUNT_ATDISCOUNT")
    private Double totalAmountAtdiscount;
    @Basic
    @Column(name = "VOUCHER")
    private Long voucher;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "CUSTOMER_NOTE")
    private String customerNote;
    @Basic
    @Column(name = "HANDLING_NOTES")
    private String handlingNotes;
    @Basic
    @Column(name = "DATE_ORDER")
    private Date dateOrder;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Double getTotalAmountAtdiscount() {
        return totalAmountAtdiscount;
    }

    public void setTotalAmountAtdiscount(Double totalAmountAtdiscount) {
        this.totalAmountAtdiscount = totalAmountAtdiscount;
    }

    public Long getVoucher() {
        return voucher;
    }

    public void setVoucher(Long voucher) {
        this.voucher = voucher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public String getHandlingNotes() {
        return handlingNotes;
    }

    public void setHandlingNotes(String handlingNotes) {
        this.handlingNotes = handlingNotes;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopingCart that = (ShopingCart) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (totalAmountAtdiscount != null ? !totalAmountAtdiscount.equals(that.totalAmountAtdiscount) : that.totalAmountAtdiscount != null)
            return false;
        if (voucher != null ? !voucher.equals(that.voucher) : that.voucher != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (customerNote != null ? !customerNote.equals(that.customerNote) : that.customerNote != null) return false;
        if (handlingNotes != null ? !handlingNotes.equals(that.handlingNotes) : that.handlingNotes != null)
            return false;
        if (dateOrder != null ? !dateOrder.equals(that.dateOrder) : that.dateOrder != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (totalAmountAtdiscount != null ? totalAmountAtdiscount.hashCode() : 0);
        result = 31 * result + (voucher != null ? voucher.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (customerNote != null ? customerNote.hashCode() : 0);
        result = 31 * result + (handlingNotes != null ? handlingNotes.hashCode() : 0);
        result = 31 * result + (dateOrder != null ? dateOrder.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
