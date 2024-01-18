package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "CHALLENGE_REWARD", schema = "SUPPERAPP", catalog = "")
public class ChallengeReward {
    public static final String TABLE_NAME = "CHALLENGE_REWARD";
    public static final String SEQ_NAME = "CHALLENGE_REWARD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_reward_generator")
    @SequenceGenerator(name = "challenge_reward_generator", allocationSize = 1, sequenceName = "challenge_reward_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "POINT_AMOUNT")
    private Long pointAmount;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "CHALLENGE_ID")
    private Long challengeId;
    @Basic
    @Column(name = "REWARD_ID")
    private Long rewardId;
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

    public Long getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(Long pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
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

        ChallengeReward that = (ChallengeReward) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pointAmount != null ? !pointAmount.equals(that.pointAmount) : that.pointAmount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (challengeId != null ? !challengeId.equals(that.challengeId) : that.challengeId != null) return false;
        if (rewardId != null ? !rewardId.equals(that.rewardId) : that.rewardId != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pointAmount != null ? pointAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (challengeId != null ? challengeId.hashCode() : 0);
        result = 31 * result + (rewardId != null ? rewardId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
