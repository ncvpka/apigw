package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MILESTONE", schema = "SUPPERAPP", catalog = "")
public class Milestone {
    public static final String TABLE_NAME = "MILESTONE";
    public static final String SEQ_NAME = "MILESTONE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "milestone_generator")
    @SequenceGenerator(name = "milestone_generator", allocationSize = 1, sequenceName = "milestone_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "SUBTITLE")
    private String subtitle;
    @Basic
    @Column(name = "DEEP_LINK")
    private String deepLink;
    @Basic
    @Column(name = "VISIBLE")
    private String visible;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "CHALLENGE_ID")
    private Long challengeId;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;
    @Basic
    @Column(name = "STATUS")
    private Long status;
    @Basic
    @Column(name = "PAYMENT_AMOUNT")
    private Long paymentAmount;
    @Basic
    @Column(name = "POINT_RECEIVED")
    private Long pointReceived;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Long getPointReceived() {
        return pointReceived;
    }

    public void setPointReceived(Long pointReceived) {
        this.pointReceived = pointReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Milestone that = (Milestone) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (subtitle != null ? !subtitle.equals(that.subtitle) : that.subtitle != null) return false;
        if (deepLink != null ? !deepLink.equals(that.deepLink) : that.deepLink != null) return false;
        if (visible != null ? !visible.equals(that.visible) : that.visible != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (challengeId != null ? !challengeId.equals(that.challengeId) : that.challengeId != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (paymentAmount != null ? !paymentAmount.equals(that.paymentAmount) : that.paymentAmount != null)
            return false;
        if (pointReceived != null ? !pointReceived.equals(that.pointReceived) : that.pointReceived != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (deepLink != null ? deepLink.hashCode() : 0);
        result = 31 * result + (visible != null ? visible.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (challengeId != null ? challengeId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paymentAmount != null ? paymentAmount.hashCode() : 0);
        result = 31 * result + (pointReceived != null ? pointReceived.hashCode() : 0);
        return result;
    }
}
