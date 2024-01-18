package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CMS_AUTH_ITEM", schema = "SUPPERAPP", catalog = "")
public class CmsAuthItem {
    public static final String TABLE_NAME = "CMS_AUTH_ITEM";
    public static final String SEQ_NAME = "CMS_AUTH_ITEM_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_auth_item_generator")
    @SequenceGenerator(name = "cms_auth_item_generator", allocationSize = 1, sequenceName = "cms_auth_item_SEQ")
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "TYPE")
    private Long type;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "RULE_NAME")
    private String ruleName;
    @Basic
    @Column(name = "DATA")
    private String data;
    @Basic
    @Column(name = "CREATED_AT")
    private Long createdAt;
    @Basic
    @Column(name = "UPDATED_AT")
    private Long updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsAuthItem that = (CmsAuthItem) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (ruleName != null ? !ruleName.equals(that.ruleName) : that.ruleName != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
