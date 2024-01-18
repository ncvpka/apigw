package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "USER_SHOP", schema = "SUPPERAPP", catalog = "")
public class UserShop {
    public static final String TABLE_NAME = "USER_SHOP";
    public static final String SEQ_NAME = "USER_SHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_shop_generator")
    @SequenceGenerator(name = "user_shop_generator", allocationSize = 1, sequenceName = "user_shop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Basic
    @Column(name = "RATE")
    private Long rate;
    @Basic
    @Column(name = "FOLLOW")
    private String follow;
    @Basic
    @Column(name = "DATE_PLACED")
    private Date datePlaced;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserShop that = (UserShop) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        if (follow != null ? !follow.equals(that.follow) : that.follow != null) return false;
        if (datePlaced != null ? !datePlaced.equals(that.datePlaced) : that.datePlaced != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (follow != null ? follow.hashCode() : 0);
        result = 31 * result + (datePlaced != null ? datePlaced.hashCode() : 0);
        return result;
    }
}
