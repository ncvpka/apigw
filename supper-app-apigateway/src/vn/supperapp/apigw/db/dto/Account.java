/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author TruongLe
 */
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
    
    public static final String TABLE_NAME = "ACCOUNT";
    public static final String SEQ_NAME = "ACCOUNT_SEQ";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name="account_generator", allocationSize=1, sequenceName = "ACCOUNT_SEQ")
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;
    @Column(name = "GENDER", nullable = false)
    private Long gender = 0L;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday = new Date();
    @Column(name = "PAN")
    private String pan;
    @Column(name = "PASS", nullable = false)
    private String pass;
    @Column(name = "STATUS", nullable = false)
    private Long status = 0L;
    @Column(name = "ACTIVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activedDate = new Date();
    @Column(name = "LAST_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin = new Date();
    @Column(name = "CARRIED_ACCOUNT_ID", nullable = false)
    private Long carriedAccountId = 0L;
    @Column(name = "TIER", nullable = false)
    private Long tier = 0L;
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber = 0L;
    @Column(name = "PAPER_TYPE")
    private Long paperType = 0L;
    @Column(name = "PAPER_NUMBER")
    private String paperNumber;

    @Transient
    private List<Organization> listOrg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getActivedDate() {
        return activedDate;
    }

    public void setActivedDate(Date activedDate) {
        this.activedDate = activedDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getCarriedAccountId() {
        return carriedAccountId;
    }

    public void setCarriedAccountId(Long carriedAccountId) {
        this.carriedAccountId = carriedAccountId;
    }

    public Long getTier() {
        return tier;
    }

    public void setTier(Long tier) {
        this.tier = tier;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getPaperType() {
        return paperType;
    }

    public void setPaperType(Long paperType) {
        this.paperType = paperType;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public List<Organization> getListOrg() {
        return listOrg;
    }

    public void setListOrg(List<Organization> listOrg) {
        this.listOrg = listOrg;
    }

}
