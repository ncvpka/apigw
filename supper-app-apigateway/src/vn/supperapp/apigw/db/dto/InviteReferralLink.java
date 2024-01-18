package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INVITE_REFERRAL_LINK")
public class InviteReferralLink implements java.io.Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invite_referral_link_generator")
    @SequenceGenerator(name = "invite_referral_link_generator", allocationSize = 1, sequenceName = "INVITE_REFERRAL_LINK_SEQ")
    private Long id;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "REFERRAL_CODE")
    private String referralCode;

    @Column(name = "REFFERAL_LINK")
    private String referralLink;

    @Column(name = "REFFERAL_TYPE")
    private String referralType;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "DATA_LINK")
    private String dataLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public void setReferralLink(String referralLink) {
        this.referralLink = referralLink;
    }

    public String getReferralType() {
        return referralType;
    }

    public void setReferralType(String referralType) {
        this.referralType = referralType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDataLink() {
        return dataLink;
    }

    public void setDataLink(String dataLink) {
        this.dataLink = dataLink;
    }
}
