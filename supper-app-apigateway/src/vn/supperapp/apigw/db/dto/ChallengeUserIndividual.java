package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CHALLENGE_USER_INDIVIDUAL", schema = "SUPPERAPP", catalog = "")
public class ChallengeUserIndividual {
    public static final String TABLE_NAME = "CHALLENGE_USER_INDIVIDUAL";
    public static final String SEQ_NAME = "CHALLENGE_USER_INDIVIDUAL_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_user_invididual_generator")
    @SequenceGenerator(name = "challenge_user_invididual_generator", allocationSize = 1, sequenceName = "challenge_user_invididual_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CHALLENGE_ID")
    private Long challengeId;
    @Basic
    @Column(name = "USER_INDIVIDUAL_ID")
    private Long userIndividualId;

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

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getUserIndividualId() {
        return userIndividualId;
    }

    public void setUserIndividualId(Long userIndividualId) {
        this.userIndividualId = userIndividualId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChallengeUserIndividual that = (ChallengeUserIndividual) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (challengeId != null ? !challengeId.equals(that.challengeId) : that.challengeId != null) return false;
        if (userIndividualId != null ? !userIndividualId.equals(that.userIndividualId) : that.userIndividualId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (challengeId != null ? challengeId.hashCode() : 0);
        result = 31 * result + (userIndividualId != null ? userIndividualId.hashCode() : 0);
        return result;
    }
}
