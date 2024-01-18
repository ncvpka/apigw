package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "VOUCHER_PROGRAMNATSHOP", schema = "SUPPERAPP", catalog = "")
public class VoucherProgramnatshop {
    public static final String TABLE_NAME = "VOUCHER_PROGRAMNATSHOP";
    public static final String SEQ_NAME = "VOUCHER_PROGRAMNATSHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucher_programnatshop_generator")
    @SequenceGenerator(name = "voucher_programnatshop_generator", allocationSize = 1, sequenceName = "voucher_programnatshop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "VOUCHER_ID")
    private Long voucherId;
    @Basic
    @Column(name = "VOUCHER_NATSHOP_ID")
    private Long voucherNatshopId;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getVoucherNatshopId() {
        return voucherNatshopId;
    }

    public void setVoucherNatshopId(Long voucherNatshopId) {
        this.voucherNatshopId = voucherNatshopId;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherProgramnatshop that = (VoucherProgramnatshop) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (voucherId != null ? !voucherId.equals(that.voucherId) : that.voucherId != null) return false;
        if (voucherNatshopId != null ? !voucherNatshopId.equals(that.voucherNatshopId) : that.voucherNatshopId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (voucherId != null ? voucherId.hashCode() : 0);
        result = 31 * result + (voucherNatshopId != null ? voucherNatshopId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
