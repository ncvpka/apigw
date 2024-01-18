package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "BUNDLE_SHOP_KEYWORD", schema = "SUPPERAPP", catalog = "")
public class BundleShopKeyword {
    public static final String TABLE_NAME = "BUNDLE_SHOP_KEYWORD";
    public static final String SEQ_NAME = "BUNDLE_SHOP_KEYWORD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bundle_shop_keyword_generator")
    @SequenceGenerator(name = "bundle_shop_keyword_generator", allocationSize = 1, sequenceName = "bundle_shop_keyword_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "KEYWORD")
    private String keyword;
    @Basic
    @Column(name = "COST")
    private double cost;
    @Basic
    @Column(name = "START_AT")
    private Date startAt;
    @Basic
    @Column(name = "END_AT")
    private Date endAt;
    @Basic
    @Column(name = "CODE")
    private String code;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

        BundleShopKeyword that = (BundleShopKeyword) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (keyword != null ? !keyword.equals(that.keyword) : that.keyword != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
