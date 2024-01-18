package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MEMBERSHIP", schema = "SUPPERAPP", catalog = "")
public class Membership {
    public static final String TABLE_NAME = "MEMBERSHIP";
    public static final String SEQ_NAME = "MEMBERSHIP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membership_generator")
    @SequenceGenerator(name = "membership_generator", allocationSize = 1, sequenceName = "membership_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "POINT_REQUIRED")
    private Long pointRequired;
    @Basic
    @Column(name = "POINT_EXPIRED")
    private Long pointExpired;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "RATE_BONUS")
    private Long rateBonus;

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

    public Long getPointRequired() {
        return pointRequired;
    }

    public void setPointRequired(Long pointRequired) {
        this.pointRequired = pointRequired;
    }

    public Long getPointExpired() {
        return pointExpired;
    }

    public void setPointExpired(Long pointExpired) {
        this.pointExpired = pointExpired;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRateBonus() {
        return rateBonus;
    }

    public void setRateBonus(Long rateBonus) {
        this.rateBonus = rateBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membership that = (Membership) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pointRequired != null ? !pointRequired.equals(that.pointRequired) : that.pointRequired != null)
            return false;
        if (pointExpired != null ? !pointExpired.equals(that.pointExpired) : that.pointExpired != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (rateBonus != null ? !rateBonus.equals(that.rateBonus) : that.rateBonus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pointRequired != null ? pointRequired.hashCode() : 0);
        result = 31 * result + (pointExpired != null ? pointExpired.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (rateBonus != null ? rateBonus.hashCode() : 0);
        return result;
    }
}
