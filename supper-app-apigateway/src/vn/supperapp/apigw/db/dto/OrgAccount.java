package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ORG_ACCOUNT", schema = "SUPPERAPP", catalog = "")
public class OrgAccount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "ORG_ID")
    private Long orgId;
    @Basic
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Basic
    @Column(name = "SUB_ID")
    private Long subId;
    @Basic
    @Column(name = "SUB_EXPIRED")
    private Date subExpired;
    @Basic
    @Column(name = "STATUS")
    private Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Date getSubExpired() {
        return subExpired;
    }

    public void setSubExpired(Date subExpired) {
        this.subExpired = subExpired;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrgAccount that = (OrgAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(orgId, that.orgId) && Objects.equals(accountId, that.accountId) && Objects.equals(subId, that.subId) && Objects.equals(subExpired, that.subExpired) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orgId, accountId, subId, subExpired, status);
    }
}
