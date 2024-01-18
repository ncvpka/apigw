package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.restful.models.BaseResponse;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "PRODUCT", schema = "SUPPERAPP", catalog = "")
public class Product {
    public static final String TABLE_NAME = "PRODUCT";
    public static final String SEQ_NAME = "PRODUCT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", allocationSize = 1, sequenceName = "product_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Basic
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Basic
    @Column(name = "PRICE")
    private double price;
    @Basic
    @Column(name = "PRODUCT_CAT_ID")
    private Long productCatId;
    @Basic
    @Column(name = "ORIGIN")
    private Long origin;
    @Basic
    @Column(name = "TRADEMARK")
    private Long trademark;
    @Basic
    @Column(name = "UNIT")
    private Long unit;
    @Basic
    @Column(name = "PRODUCT_STOCK")
    private Long productStock;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "RATE")
    private Double rate;
    @Basic
    @Column(name = "IMAGE1")
    private String image1;
    @Basic
    @Column(name = "IMAGE2")
    private String image2;
    @Basic
    @Column(name = "IMAGE4")
    private String image4;
    @Basic
    @Column(name = "IMAGE5")
    private String image5;
    @Basic
    @Column(name = "IMAGE6")
    private String image6;
    @Basic
    @Column(name = "IS_SHOW")
    private Long isShow;
    @Basic
    @Column(name = "SOLD_QUANTITY")
    private Long soldQuantity;
    @Basic
    @Column(name = "SHOW_IN_CATEGORY")
    private Long showInCategory;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(Long productCatId) {
        this.productCatId = productCatId;
    }

    public Long getOrigin() {
        return origin;
    }

    public void setOrigin(Long origin) {
        this.origin = origin;
    }

    public Long getTrademark() {
        return trademark;
    }

    public void setTrademark(Long trademark) {
        this.trademark = trademark;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public Long getProductStock() {
        return productStock;
    }

    public void setProductStock(Long productStock) {
        this.productStock = productStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public Long getIsShow() {
        return isShow;
    }

    public void setIsShow(Long isShow) {
        this.isShow = isShow;
    }

    public Long getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Long getShowInCategory() {
        return showInCategory;
    }

    public void setShowInCategory(Long showInCategory) {
        this.showInCategory = showInCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productCatId != null ? !productCatId.equals(that.productCatId) : that.productCatId != null) return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        if (trademark != null ? !trademark.equals(that.trademark) : that.trademark != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (productStock != null ? !productStock.equals(that.productStock) : that.productStock != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        if (image1 != null ? !image1.equals(that.image1) : that.image1 != null) return false;
        if (image2 != null ? !image2.equals(that.image2) : that.image2 != null) return false;
        if (image4 != null ? !image4.equals(that.image4) : that.image4 != null) return false;
        if (image5 != null ? !image5.equals(that.image5) : that.image5 != null) return false;
        if (image6 != null ? !image6.equals(that.image6) : that.image6 != null) return false;
        if (isShow != null ? !isShow.equals(that.isShow) : that.isShow != null) return false;
        if (soldQuantity != null ? !soldQuantity.equals(that.soldQuantity) : that.soldQuantity != null) return false;
        if (showInCategory != null ? !showInCategory.equals(that.showInCategory) : that.showInCategory != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (productCatId != null ? productCatId.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (trademark != null ? trademark.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (productStock != null ? productStock.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (image1 != null ? image1.hashCode() : 0);
        result = 31 * result + (image2 != null ? image2.hashCode() : 0);
        result = 31 * result + (image4 != null ? image4.hashCode() : 0);
        result = 31 * result + (image5 != null ? image5.hashCode() : 0);
        result = 31 * result + (image6 != null ? image6.hashCode() : 0);
        result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
        result = 31 * result + (soldQuantity != null ? soldQuantity.hashCode() : 0);
        result = 31 * result + (showInCategory != null ? showInCategory.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


}
