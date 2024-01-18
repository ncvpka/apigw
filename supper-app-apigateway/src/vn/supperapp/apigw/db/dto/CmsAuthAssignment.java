package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CMS_AUTH_ASSIGNMENT", schema = "SUPPERAPP", catalog = "")
public class CmsAuthAssignment {

    public static final String TABLE_NAME = "CMS_AUTH_ASSIGNMENT";
    public static final String SEQ_NAME = "CMS_AUTH_ASSIGNMENT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_auth_assignment_generator")
    @SequenceGenerator(name = "cms_auth_assignment_generator", allocationSize = 1, sequenceName = "cms_auth_assignment_SEQ")
    @Basic
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Basic
    @Column(name = "USER_ID")
    private String userId;
    @Basic
    @Column(name = "CREATED_AT")
    private Long createdAt;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsAuthAssignment that = (CmsAuthAssignment) o;

        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemName != null ? itemName.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
