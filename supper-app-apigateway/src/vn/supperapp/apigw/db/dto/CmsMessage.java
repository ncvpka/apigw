package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "CMS_MESSAGE", schema = "SUPPERAPP", catalog = "")
public class CmsMessage {
    public static final String TABLE_NAME = "CMS_MESSAGE";
    public static final String SEQ_NAME = "CMS_MESSAGE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_message_generator")
    @SequenceGenerator(name = "cms_message_generator", allocationSize = 1, sequenceName = "cms_message_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "SEND_TO")
    private String sendTo;
    @Basic
    @Column(name = "PUBLISH_TIME")
    private Date publishTime;
    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "CONTENT")
    private String content;
    @Basic
    @Column(name = "DEEP_LINK")
    private String deepLink;
    @Basic
    @Column(name = "NEWS_ID")
    private String newsId;
    @Basic
    @Column(name = "NEWS_TYPE")
    private String newsType;
    @Basic
    @Column(name = "REF_ID")
    private Long refId;
    @Basic
    @Column(name = "TITLE_LANGUAGE")
    private String titleLanguage;
    @Basic
    @Column(name = "CONTENT_LANGUAGE")
    private String contentLanguage;
    @Basic
    @Column(name = "START_TIME")
    private Date startTime;
    @Basic
    @Column(name = "END_TIME")
    private Date endTime;
    @Basic
    @Column(name = "JOB_TYPE")
    private String jobType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getTitleLanguage() {
        return titleLanguage;
    }

    public void setTitleLanguage(String titleLanguage) {
        this.titleLanguage = titleLanguage;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsMessage that = (CmsMessage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sendTo != null ? !sendTo.equals(that.sendTo) : that.sendTo != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (deepLink != null ? !deepLink.equals(that.deepLink) : that.deepLink != null) return false;
        if (newsId != null ? !newsId.equals(that.newsId) : that.newsId != null) return false;
        if (newsType != null ? !newsType.equals(that.newsType) : that.newsType != null) return false;
        if (refId != null ? !refId.equals(that.refId) : that.refId != null) return false;
        if (titleLanguage != null ? !titleLanguage.equals(that.titleLanguage) : that.titleLanguage != null)
            return false;
        if (contentLanguage != null ? !contentLanguage.equals(that.contentLanguage) : that.contentLanguage != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (jobType != null ? !jobType.equals(that.jobType) : that.jobType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sendTo != null ? sendTo.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (deepLink != null ? deepLink.hashCode() : 0);
        result = 31 * result + (newsId != null ? newsId.hashCode() : 0);
        result = 31 * result + (newsType != null ? newsType.hashCode() : 0);
        result = 31 * result + (refId != null ? refId.hashCode() : 0);
        result = 31 * result + (titleLanguage != null ? titleLanguage.hashCode() : 0);
        result = 31 * result + (contentLanguage != null ? contentLanguage.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (jobType != null ? jobType.hashCode() : 0);
        return result;
    }
}
