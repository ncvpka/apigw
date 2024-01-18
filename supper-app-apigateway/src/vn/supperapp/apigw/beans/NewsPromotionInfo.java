package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppImage;

import java.util.List;

public class NewsPromotionInfo {
    private Long id;
    private String newsType;
    private Integer status;
    private String startTime;
    private String endTime;
    private String createTime;
    private Integer contentType;
    private String title;
    private String shortContent;
    private String fullContent;
    private Integer transactionNews;
    private String transactionData;

    List<AppImage> images;

    public NewsPromotionInfo() {
    }

    public NewsPromotionInfo(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public NewsPromotionInfo(Long id, String newsType, Integer status, Integer transactionNews, String transactionData, String startTime, String endTime, String createTime,
                             Integer contentType, String title, String shortContent, String fullContent) {
        this.id = id;
        this.newsType = newsType;
        this.status = status;
        this.transactionNews = transactionNews;
        this.transactionData = transactionData;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.contentType = contentType;
        this.title = title;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<AppImage> getImages() {
        return images;
    }

    public void setImages(List<AppImage> images) {
        this.images = images;
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
