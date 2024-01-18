package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "PRIZES_STRUCTURE", schema = "SUPPERAPP", catalog = "")
public class PrizesStructure {
    public static final String TABLE_NAME = "PRIZES_STRUCTURE";
    public static final String SEQ_NAME = "PRIZES_STRUCTURE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prizes_structure_generator")
    @SequenceGenerator(name = "prizes_structure_generator", allocationSize = 1, sequenceName = "prizes_structure_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "GAME_ID")
    private Long gameId;
    @Basic
    @Column(name = "PRIZE_ID")
    private Long prizeId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "QUANTITY")
    private Long quantity;
    @Basic
    @Column(name = "EXIST")
    private Long exist;
    @Basic
    @Column(name = "VALUE")
    private Double value;
    @Basic
    @Column(name = "AMOUNT")
    private Double amount;
    @Basic
    @Column(name = "LEVELS")
    private Long levels;
    @Basic
    @Column(name = "ORIGINAL")
    private Long original;
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "ALERT")
    private String alert;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getExist() {
        return exist;
    }

    public void setExist(Long exist) {
        this.exist = exist;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getLevels() {
        return levels;
    }

    public void setLevels(Long levels) {
        this.levels = levels;
    }

    public Long getOriginal() {
        return original;
    }

    public void setOriginal(Long original) {
        this.original = original;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrizesStructure that = (PrizesStructure) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (gameId != null ? !gameId.equals(that.gameId) : that.gameId != null) return false;
        if (prizeId != null ? !prizeId.equals(that.prizeId) : that.prizeId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (exist != null ? !exist.equals(that.exist) : that.exist != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (levels != null ? !levels.equals(that.levels) : that.levels != null) return false;
        if (original != null ? !original.equals(that.original) : that.original != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (alert != null ? !alert.equals(that.alert) : that.alert != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gameId != null ? gameId.hashCode() : 0);
        result = 31 * result + (prizeId != null ? prizeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (exist != null ? exist.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (levels != null ? levels.hashCode() : 0);
        result = 31 * result + (original != null ? original.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (alert != null ? alert.hashCode() : 0);
        return result;
    }
}
