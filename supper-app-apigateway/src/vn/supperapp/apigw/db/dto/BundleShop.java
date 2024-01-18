package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "BUNDLE_SHOP", schema = "SUPPERAPP", catalog = "")
public class BundleShop {
    public static final String TABLE_NAME = "BUNDLE_SHOP";
    public static final String SEQ_NAME = "BUNDLE_SHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bundle_shop_generator")
    @SequenceGenerator(name = "bundle_shop_generator", allocationSize = 1, sequenceName = "bundle_shop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BUNDLE_TYPE")
    private String bundleType;
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

    public String getBundleType() {
        return bundleType;
    }

    public void setBundleType(String bundleType) {
        this.bundleType = bundleType;
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

        BundleShop that = (BundleShop) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bundleType != null ? !bundleType.equals(that.bundleType) : that.bundleType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bundleType != null ? bundleType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
