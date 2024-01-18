package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CHALLENGE_MEMBERSHIP", schema = "SUPPERAPP", catalog = "")
public class ChallengeMembership {
    public static final String TABLE_NAME = "CHALLENGE_MEMBERSHIP";
    public static final String SEQ_NAME = "CHALLENGE_MEMBERSHIP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_membership_generator")
    @SequenceGenerator(name = "challenge_membership_generator", allocationSize = 1, sequenceName = "challenge_membership_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CHALLENGE_ID")
    private Long challengeId;
    @Basic
    @Column(name = "MEMBERSHIP_ID")
    private Long membershipId;

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

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChallengeMembership that = (ChallengeMembership) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (challengeId != null ? !challengeId.equals(that.challengeId) : that.challengeId != null) return false;
        if (membershipId != null ? !membershipId.equals(that.membershipId) : that.membershipId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (challengeId != null ? challengeId.hashCode() : 0);
        result = 31 * result + (membershipId != null ? membershipId.hashCode() : 0);
        return result;
    }
}
