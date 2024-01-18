package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "BUNDLE_BANNER", schema = "SUPPERAPP", catalog = "")
public class BundleBanner {
    public static final String TABLE_NAME = "BUNDLE_BANNER";
    public static final String SEQ_NAME = "BUNDLE_BANNER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bundle_banner_generator")
    @SequenceGenerator(name = "bundle_banner_generator", allocationSize = 1, sequenceName = "bundle_banner_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "LOCALTION")
    private String localtion;
    @Basic
    @Column(name = "COST")
    private double cost;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "BANNER")
    private String banner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BundleBanner that = (BundleBanner) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (localtion != null ? !localtion.equals(that.localtion) : that.localtion != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (banner != null ? !banner.equals(that.banner) : that.banner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (localtion != null ? localtion.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (banner != null ? banner.hashCode() : 0);
        return result;
    }
}
