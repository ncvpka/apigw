package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "UI_SHOP", schema = "SUPPERAPP", catalog = "")
public class UiShop {
    public static final String TABLE_NAME = "UI_SHOP";
    public static final String SEQ_NAME = "UI_SHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ui_shop_generator")
    @SequenceGenerator(name = "ui_shop_generator", allocationSize = 1, sequenceName = "ui_shop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "BANNER")
    private String banner;
    @Basic
    @Column(name = "LOCATION")
    private String location;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "DEEP_LINK")
    private String deepLink;

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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiShop that = (UiShop) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (banner != null ? !banner.equals(that.banner) : that.banner != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (deepLink != null ? !deepLink.equals(that.deepLink) : that.deepLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (banner != null ? banner.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deepLink != null ? deepLink.hashCode() : 0);
        return result;
    }
}
