package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

@Entity
@Table(name = "CMS_USER_LOCKED", schema = "SUPPERAPP", catalog = "")
public class CmsUserLocked {
    public static final String TABLE_NAME = "CMS_USER_LOCKED";
    public static final String SEQ_NAME = "CMS_USER_LOCKED_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_user_locked_generator")
    @SequenceGenerator(name = "cms_user_locked_generator", allocationSize = 1, sequenceName = "cms_user_locked_SEQ")

    @Basic
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "USERNAME")
    private String username;
    @Basic
    @Column(name = "IP")
    private String ip;
    @Basic
    @Column(name = "CREATED_AT")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsUserLocked that = (CmsUserLocked) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
