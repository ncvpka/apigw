package vn.supperapp.apigw.db.dto;
// Generated Feb 28, 2018 5:41:20 PM by Hibernate Tools 4.3.1

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORGANIZATION")
public class Organization implements java.io.Serializable {
    
    public static final String TABLE_NAME = "ORGANIZATION";
    public static final String SEQ_NAME = "ORGANIZATION_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_generator")
    @SequenceGenerator(name = "organization_generator", allocationSize = 1, sequenceName = "ORGANIZATION_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "STATUS")
    private Long status;
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

    @Transient
    private List<Branch> listBranch;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public List<Branch> getListBranch() {
        return listBranch;
    }

    public void setListBranch(List<Branch> listBranch) {
        this.listBranch = listBranch;
    }
}
