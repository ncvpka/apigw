package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "LOYALTY_REWARD", schema = "SUPPERAPP", catalog = "")
public class LoyaltyReward {

    public static final String TABLE_NAME = "LOYALTY_REWARD";
    public static final String SEQ_NAME = "LOYALTY_REWARD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_reward_generator")
    @SequenceGenerator(name = "loyalty_reward_generator", allocationSize = 1, sequenceName = "loyalty_reward_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Basic
    @Column(name = "START_TIME")
    private Date startTime;
    @Basic
    @Column(name = "END_TIME")
    private Date endTime;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "POINT_VALUE")
    private Long pointValue;
    @Basic
    @Column(name = "CONDITION")
    private String condition;
    @Basic
    @Column(name = "POINT_DISCOUNT")
    private Long pointDiscount;
    @Basic
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPointValue() {
        return pointValue;
    }

    public void setPointValue(Long pointValue) {
        this.pointValue = pointValue;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getPointDiscount() {
        return pointDiscount;
    }

    public void setPointDiscount(Long pointDiscount) {
        this.pointDiscount = pointDiscount;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyReward that = (LoyaltyReward) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (pointValue != null ? !pointValue.equals(that.pointValue) : that.pointValue != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;
        if (pointDiscount != null ? !pointDiscount.equals(that.pointDiscount) : that.pointDiscount != null)
            return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pointValue != null ? pointValue.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (pointDiscount != null ? pointDiscount.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }
}
