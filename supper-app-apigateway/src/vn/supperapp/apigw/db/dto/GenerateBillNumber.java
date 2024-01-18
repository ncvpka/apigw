package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "GENERATE_BILL_NUMBER", schema = "SUPPERAPP", catalog = "")
public class GenerateBillNumber {
    public static final String TABLE_NAME = "GENERATE_BILL_NUMBER";
    public static final String SEQ_NAME = "GENERATE_BILL_NUMBER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generate_bill_number_generator")
    @SequenceGenerator(name = "generate_bill_number_generator", allocationSize = 1, sequenceName = "generate_bill_number_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BILL_NUMBER")
    private String billNumber;
    @Basic
    @Column(name = "ORG_ID")
    private Long orgId;
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
    @Column(name = "BILL_CODE")
    private String billCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenerateBillNumber that = (GenerateBillNumber) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (billNumber != null ? !billNumber.equals(that.billNumber) : that.billNumber != null) return false;
        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (billCode != null ? !billCode.equals(that.billCode) : that.billCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (billNumber != null ? billNumber.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (billCode != null ? billCode.hashCode() : 0);
        return result;
    }
}
