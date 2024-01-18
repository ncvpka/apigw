package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "LOYALTY_POINT_EXCHANGED", schema = "SUPPERAPP", catalog = "")
public class LoyaltyPointExchanged {

    public static final String TABLE_NAME = "LOYALTY_POINT_EXCHANGED";
    public static final String SEQ_NAME = "LOYALTY_POINT_EXCHANGED_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_point_exchanged_generator")
    @SequenceGenerator(name = "loyalty_point_exchanged_generator", allocationSize = 1, sequenceName = "loyalty_point_exchanged_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "RATE_TO_MONEY")
    private Long rateToMoney;
    @Basic
    @Column(name = "RATE_FROM_MONEY")
    private Long rateFromMoney;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;
    @Basic
    @Column(name = "VALID_FROM")
    private Timestamp validFrom;
    @Basic
    @Column(name = "VALID_TO")
    private Timestamp validTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRateToMoney() {
        return rateToMoney;
    }

    public void setRateToMoney(Long rateToMoney) {
        this.rateToMoney = rateToMoney;
    }

    public Long getRateFromMoney() {
        return rateFromMoney;
    }

    public void setRateFromMoney(Long rateFromMoney) {
        this.rateFromMoney = rateFromMoney;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public Timestamp getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    public Timestamp getValidTo() {
        return validTo;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyPointExchanged that = (LoyaltyPointExchanged) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rateToMoney != null ? !rateToMoney.equals(that.rateToMoney) : that.rateToMoney != null) return false;
        if (rateFromMoney != null ? !rateFromMoney.equals(that.rateFromMoney) : that.rateFromMoney != null)
            return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (validFrom != null ? !validFrom.equals(that.validFrom) : that.validFrom != null) return false;
        if (validTo != null ? !validTo.equals(that.validTo) : that.validTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rateToMoney != null ? rateToMoney.hashCode() : 0);
        result = 31 * result + (rateFromMoney != null ? rateFromMoney.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validTo != null ? validTo.hashCode() : 0);
        return result;
    }
}
