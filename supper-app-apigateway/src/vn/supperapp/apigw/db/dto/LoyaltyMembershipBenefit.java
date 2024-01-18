package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "LOYALTY_MEMBERSHIP_BENEFIT", schema = "SUPPERAPP", catalog = "")
public class LoyaltyMembershipBenefit {

    public static final String TABLE_NAME = "LOYALTY_MEMBERSHIP_BENEFIT";
    public static final String SEQ_NAME = "LOYALTY_MEMBERSHIP_BENEFIT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_membership_benefit_generator")
    @SequenceGenerator(name = "loyalty_membership_benefit_generator", allocationSize = 1, sequenceName = "loyalty_membership_benefit_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "MEMBERSHIP_ID")
    private Long membershipId;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "DISCOUNT_RATE")
    private Long discountRate;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "TYPE")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Long discountRate) {
        this.discountRate = discountRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyMembershipBenefit that = (LoyaltyMembershipBenefit) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (membershipId != null ? !membershipId.equals(that.membershipId) : that.membershipId != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (discountRate != null ? !discountRate.equals(that.discountRate) : that.discountRate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (membershipId != null ? membershipId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (discountRate != null ? discountRate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
