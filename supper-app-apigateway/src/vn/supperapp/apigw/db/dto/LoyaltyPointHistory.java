package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "LOYALTY_POINT_HISTORY", schema = "SUPPERAPP", catalog = "")
public class LoyaltyPointHistory {

    public static final String TABLE_NAME = "LOYALTY_POINT_HISTORY";
    public static final String SEQ_NAME = "LOYALTY_POINT_HISTORY_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_point_history_generator")
    @SequenceGenerator(name = "loyalty_point_history_generator", allocationSize = 1, sequenceName = "loyalty_point_history_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "LOYALTY_USER")
    private Long loyaltyUser;
    @Basic
    @Column(name = "ID_REFERENCES")
    private Long idReferences;
    @Basic
    @Column(name = "TYPE_USE")
    private String typeUse;
    @Basic
    @Column(name = "POINT")
    private Long point;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "DATE_EXPIRED")
    private Date dateExpired;
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

    public Long getLoyaltyUser() {
        return loyaltyUser;
    }

    public void setLoyaltyUser(Long loyaltyUser) {
        this.loyaltyUser = loyaltyUser;
    }

    public Long getIdReferences() {
        return idReferences;
    }

    public void setIdReferences(Long idReferences) {
        this.idReferences = idReferences;
    }

    public String getTypeUse() {
        return typeUse;
    }

    public void setTypeUse(String typeUse) {
        this.typeUse = typeUse;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
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

        LoyaltyPointHistory that = (LoyaltyPointHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (loyaltyUser != null ? !loyaltyUser.equals(that.loyaltyUser) : that.loyaltyUser != null) return false;
        if (idReferences != null ? !idReferences.equals(that.idReferences) : that.idReferences != null) return false;
        if (typeUse != null ? !typeUse.equals(that.typeUse) : that.typeUse != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (dateExpired != null ? !dateExpired.equals(that.dateExpired) : that.dateExpired != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loyaltyUser != null ? loyaltyUser.hashCode() : 0);
        result = 31 * result + (idReferences != null ? idReferences.hashCode() : 0);
        result = 31 * result + (typeUse != null ? typeUse.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (dateExpired != null ? dateExpired.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
