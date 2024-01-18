package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CHALLENGE_USER", schema = "SUPPERAPP", catalog = "")
public class ChallengeUser {
    public static final String TABLE_NAME = "CHALLENGE_USER";
    public static final String SEQ_NAME = "CHALLENGE_USER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_user_generator")
    @SequenceGenerator(name = "challenge_user_generator", allocationSize = 1, sequenceName = "challenge_user_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CHALLENGE_ID")
    private Long challengeId;
    @Basic
    @Column(name = "LOYALTY_USER_ID")
    private Long loyaltyUserId;
    @Basic
    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getLoyaltyUserId() {
        return loyaltyUserId;
    }

    public void setLoyaltyUserId(Long loyaltyUserId) {
        this.loyaltyUserId = loyaltyUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChallengeUser that = (ChallengeUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (challengeId != null ? !challengeId.equals(that.challengeId) : that.challengeId != null) return false;
        if (loyaltyUserId != null ? !loyaltyUserId.equals(that.loyaltyUserId) : that.loyaltyUserId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (challengeId != null ? challengeId.hashCode() : 0);
        result = 31 * result + (loyaltyUserId != null ? loyaltyUserId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
