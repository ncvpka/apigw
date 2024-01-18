package vn.supperapp.apigw.db.dto;

import javax.persistence.*;


@Entity
@Table(name = "UI_COMMON", schema = "SUPPERAPP", catalog = "")
public class UiCommon {
    public static final String TABLE_NAME = "UI_COMMON";
    public static final String SEQ_NAME = "UI_COMMON_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ui_common_generator")
    @SequenceGenerator(name = "ui_common_generator", allocationSize = 1, sequenceName = "ui_common_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "BANNER")
    private String banner;
    @Basic
    @Column(name = "LOCATION")
    private String location;
    @Basic
    @Column(name = "DEEP_LINK")
    private String deepLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UiCommon that = (UiCommon) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (banner != null ? !banner.equals(that.banner) : that.banner != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (deepLink != null ? !deepLink.equals(that.deepLink) : that.deepLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (banner != null ? banner.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (deepLink != null ? deepLink.hashCode() : 0);
        return result;
    }
}
