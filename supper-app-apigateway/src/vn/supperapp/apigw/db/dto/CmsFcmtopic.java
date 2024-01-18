package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CMS_FCMTOPIC", schema = "SUPPERAPP", catalog = "")
public class CmsFcmtopic {

    public static final String TABLE_NAME = "CMS_FCMTOPIC";
    public static final String SEQ_NAME = "CMS_FCMTOPIC_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_fcmtopic_generator")
    @SequenceGenerator(name = "cms_fcmtopic_generator", allocationSize = 1, sequenceName = "cms_fcmtopic_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "TOPIC_NAME")
    private String topicName;
    @Basic
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "CHANNEL")
    private String channel;
    @Basic
    @Column(name = "ISALL")
    private Boolean isall;
    @Basic
    @Column(name = "ISUSER")
    private Boolean isuser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean getIsall() {
        return isall;
    }

    public void setIsall(Boolean isall) {
        this.isall = isall;
    }

    public Boolean getIsuser() {
        return isuser;
    }

    public void setIsuser(Boolean isuser) {
        this.isuser = isuser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsFcmtopic that = (CmsFcmtopic) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (topicName != null ? !topicName.equals(that.topicName) : that.topicName != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (channel != null ? !channel.equals(that.channel) : that.channel != null) return false;
        if (isall != null ? !isall.equals(that.isall) : that.isall != null) return false;
        if (isuser != null ? !isuser.equals(that.isuser) : that.isuser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topicName != null ? topicName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        result = 31 * result + (isall != null ? isall.hashCode() : 0);
        result = 31 * result + (isuser != null ? isuser.hashCode() : 0);
        return result;
    }
}
