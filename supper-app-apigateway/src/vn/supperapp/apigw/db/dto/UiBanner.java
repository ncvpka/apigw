package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "UI_BANNER", schema = "SUPPERAPP", catalog = "")
public class UiBanner {
    public static final String TABLE_NAME = "UI_BANNER";
    public static final String SEQ_NAME = "UI_BANNER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ui_banner_generator")
    @SequenceGenerator(name = "ui_banner_generator", allocationSize = 1, sequenceName = "ui_banner_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BUNDLE_BANNER_ID")
    private Long bundleBannerId;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "STATUS")
    private String status;
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
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "ID_REFERENCES")
    private Long idReferences;
    @Basic
    @Column(name = "URL")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBundleBannerId() {
        return bundleBannerId;
    }

    public void setBundleBannerId(Long bundleBannerId) {
        this.bundleBannerId = bundleBannerId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdReferences() {
        return idReferences;
    }

    public void setIdReferences(Long idReferences) {
        this.idReferences = idReferences;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiBanner that = (UiBanner) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bundleBannerId != null ? !bundleBannerId.equals(that.bundleBannerId) : that.bundleBannerId != null)
            return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (idReferences != null ? !idReferences.equals(that.idReferences) : that.idReferences != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bundleBannerId != null ? bundleBannerId.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idReferences != null ? idReferences.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
