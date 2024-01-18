package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "FLASHSALE_NATSHOP", schema = "SUPPERAPP", catalog = "")
public class FlashsaleNatshop {
    public static final String TABLE_NAME = "FLASHSALE_NATSHOP";
    public static final String SEQ_NAME = "FLASHSALE_NATSHOP_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashsale_natshop_generator")
    @SequenceGenerator(name = "flashsale_natshop_generator", allocationSize = 1, sequenceName = "flashsale_natshop_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PROGRAM_NAME")
    private String programName;
    @Basic
    @Column(name = "REGISTRATION_STARTAT")
    private Date registrationStartat;
    @Basic
    @Column(name = "REGISTRATION_ENDAT")
    private Date registrationEndat;
    @Basic
    @Column(name = "PROGRAM_STARTAT")
    private Date programStartat;
    @Basic
    @Column(name = "PROGRAM_ENDAT")
    private Date programEndat;
    @Basic
    @Column(name = "MAX_SHOP_REGIS")
    private Long maxShopRegis;
    @Basic
    @Column(name = "MIN_PRODUCT_PRICE")
    private double minProductPrice;
    @Basic
    @Column(name = "MAX_PRODUCT_PRICE")
    private double maxProductPrice;
    @Basic
    @Column(name = "MIN_DISCOUNT")
    private double minDiscount;
    @Basic
    @Column(name = "MAX_DISCOUNT")
    private double maxDiscount;
    @Basic
    @Column(name = "BANNER")
    private String banner;
    @Basic
    @Column(name = "PRODUCT_CAT")
    private String productCat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Date getRegistrationStartat() {
        return registrationStartat;
    }

    public void setRegistrationStartat(Date registrationStartat) {
        this.registrationStartat = registrationStartat;
    }

    public Date getRegistrationEndat() {
        return registrationEndat;
    }

    public void setRegistrationEndat(Date registrationEndat) {
        this.registrationEndat = registrationEndat;
    }

    public Date getProgramStartat() {
        return programStartat;
    }

    public void setProgramStartat(Date programStartat) {
        this.programStartat = programStartat;
    }

    public Date getProgramEndat() {
        return programEndat;
    }

    public void setProgramEndat(Date programEndat) {
        this.programEndat = programEndat;
    }

    public Long getMaxShopRegis() {
        return maxShopRegis;
    }

    public void setMaxShopRegis(Long maxShopRegis) {
        this.maxShopRegis = maxShopRegis;
    }

    public double getMinProductPrice() {
        return minProductPrice;
    }

    public void setMinProductPrice(double minProductPrice) {
        this.minProductPrice = minProductPrice;
    }

    public double getMaxProductPrice() {
        return maxProductPrice;
    }

    public void setMaxProductPrice(double maxProductPrice) {
        this.maxProductPrice = maxProductPrice;
    }

    public double getMinDiscount() {
        return minDiscount;
    }

    public void setMinDiscount(double minDiscount) {
        this.minDiscount = minDiscount;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlashsaleNatshop that = (FlashsaleNatshop) o;

        if (Double.compare(that.minProductPrice, minProductPrice) != 0) return false;
        if (Double.compare(that.maxProductPrice, maxProductPrice) != 0) return false;
        if (Double.compare(that.minDiscount, minDiscount) != 0) return false;
        if (Double.compare(that.maxDiscount, maxDiscount) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (programName != null ? !programName.equals(that.programName) : that.programName != null) return false;
        if (registrationStartat != null ? !registrationStartat.equals(that.registrationStartat) : that.registrationStartat != null)
            return false;
        if (registrationEndat != null ? !registrationEndat.equals(that.registrationEndat) : that.registrationEndat != null)
            return false;
        if (programStartat != null ? !programStartat.equals(that.programStartat) : that.programStartat != null)
            return false;
        if (programEndat != null ? !programEndat.equals(that.programEndat) : that.programEndat != null) return false;
        if (maxShopRegis != null ? !maxShopRegis.equals(that.maxShopRegis) : that.maxShopRegis != null) return false;
        if (banner != null ? !banner.equals(that.banner) : that.banner != null) return false;
        if (productCat != null ? !productCat.equals(that.productCat) : that.productCat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (programName != null ? programName.hashCode() : 0);
        result = 31 * result + (registrationStartat != null ? registrationStartat.hashCode() : 0);
        result = 31 * result + (registrationEndat != null ? registrationEndat.hashCode() : 0);
        result = 31 * result + (programStartat != null ? programStartat.hashCode() : 0);
        result = 31 * result + (programEndat != null ? programEndat.hashCode() : 0);
        result = 31 * result + (maxShopRegis != null ? maxShopRegis.hashCode() : 0);
        temp = Double.doubleToLongBits(minProductPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxProductPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxDiscount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (banner != null ? banner.hashCode() : 0);
        result = 31 * result + (productCat != null ? productCat.hashCode() : 0);
        return result;
    }
}
