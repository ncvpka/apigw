package vn.supperapp.apigw.beans;


public class GroupDataPackageInfo {
    private Long id;
    private String code;
    private String title;
    private String subTitle;
    private String description;
    private double value;
    private int status;
    private String name;
    private String shortName;
    private Long groupDataId;
    private String url;

    public GroupDataPackageInfo(Long id, String code, String title, String subTitle, String description, double value, String name, String shortName, Long groupDataId, String url) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.value = value;
        this.name = name;
        this.shortName = shortName;
        this.groupDataId = groupDataId;
        this.url = url;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getGroupDataId() {
        return groupDataId;
    }

    public void setGroupDataId(Long groupDataId) {
        this.groupDataId = groupDataId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
