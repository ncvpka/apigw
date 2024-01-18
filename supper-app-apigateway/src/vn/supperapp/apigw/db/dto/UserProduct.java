package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "USER_PRODUCT", schema = "SUPPERAPP", catalog = "")
public class UserProduct {
    public static final String TABLE_NAME = "USER_PRODUCT";
    public static final String SEQ_NAME = "USER_PRODUCT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_product_generator")
    @SequenceGenerator(name = "user_product_generator", allocationSize = 1, sequenceName = "user_product_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Basic
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Basic
    @Column(name = "FAVOURITE")
    private String favourite;
    @Basic
    @Column(name = "DATE_PLACED")
    private Date datePlaced;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
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

        UserProduct that = (UserProduct) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (favourite != null ? !favourite.equals(that.favourite) : that.favourite != null) return false;
        if (datePlaced != null ? !datePlaced.equals(that.datePlaced) : that.datePlaced != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (favourite != null ? favourite.hashCode() : 0);
        result = 31 * result + (datePlaced != null ? datePlaced.hashCode() : 0);
        return result;
    }
}
