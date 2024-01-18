package vn.supperapp.apigw.db.dto;

import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.BlogTagInfo;
import vn.supperapp.apigw.beans.PersonalScheduleInfo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PERSONAL_SCHEDULE")
@SqlResultSetMapping(name = "PERSONAL_SCHEDULE_MAPPING", classes = {@ConstructorResult(
        targetClass = PersonalScheduleInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "userId", type = Long.class),
        @ColumnResult(name = "title", type = String.class),
        @ColumnResult(name = "content", type = String.class),
        @ColumnResult(name = "startDate", type = Date.class),
        @ColumnResult(name = "toDate", type = Date.class),
        @ColumnResult(name = "type", type = String.class),
        @ColumnResult(name = "remind", type = String.class),
        @ColumnResult(name = "repeat", type = String.class),
        @ColumnResult(name = "address", type = String.class),
        @ColumnResult(name = "color", type = String.class),
        @ColumnResult(name = "allDay", type = Long.class),
})})
public class PersonalSchedule implements java.io.Serializable {

    public static final String TABLE_NAME = "PERSONAL_SCHEDULE";
    public static final String SEQ_NAME = "PERSONAL_SCHEDULE_SEQ";
    public static final String PERSONAL_SCHEDULE_MAPPING = "PERSONAL_SCHEDULE_MAPPING";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_schedule_generator")
    @SequenceGenerator(name = "personal_schedule_generator", allocationSize = 1, sequenceName = "BLOG_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "REMIND")
    private String remind;
    @Column(name = "REPEAT")
    private String repeat;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ORG_ID")
    private Long orgId;
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
    @Column(name = "COLOR")
    private String color;
    @Column(name = "ALL_DAY")
    private Long allDay;
    @Transient
    private  List<BlogTagInfo> blogTagList;

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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public List<BlogTagInfo> getBlogTagList() {
        return blogTagList;
    }

    public void setBlogTagList(List<BlogTagInfo> blogTagList) {
        this.blogTagList = blogTagList;
    }
}
