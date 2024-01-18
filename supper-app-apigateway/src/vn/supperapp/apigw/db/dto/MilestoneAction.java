package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "MILESTONE_ACTION", schema = "SUPPERAPP", catalog = "")
public class MilestoneAction {
    public static final String TABLE_NAME = "MILESTONE_ACTION";
    public static final String SEQ_NAME = "MILESTONE_ACTION_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "milestone_action_generator")
    @SequenceGenerator(name = "milestone_action_generator", allocationSize = 1, sequenceName = "milestone_action_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "ACTION_TYPE")
    private String actionType;
    @Basic
    @Column(name = "MILESTONE_ID")
    private Long milestoneId;
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

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
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

        MilestoneAction that = (MilestoneAction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (actionType != null ? !actionType.equals(that.actionType) : that.actionType != null) return false;
        if (milestoneId != null ? !milestoneId.equals(that.milestoneId) : that.milestoneId != null) return false;
        if (loyaltyUserId != null ? !loyaltyUserId.equals(that.loyaltyUserId) : that.loyaltyUserId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (milestoneId != null ? milestoneId.hashCode() : 0);
        result = 31 * result + (loyaltyUserId != null ? loyaltyUserId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
