package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "LOYALTY_REWARD_CATEGORY", schema = "SUPPERAPP", catalog = "")
public class LoyaltyRewardCategory {

    public static final String TABLE_NAME = "LOYALTY_REWARD_CATEGORY";
    public static final String SEQ_NAME = "LOYALTY_REWARD_CATEGORY_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyalty_reward_category_generator")
    @SequenceGenerator(name = "loyalty_reward_category_generator", allocationSize = 1, sequenceName = "loyalty_reward_category_SEQ")
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "STATUS")
    private Boolean status;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "ICON")
    private String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyaltyRewardCategory that = (LoyaltyRewardCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }
}
