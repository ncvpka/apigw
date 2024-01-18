package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "LOYALTY_REWARD_REQUIRED", schema = "SUPPERAPP", catalog = "")
public class LoyaltyRewardRequired {

    public static final String TABLE_NAME = "LOYALTY_REWARD_REQUIRED";
    public static final String SEQ_NAME = "LOYALTY_REWARD_REQUIRED_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_reward_required_generator")
    @SequenceGenerator(name = "loyalty_reward_required_generator", allocationSize = 1, sequenceName = "loyalty_reward_required_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "REWARD_ID")
    private Long rewardId;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "ID_REFERENCES")
    private Long idReferences;
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

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
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

        LoyaltyRewardRequired that = (LoyaltyRewardRequired) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rewardId != null ? !rewardId.equals(that.rewardId) : that.rewardId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (idReferences != null ? !idReferences.equals(that.idReferences) : that.idReferences != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rewardId != null ? rewardId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idReferences != null ? idReferences.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
