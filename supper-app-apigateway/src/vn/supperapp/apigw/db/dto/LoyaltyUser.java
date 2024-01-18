package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "LOYALTY_USER", schema = "SUPPERAPP", catalog = "")
public class LoyaltyUser {

    public static final String TABLE_NAME = "LOYALTY_USER";
    public static final String SEQ_NAME = "LOYALTY_USER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_user_generator")
    @SequenceGenerator(name = "loyalty_user_generator", allocationSize = 1, sequenceName = "loyalty_user_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "USER_ID")
    private Long userId;
    @Basic
    @Column(name = "MEMBERSHIP_ID")
    private Long membershipId;
    @Basic
    @Column(name = "CURRENT_POINT")
    private Long currentPoint;
    @Basic
    @Column(name = "ACCUMULATED_POINT")
    private Long accumulatedPoint;
    @Basic
    @Column(name = "TIME_EXPIRED")
    private Date timeExpired;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private String updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Long getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Long currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Long getAccumulatedPoint() {
        return accumulatedPoint;
    }

    public void setAccumulatedPoint(Long accumulatedPoint) {
        this.accumulatedPoint = accumulatedPoint;
    }

    public Date getTimeExpired() {
        return timeExpired;
    }

    public void setTimeExpired(Date timeExpired) {
        this.timeExpired = timeExpired;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyUser that = (LoyaltyUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (membershipId != null ? !membershipId.equals(that.membershipId) : that.membershipId != null) return false;
        if (currentPoint != null ? !currentPoint.equals(that.currentPoint) : that.currentPoint != null) return false;
        if (accumulatedPoint != null ? !accumulatedPoint.equals(that.accumulatedPoint) : that.accumulatedPoint != null)
            return false;
        if (timeExpired != null ? !timeExpired.equals(that.timeExpired) : that.timeExpired != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (membershipId != null ? membershipId.hashCode() : 0);
        result = 31 * result + (currentPoint != null ? currentPoint.hashCode() : 0);
        result = 31 * result + (accumulatedPoint != null ? accumulatedPoint.hashCode() : 0);
        result = 31 * result + (timeExpired != null ? timeExpired.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
