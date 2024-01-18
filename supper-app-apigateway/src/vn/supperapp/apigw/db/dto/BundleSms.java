package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "BUNDLE_SMS", schema = "SUPPERAPP", catalog = "")
public class BundleSms {
    public static final String TABLE_NAME = "BUNDLE_SMS";
    public static final String SEQ_NAME = "BUNDLE_SMS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bundle_sms_generator")
    @SequenceGenerator(name = "bundle_sms_generator", allocationSize = 1, sequenceName = "bundle_sms_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SMS_CODE")
    private String smsCode;
    @Basic
    @Column(name = "SMS_LIMIT")
    private Long smsLimit;
    @Basic
    @Column(name = "START_AT")
    private Date startAt;
    @Basic
    @Column(name = "END_AT")
    private Date endAt;
    @Basic
    @Column(name = "COST")
    private double cost;
    @Basic
    @Column(name = "IMAGE")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Long getSmsLimit() {
        return smsLimit;
    }

    public void setSmsLimit(Long smsLimit) {
        this.smsLimit = smsLimit;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BundleSms that = (BundleSms) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (smsCode != null ? !smsCode.equals(that.smsCode) : that.smsCode != null) return false;
        if (smsLimit != null ? !smsLimit.equals(that.smsLimit) : that.smsLimit != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (smsCode != null ? smsCode.hashCode() : 0);
        result = 31 * result + (smsLimit != null ? smsLimit.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
