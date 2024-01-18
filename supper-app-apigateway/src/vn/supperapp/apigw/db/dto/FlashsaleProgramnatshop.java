package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "FLASHSALE_PROGRAMNATSHOP", schema = "SUPPERAPP", catalog = "")
public class FlashsaleProgramnatshop {
    public static final String TABLE_NAME = "FLASHSALE_PROGRAMNATSHOP";
    public static final String SEQ_NAME = "FLASHSALE_PROGRAMNATSHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashsale_programnatshop_generator")
    @SequenceGenerator(name = "flashsale_programnatshop_generator", allocationSize = 1, sequenceName = "flashsale_programnatshop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "FLASHSALE_ID")
    private Long flashsaleId;
    @Basic
    @Column(name = "FLASHSALE_NATSHOP_ID")
    private Long flashsaleNatshopId;
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

    public Long getFlashsaleId() {
        return flashsaleId;
    }

    public void setFlashsaleId(Long flashsaleId) {
        this.flashsaleId = flashsaleId;
    }

    public Long getFlashsaleNatshopId() {
        return flashsaleNatshopId;
    }

    public void setFlashsaleNatshopId(Long flashsaleNatshopId) {
        this.flashsaleNatshopId = flashsaleNatshopId;
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

        FlashsaleProgramnatshop that = (FlashsaleProgramnatshop) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (flashsaleId != null ? !flashsaleId.equals(that.flashsaleId) : that.flashsaleId != null) return false;
        if (flashsaleNatshopId != null ? !flashsaleNatshopId.equals(that.flashsaleNatshopId) : that.flashsaleNatshopId != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (flashsaleId != null ? flashsaleId.hashCode() : 0);
        result = 31 * result + (flashsaleNatshopId != null ? flashsaleNatshopId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
