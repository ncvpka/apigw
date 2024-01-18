package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "SHOP_INFO", schema = "SUPPERAPP", catalog = "")
public class ShopInfo {
    public static final String TABLE_NAME = "SHOP_INFO";
    public static final String SEQ_NAME = "SHOP_INFO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_info_generator")
    @SequenceGenerator(name = "shop_info_generator", allocationSize = 1, sequenceName = "shop_info_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SHOP_CODE")
    private String shopCode;
    @Basic
    @Column(name = "SHOP_NAME")
    private String shopName;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "PHONE")
    private String phone;
    @Basic
    @Column(name = "OWNER_SHOP")
    private String ownerShop;
    @Basic
    @Column(name = "AVATAR")
    private String avatar;
    @Basic
    @Column(name = "SHOP_TYPE")
    private String shopType;
    @Basic
    @Column(name = "SHOP_RATING")
    private Double shopRating;
    @Basic
    @Column(name = "SHOP_FOLLOWING")
    private Long shopFollowing;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "STATUS")
    private Long status;
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

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerShop() {
        return ownerShop;
    }

    public void setOwnerShop(String ownerShop) {
        this.ownerShop = ownerShop;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public Double getShopRating() {
        return shopRating;
    }

    public void setShopRating(Double shopRating) {
        this.shopRating = shopRating;
    }

    public Long getShopFollowing() {
        return shopFollowing;
    }

    public void setShopFollowing(Long shopFollowing) {
        this.shopFollowing = shopFollowing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

        ShopInfo that = (ShopInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shopCode != null ? !shopCode.equals(that.shopCode) : that.shopCode != null) return false;
        if (shopName != null ? !shopName.equals(that.shopName) : that.shopName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (ownerShop != null ? !ownerShop.equals(that.ownerShop) : that.ownerShop != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (shopType != null ? !shopType.equals(that.shopType) : that.shopType != null) return false;
        if (shopRating != null ? !shopRating.equals(that.shopRating) : that.shopRating != null) return false;
        if (shopFollowing != null ? !shopFollowing.equals(that.shopFollowing) : that.shopFollowing != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shopCode != null ? shopCode.hashCode() : 0);
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (ownerShop != null ? ownerShop.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (shopType != null ? shopType.hashCode() : 0);
        result = 31 * result + (shopRating != null ? shopRating.hashCode() : 0);
        result = 31 * result + (shopFollowing != null ? shopFollowing.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
