package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.NewsPromotionInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NEWS")
@SqlResultSetMapping(name = "NEWS_PROMOTION_MAPPING", classes = {@ConstructorResult(
        targetClass = NewsPromotionInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "newsType", type = String.class),
        @ColumnResult(name = "status", type = Integer.class),
        @ColumnResult(name = "transactionNews", type = Integer.class),
        @ColumnResult(name = "transactionData", type = String.class),
        @ColumnResult(name = "startTime", type = String.class),
        @ColumnResult(name = "endTime", type = String.class),
        @ColumnResult(name = "createTime", type = String.class),
        @ColumnResult(name = "contentType", type = Integer.class),
        @ColumnResult(name = "title", type = String.class),
        @ColumnResult(name = "shortContent", type = String.class),
        @ColumnResult(name = "fullContent", type = String.class),

})})
public class News implements java.io.Serializable {

    public static final String TABLE_NAME = "NEWS";
    public static final String SEQ_NAME = "NEWS_SEQ";
    public static final String NEWS_PROMOTION_MAPPING = "NEWS_PROMOTION_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_generator")
    @SequenceGenerator(name = "news_generator", allocationSize = 1, sequenceName = "NEWS_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NEWS_TYPE")
    private String newsType;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "CLIENT_TYPE")
    private String clientType;
    @Column(name = "START_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "END_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT_TYPE")
    private Integer contentType; //0: Url; 1: Html content
    @Column(name = "SHORT_CONTENT")
    private String shortContent;
    @Column(name = "FULL_CONTENT")
    private String fullContent;
    @Column(name = "CREATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Column(name = "UPDATE_TIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "SHOW_ON_TOP")
    private int showOnTop;
    @Column(name = "SHOW_AT_HOME")
    private int showAtHome;
    @Column(name = "HOT_NEWS")
    private int hotNews;
    @Column(name = "HOT_DEAL")
    private Integer hotDeal;
    @Column(name = "BIG_VOURCHER")
    private Integer bigVourcher;
    @Column(name = "PROMOTION")
    private Integer promotion;
    @Column(name = "TRANSACTION_NEWS")
    private Integer transactionNews;
    @Column(name = "TRANSACTION_DATA")
    private String transactionData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getShowOnTop() {
        return showOnTop;
    }

    public void setShowOnTop(int showOnTop) {
        this.showOnTop = showOnTop;
    }

    public int getShowAtHome() {
        return showAtHome;
    }

    public void setShowAtHome(int showAtHome) {
        this.showAtHome = showAtHome;
    }

    public int getHotNews() {
        return hotNews;
    }

    public void setHotNews(int hotNews) {
        this.hotNews = hotNews;
    }

    public Integer getHotDeal() {
        return hotDeal;
    }

    public void setHotDeal(Integer hotDeal) {
        this.hotDeal = hotDeal;
    }

    public Integer getBigVourcher() {
        return bigVourcher;
    }

    public void setBigVourcher(Integer bigVourcher) {
        this.bigVourcher = bigVourcher;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    public Integer getTransactionNews() {
        return transactionNews;
    }

    public void setTransactionNews(Integer transactionNews) {
        this.transactionNews = transactionNews;
    }

    public String getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(String transactionData) {
        this.transactionData = transactionData;
    }
}
