package vn.supperapp.apigw.db.dto;

import liquibase.structure.core.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INVITE_LOG")
public class InviteLog implements java.io.Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invite_log_generator")
    @SequenceGenerator(name = "invite_log_generator", allocationSize = 1, sequenceName = "INVITE_LOG_SEQ")
    private Long id;

    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;

    @Column(name = "INVITED_NUMBER")
    private String invitedNumber;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "STATUS")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getInvitedNumber() {
        return invitedNumber;
    }

    public void setInvitedNumber(String invitedNumber) {
        this.invitedNumber = invitedNumber;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
