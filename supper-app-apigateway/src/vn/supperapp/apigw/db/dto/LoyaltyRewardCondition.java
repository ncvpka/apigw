package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "LOYALTY_REWARD_CONDITION", schema = "SUPPERAPP", catalog = "")
public class LoyaltyRewardCondition {

    public static final String TABLE_NAME = "LOYALTY_REWARD_CONDITION";
    public static final String SEQ_NAME = "LOYALTY_REWARD_CONDITION_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_reward_condition_generator")
    @SequenceGenerator(name = "loyalty_reward_condition_generator", allocationSize = 1, sequenceName = "loyalty_reward_condition_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "LOYALTY_USER_ID")
    private Long loyaltyUserId;
    @Basic
    @Column(name = "ITEM_GENCODE")
    private String itemGencode;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;
    @Basic
    @Column(name = "REWARD_ID")
    private Long rewardId;
    @Basic
    @Column(name = "MEMBERSHIP_REQUIRED")
    private Long membershipRequired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLoyaltyUserId() {
        return loyaltyUserId;
    }

    public void setLoyaltyUserId(Long loyaltyUserId) {
        this.loyaltyUserId = loyaltyUserId;
    }

    public String getItemGencode() {
        return itemGencode;
    }

    public void setItemGencode(String itemGencode) {
        this.itemGencode = itemGencode;
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

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getMembershipRequired() {
        return membershipRequired;
    }

    public void setMembershipRequired(Long membershipRequired) {
        this.membershipRequired = membershipRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyRewardCondition that = (LoyaltyRewardCondition) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (loyaltyUserId != null ? !loyaltyUserId.equals(that.loyaltyUserId) : that.loyaltyUserId != null)
            return false;
        if (itemGencode != null ? !itemGencode.equals(that.itemGencode) : that.itemGencode != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (rewardId != null ? !rewardId.equals(that.rewardId) : that.rewardId != null) return false;
        if (membershipRequired != null ? !membershipRequired.equals(that.membershipRequired) : that.membershipRequired != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (loyaltyUserId != null ? loyaltyUserId.hashCode() : 0);
        result = 31 * result + (itemGencode != null ? itemGencode.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (rewardId != null ? rewardId.hashCode() : 0);
        result = 31 * result + (membershipRequired != null ? membershipRequired.hashCode() : 0);
        return result;
    }
}
