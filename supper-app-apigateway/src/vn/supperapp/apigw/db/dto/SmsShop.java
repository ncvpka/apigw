package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "SMS_SHOP", schema = "SUPPERAPP", catalog = "")
public class SmsShop {
    public static final String TABLE_NAME = "SMS_SHOP";
    public static final String SEQ_NAME = "SMS_SHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_shop_generator")
    @SequenceGenerator(name = "sms_shop_generator", allocationSize = 1, sequenceName = "sms_shop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BUNDLE_SMS_ID")
    private Long bundleSmsId;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "STATUS")
    private String status;
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

    public Long getBundleSmsId() {
        return bundleSmsId;
    }

    public void setBundleSmsId(Long bundleSmsId) {
        this.bundleSmsId = bundleSmsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

        SmsShop that = (SmsShop) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bundleSmsId != null ? !bundleSmsId.equals(that.bundleSmsId) : that.bundleSmsId != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bundleSmsId != null ? bundleSmsId.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
