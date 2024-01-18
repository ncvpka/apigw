package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "WHITE_LIST", schema = "SUPPERAPP", catalog = "")
public class WhiteList {

    public static final String TABLE_NAME = "WHITE_LIST";
    public static final String SEQ_NAME = "WHITE_LIST_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "white_list_generator")
    @SequenceGenerator(name = "white_list_generator", allocationSize = 1, sequenceName = "white_list_SEQ")
    
    @Basic
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "BRANCH")
    private String branch;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "MSISDN")
    private Long msisdn;
    @Basic
    @Column(name = "TYPE")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WhiteList that = (WhiteList) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (branch != null ? !branch.equals(that.branch) : that.branch != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (msisdn != null ? !msisdn.equals(that.msisdn) : that.msisdn != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (msisdn != null ? msisdn.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
