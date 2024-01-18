package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

@Entity
@Table(name = "CMS_USER", schema = "SUPPERAPP", catalog = "")
public class CmsUser {
    public static final String TABLE_NAME = "CMS_USER";
    public static final String SEQ_NAME = "CMS_USER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_user_generator")
    @SequenceGenerator(name = "cms_user_generator", allocationSize = 1, sequenceName = "cms_user_SEQ")
    @Column(name = "ID", nullable = false)
    private short id;
    @Basic
    @Column(name = "USERNAME")
    private String username;
    @Basic
    @Column(name = "FULLNAME")
    private String fullname;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "AUTH_KEY")
    private String authKey;
    @Basic
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;
    @Basic
    @Column(name = "PASSWORD_RESET_TOKEN")
    private String passwordResetToken;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "STATUS")
    private Byte status;
    @Basic
    @Column(name = "CREATED_AT")
    private String createdAt;
    @Basic
    @Column(name = "UPDATED_AT")
    private String updatedAt;
    @Basic
    @Column(name = "LAST_TIME_LOGIN")
    private String lastTimeLogin;
    @Basic
    @Column(name = "IS_FIRST_LOGIN")
    private Byte isFirstLogin;
    @Basic
    @Column(name = "CP_ID")
    private String cpId;
    @Basic
    @Column(name = "NUM_LOGIN_FAIL")
    private String numLoginFail;
    @Basic
    @Column(name = "PARTNER_IDS")
    private String partnerIds;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(String lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public Byte getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(Byte isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getNumLoginFail() {
        return numLoginFail;
    }

    public void setNumLoginFail(String numLoginFail) {
        this.numLoginFail = numLoginFail;
    }

    public String getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(String partnerIds) {
        this.partnerIds = partnerIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsUser that = (CmsUser) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (authKey != null ? !authKey.equals(that.authKey) : that.authKey != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;
        if (passwordResetToken != null ? !passwordResetToken.equals(that.passwordResetToken) : that.passwordResetToken != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (lastTimeLogin != null ? !lastTimeLogin.equals(that.lastTimeLogin) : that.lastTimeLogin != null)
            return false;
        if (isFirstLogin != null ? !isFirstLogin.equals(that.isFirstLogin) : that.isFirstLogin != null) return false;
        if (cpId != null ? !cpId.equals(that.cpId) : that.cpId != null) return false;
        if (numLoginFail != null ? !numLoginFail.equals(that.numLoginFail) : that.numLoginFail != null) return false;
        if (partnerIds != null ? !partnerIds.equals(that.partnerIds) : that.partnerIds != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (authKey != null ? authKey.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (passwordResetToken != null ? passwordResetToken.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (lastTimeLogin != null ? lastTimeLogin.hashCode() : 0);
        result = 31 * result + (isFirstLogin != null ? isFirstLogin.hashCode() : 0);
        result = 31 * result + (cpId != null ? cpId.hashCode() : 0);
        result = 31 * result + (numLoginFail != null ? numLoginFail.hashCode() : 0);
        result = 31 * result + (partnerIds != null ? partnerIds.hashCode() : 0);
        return result;
    }
}
