package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "CHALLENGE", schema = "SUPPERAPP", catalog = "")
public class Challenge {
    public static final String TABLE_NAME = "CHALLENGE";
    public static final String SEQ_NAME = "CHALLENGE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "challenge_generator")
    @SequenceGenerator(name = "challenge_generator", allocationSize = 1, sequenceName = "challenge_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "CODE")
    private Long code;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "TYPE")
    private String type;
    @Basic
    @Column(name = "STATUS")
    private BigInteger status;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "TIP")
    private String tip;
    @Basic
    @Column(name = "TIME_EXPIRED")
    private Date timeExpired;
    @Basic
    @Column(name = "CREATE_AT")
    private Timestamp createAt;
    @Basic
    @Column(name = "UPDATE_AT")
    private Timestamp updateAt;
    @Basic
    @Column(name = "RULE")
    private String rule;
    @Basic
    @Column(name = "TERM")
    private String term;
    @Basic
    @Column(name = "DESCRIPTIONS")
    private String descriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Date getTimeExpired() {
        return timeExpired;
    }

    public void setTimeExpired(Date timeExpired) {
        this.timeExpired = timeExpired;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge that = (Challenge) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (tip != null ? !tip.equals(that.tip) : that.tip != null) return false;
        if (timeExpired != null ? !timeExpired.equals(that.timeExpired) : that.timeExpired != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (rule != null ? !rule.equals(that.rule) : that.rule != null) return false;
        if (term != null ? !term.equals(that.term) : that.term != null) return false;
        if (descriptions != null ? !descriptions.equals(that.descriptions) : that.descriptions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (tip != null ? tip.hashCode() : 0);
        result = 31 * result + (timeExpired != null ? timeExpired.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (descriptions != null ? descriptions.hashCode() : 0);
        return result;
    }
}
