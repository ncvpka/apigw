package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "SMS_LOG", schema = "SUPPERAPP", catalog = "")
public class SmsLog {
    public static final String TABLE_NAME = "SMS_LOG";
    public static final String SEQ_NAME = "SMS_LOG_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_log_generator")
    @SequenceGenerator(name = "shop_log_generator", allocationSize = 1, sequenceName = "shop_log_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SMS_SHOP_ID")
    private Long smsShopId;
    @Basic
    @Column(name = "SEND_TO")
    private String sendTo;
    @Basic
    @Column(name = "REMAINING_SMS")
    private Long remainingSms;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSmsShopId() {
        return smsShopId;
    }

    public void setSmsShopId(Long smsShopId) {
        this.smsShopId = smsShopId;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public Long getRemainingSms() {
        return remainingSms;
    }

    public void setRemainingSms(Long remainingSms) {
        this.remainingSms = remainingSms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsLog that = (SmsLog) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (smsShopId != null ? !smsShopId.equals(that.smsShopId) : that.smsShopId != null) return false;
        if (sendTo != null ? !sendTo.equals(that.sendTo) : that.sendTo != null) return false;
        if (remainingSms != null ? !remainingSms.equals(that.remainingSms) : that.remainingSms != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (smsShopId != null ? smsShopId.hashCode() : 0);
        result = 31 * result + (sendTo != null ? sendTo.hashCode() : 0);
        result = 31 * result + (remainingSms != null ? remainingSms.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }
}
