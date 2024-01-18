package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "UI_SHOP_KEYWORD", schema = "SUPPERAPP", catalog = "")
public class UiShopKeyword {
    public static final String TABLE_NAME = "UI_SHOP_KEYWORD";
    public static final String SEQ_NAME = "UI_SHOP_KEYWORD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ui_shop_keyword_generator")
    @SequenceGenerator(name = "ui_shop_keyword_generator", allocationSize = 1, sequenceName = "ui_shop_keyword_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BUNDLE_SHOP_KEYWORD_ID")
    private Long bundleShopKeywordId;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "STATUS")
    private Long status;
    @Basic
    @Column(name = "START_AT")
    private Date startAt;
    @Basic
    @Column(name = "END_AT")
    private Date endAt;
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

    public Long getBundleShopKeywordId() {
        return bundleShopKeywordId;
    }

    public void setBundleShopKeywordId(Long bundleShopKeywordId) {
        this.bundleShopKeywordId = bundleShopKeywordId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

        UiShopKeyword that = (UiShopKeyword) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bundleShopKeywordId != null ? !bundleShopKeywordId.equals(that.bundleShopKeywordId) : that.bundleShopKeywordId != null)
            return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bundleShopKeywordId != null ? bundleShopKeywordId.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
