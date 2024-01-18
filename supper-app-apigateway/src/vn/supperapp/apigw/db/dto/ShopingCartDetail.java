package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "SHOPING_CART_DETAIL", schema = "SUPPERAPP", catalog = "")
public class ShopingCartDetail {
    public static final String TABLE_NAME = "SHOPING_CART_DETAIL";
    public static final String SEQ_NAME = "SHOPING_CART_DETAIL_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_cart_detail_generator")
    @SequenceGenerator(name = "shopping_cart_detail_generator", allocationSize = 1, sequenceName = "shopping_cart_detail_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CART_ID")
    private Long cartId;
    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Basic
    @Column(name = "PRICE")
    private Long price;
    @Basic
    @Column(name = "QUANITY")
    private Long quanity;
    @Basic
    @Column(name = "PROMOTION")
    private Long promotion;
    @Basic
    @Column(name = "TOTAL_AMOUNT")
    private Long totalAmount;
    @Basic
    @Column(name = "TOTAL_AMOUNT_ATDISCOUNT")
    private Long totalAmountAtdiscount;
    @Basic
    @Column(name = "EXPLAIN")
    private String explain;
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

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuanity() {
        return quanity;
    }

    public void setQuanity(Long quanity) {
        this.quanity = quanity;
    }

    public Long getPromotion() {
        return promotion;
    }

    public void setPromotion(Long promotion) {
        this.promotion = promotion;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalAmountAtdiscount() {
        return totalAmountAtdiscount;
    }

    public void setTotalAmountAtdiscount(Long totalAmountAtdiscount) {
        this.totalAmountAtdiscount = totalAmountAtdiscount;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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

        ShopingCartDetail that = (ShopingCartDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cartId != null ? !cartId.equals(that.cartId) : that.cartId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (quanity != null ? !quanity.equals(that.quanity) : that.quanity != null) return false;
        if (promotion != null ? !promotion.equals(that.promotion) : that.promotion != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (totalAmountAtdiscount != null ? !totalAmountAtdiscount.equals(that.totalAmountAtdiscount) : that.totalAmountAtdiscount != null)
            return false;
        if (explain != null ? !explain.equals(that.explain) : that.explain != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cartId != null ? cartId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quanity != null ? quanity.hashCode() : 0);
        result = 31 * result + (promotion != null ? promotion.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (totalAmountAtdiscount != null ? totalAmountAtdiscount.hashCode() : 0);
        result = 31 * result + (explain != null ? explain.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
