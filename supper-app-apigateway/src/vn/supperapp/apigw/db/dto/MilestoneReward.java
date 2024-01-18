package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "MILESTONE_REWARD", schema = "SUPPERAPP", catalog = "")
public class MilestoneReward {
    public static final String TABLE_NAME = "MILESTONE_REWARD";
    public static final String SEQ_NAME = "MILESTONE_REWARD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "milestone_reward_generator")
    @SequenceGenerator(name = "milestone_reward_generator", allocationSize = 1, sequenceName = "milestone_reward_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "REWARD_ID")
    private Long rewardId;
    @Basic
    @Column(name = "MILESTONE_ID")
    private Long milestoneId;

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

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MilestoneReward that = (MilestoneReward) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (rewardId != null ? !rewardId.equals(that.rewardId) : that.rewardId != null) return false;
        if (milestoneId != null ? !milestoneId.equals(that.milestoneId) : that.milestoneId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rewardId != null ? rewardId.hashCode() : 0);
        result = 31 * result + (milestoneId != null ? milestoneId.hashCode() : 0);
        return result;
    }
}
