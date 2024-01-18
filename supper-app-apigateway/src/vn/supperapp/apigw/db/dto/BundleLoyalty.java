package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "BUNDLE_LOYALTY", schema = "SUPPERAPP", catalog = "")
public class BundleLoyalty {
    public static final String TABLE_NAME = "BUNDLE_LOYALTY";
    public static final String SEQ_NAME = "BUNDLE_LOYALTY_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bundle_loyalty_generator")
    @SequenceGenerator(name = "bundle_loyalty_generator", allocationSize = 1, sequenceName = "bundle_loyalty_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "DESCRIPTIONS")
    private String descriptions;
    @Basic
    @Column(name = "PRICE")
    private BigInteger price;
    @Basic
    @Column(name = "TIME_APPLIED")
    private Timestamp timeApplied;
    @Basic
    @Column(name = "BENEFIT_TERMS")
    private String benefitTerms;
    @Basic
    @Column(name = "INSURANCE_TERMS")
    private String insuranceTerms;
    @Basic
    @Column(name = "PACKAGE_TERMS")
    private String packageTerms;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Timestamp getTimeApplied() {
        return timeApplied;
    }

    public void setTimeApplied(Timestamp timeApplied) {
        this.timeApplied = timeApplied;
    }

    public String getBenefitTerms() {
        return benefitTerms;
    }

    public void setBenefitTerms(String benefitTerms) {
        this.benefitTerms = benefitTerms;
    }

    public String getInsuranceTerms() {
        return insuranceTerms;
    }

    public void setInsuranceTerms(String insuranceTerms) {
        this.insuranceTerms = insuranceTerms;
    }

    public String getPackageTerms() {
        return packageTerms;
    }

    public void setPackageTerms(String packageTerms) {
        this.packageTerms = packageTerms;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BundleLoyalty that = (BundleLoyalty) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (descriptions != null ? !descriptions.equals(that.descriptions) : that.descriptions != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (timeApplied != null ? !timeApplied.equals(that.timeApplied) : that.timeApplied != null) return false;
        if (benefitTerms != null ? !benefitTerms.equals(that.benefitTerms) : that.benefitTerms != null) return false;
        if (insuranceTerms != null ? !insuranceTerms.equals(that.insuranceTerms) : that.insuranceTerms != null)
            return false;
        if (packageTerms != null ? !packageTerms.equals(that.packageTerms) : that.packageTerms != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (descriptions != null ? descriptions.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (timeApplied != null ? timeApplied.hashCode() : 0);
        result = 31 * result + (benefitTerms != null ? benefitTerms.hashCode() : 0);
        result = 31 * result + (insuranceTerms != null ? insuranceTerms.hashCode() : 0);
        result = 31 * result + (packageTerms != null ? packageTerms.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
