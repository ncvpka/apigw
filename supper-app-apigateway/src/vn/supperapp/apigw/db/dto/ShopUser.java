package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "SHOP_USER", schema = "SUPPERAPP", catalog = "")
public class ShopUser {
    public static final String TABLE_NAME = "SHOP_USER";
    public static final String SEQ_NAME = "SHOP_USER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_user_generator")
    @SequenceGenerator(name = "shop_user_generator", allocationSize = 1, sequenceName = "shop_user_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopUser that = (ShopUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        return result;
    }
}
