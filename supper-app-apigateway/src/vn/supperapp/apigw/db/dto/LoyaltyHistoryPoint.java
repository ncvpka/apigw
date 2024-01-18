package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "LOYALTY_HISTORY_POINT", schema = "SUPPERAPP", catalog = "")
public class LoyaltyHistoryPoint {

    public static final String TABLE_NAME = "LOYALTY_HISTORY_POINT";
    public static final String SEQ_NAME = "LOYALTY_HISTORY_POINT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_history_point_generator")
    @SequenceGenerator(name = "loyalty_history_point_generator", allocationSize = 1, sequenceName = "loyalty_history_point_SEQ")
   
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "AMOUNT")
    private Long amount;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "TIME_USED")
    private Timestamp timeUsed;
    @Basic
    @Column(name = "LOYALTY_USER_ID")
    private Long loyaltyUserId;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(Timestamp timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Long getLoyaltyUserId() {
        return loyaltyUserId;
    }

    public void setLoyaltyUserId(Long loyaltyUserId) {
        this.loyaltyUserId = loyaltyUserId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyHistoryPoint that = (LoyaltyHistoryPoint) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (timeUsed != null ? !timeUsed.equals(that.timeUsed) : that.timeUsed != null) return false;
        if (loyaltyUserId != null ? !loyaltyUserId.equals(that.loyaltyUserId) : that.loyaltyUserId != null)
            return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (timeUsed != null ? timeUsed.hashCode() : 0);
        result = 31 * result + (loyaltyUserId != null ? loyaltyUserId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
