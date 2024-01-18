package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "FLASHSALE_ATTRIBUTE", schema = "SUPPERAPP", catalog = "")
public class FlashsaleAttribute {
    public static final String TABLE_NAME = "FLASHSALE_ATTRIBUTE";
    public static final String SEQ_NAME = "FLASHSALE_ATTRIBUTE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashsale_attribute_generator")
    @SequenceGenerator(name = "flashsale_attribute_generator", allocationSize = 1, sequenceName = "flashsale_attribute_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "FLASH_SALE_ID")
    private Long flashSaleId;
    @Basic
    @Column(name = "START_AT")
    private Date startAt;
    @Basic
    @Column(name = "END_AT")
    private Date endAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlashSaleId() {
        return flashSaleId;
    }

    public void setFlashSaleId(Long flashSaleId) {
        this.flashSaleId = flashSaleId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlashsaleAttribute that = (FlashsaleAttribute) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (flashSaleId != null ? !flashSaleId.equals(that.flashSaleId) : that.flashSaleId != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (flashSaleId != null ? flashSaleId.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        return result;
    }
}
