package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "BONUS_INVITE", schema = "SUPPERAPP", catalog = "")
public class BonusInvite {

    public static final String TABLE_NAME = "BONUS_INVITE";
    public static final String SEQ_NAME = "BONUS_INVITE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bonus_invite_generator")
    @SequenceGenerator(name = "bonus_invite_generator", allocationSize = 1, sequenceName = "bonus_invite_SEQ")

    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "INVITER")
    private String inviter;
    @Basic
    @Column(name = "CUSTOMER")
    private String customer;
    @Basic
    @Column(name = "CUS_DEVICE")
    private String cusDevice;
    @Basic
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @Basic
    @Column(name = "NOTE")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCusDevice() {
        return cusDevice;
    }

    public void setCusDevice(String cusDevice) {
        this.cusDevice = cusDevice;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BonusInvite that = (BonusInvite) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (inviter != null ? !inviter.equals(that.inviter) : that.inviter != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (cusDevice != null ? !cusDevice.equals(that.cusDevice) : that.cusDevice != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (inviter != null ? inviter.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (cusDevice != null ? cusDevice.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
