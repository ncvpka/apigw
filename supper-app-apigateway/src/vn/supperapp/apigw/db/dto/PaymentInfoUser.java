/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author taink
 */
@Entity
@Table(name = "PAYMENT_INFO_USER")
public class PaymentInfoUser implements Serializable {
    
    public static final String TABLE_NAME = "PAYMENT_INFO_USER";
    public static final String SEQ_NAME = "PAYMENT_INFO_USER_SEQ";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_info_user_generator")
    @SequenceGenerator(name="payment_info_user_generator", allocationSize=1, sequenceName = "PAYMENT_INFO_USER_SEQ")
    private Long id;
    @Column(name = "ID_USER", nullable = false)
    private Long idUser;
    @Column(name = "BENEFICIARY", nullable = false)
    private String beneficiary;
    @Column(name = "BANK")
    private Long bank;
    @Column(name = "BANK_NUMBER")
    private String bankNumber;
    @Column(name = "BANK_BRANCH", nullable = false)
    private String bankBranch;
    @Column(name = "ORG_ID")
    private Long orgId;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Long getBank() {
        return bank;
    }

    public void setBank(Long bank) {
        this.bank = bank;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
