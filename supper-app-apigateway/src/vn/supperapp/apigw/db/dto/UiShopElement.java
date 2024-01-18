package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "UI_SHOP_ELEMENT", schema = "SUPPERAPP", catalog = "")
public class UiShopElement {
    public static final String TABLE_NAME = "UI_SHOP_ELEMENT";
    public static final String SEQ_NAME = "UI_SHOP_ELEMENT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ui_shop_element_generator")
    @SequenceGenerator(name = "ui_shop_element_generator", allocationSize = 1, sequenceName = "ui_shop_element_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "TOP_PRODUCT")
    private Long topProduct;

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

    public Long getTopProduct() {
        return topProduct;
    }

    public void setTopProduct(Long topProduct) {
        this.topProduct = topProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiShopElement that = (UiShopElement) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (topProduct != null ? !topProduct.equals(that.topProduct) : that.topProduct != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (topProduct != null ? topProduct.hashCode() : 0);
        return result;
    }
}
