package vn.supperapp.apigw.beans;

import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.BlogTag;

import java.util.Date;
import java.util.List;

public class BlogInfo {
    private Long id;
    private Long userId;
    private String fullName;
    private String avatarUrl;
    private Long category;
    private String type;
    private String title;
    private String content;
    private String status;
    private String createAt;
    private Long countView;
    private Long countLike;
    private Long countComment;
    List<AppImage> images;
    List<BlogTag> blogTags;

    private boolean isLike;

    public BlogInfo(Long id, Long userId, String fullName, String avatarUrl, Long category, String type, String title, String content, String status, String createAt, Long countView, Long countLike, Long countComment) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.category = category;
        this.type = type;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createAt = createAt;
        this.countView = countView;
        this.countLike = countLike;
        this.countComment = countComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Long getCountView() {
        return countView;
    }

    public void setCountView(Long countView) {
        this.countView = countView;
    }

    public Long getCountLike() {
        return countLike;
    }

    public void setCountLike(Long countLike) {
        this.countLike = countLike;
    }

    public Long getCountComment() {
        return countComment;
    }

    public void setCountComment(Long countComment) {
        this.countComment = countComment;
    }

    public List<AppImage> getImages() {
        return images;
    }

    public void setImages(List<AppImage> images) {
        this.images = images;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public List<BlogTag> getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(List<BlogTag> blogTags) {
        this.blogTags = blogTags;
    }
}
