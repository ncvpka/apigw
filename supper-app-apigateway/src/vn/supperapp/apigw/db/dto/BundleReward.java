package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "BUNDLE_REWARD", schema = "SUPPERAPP", catalog = "")
public class BundleReward {
    public static final String TABLE_NAME = "BUNDLE_REWARD";
    public static final String SEQ_NAME = "BUNDLE_REWARD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bundle_reward_generator")
    @SequenceGenerator(name = "bundle_reward_generator", allocationSize = 1, sequenceName = "bundle_reward_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BUNDLE_ID")
    private Long bundleId;
    @Basic
    @Column(name = "REWARD_ID")
    private Long rewardId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BundleReward that = (BundleReward) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bundleId != null ? !bundleId.equals(that.bundleId) : that.bundleId != null) return false;
        if (rewardId != null ? !rewardId.equals(that.rewardId) : that.rewardId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bundleId != null ? bundleId.hashCode() : 0);
        result = 31 * result + (rewardId != null ? rewardId.hashCode() : 0);
        return result;
    }
}
