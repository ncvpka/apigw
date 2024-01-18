package vn.supperapp.apigw.beans;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class PersonalScheduleInfo {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Date startDate;
    private Date toDate;
    private String type;
    private String remind;
    private String repeat;
    private String address;
    private String color;
    private Long allDay;
    private List<BlogTagInfo> blogTagInfoList;

    public PersonalScheduleInfo(Long id, Long userId, String title, String content, Date startDate, Date toDate, String type, String remind, String repeat, String address, String color, Long allDay) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.toDate = toDate;
        this.type = type;
        this.remind = remind;
        this.repeat = repeat;
        this.address = address;
        this.color = color;
        this.allDay = allDay;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getAllDay() {
        return allDay;
    }

    public void setAllDay(Long allDay) {
        this.allDay = allDay;
    }

    public List<BlogTagInfo> getBlogTagInfoList() {
        return blogTagInfoList;
    }

    public void setBlogTagInfoList(List<BlogTagInfo> blogTagInfoList) {
        this.blogTagInfoList = blogTagInfoList;
    }
}
