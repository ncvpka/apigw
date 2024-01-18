package vn.supperapp.apigw.db.dto;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "CMS_DEVICE_FCMTOPIC", schema = "SUPPERAPP", catalog = "")
public class CmsDeviceFcmtopic {

    public static final String TABLE_NAME = "CMS_DEVICE_FCMTOPIC";
    public static final String SEQ_NAME = "CMS_DEVICE_FCMTOPIC_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_device_fcmtopic_generator")
    @SequenceGenerator(name = "cms_device_fcmtopic_generator", allocationSize = 1, sequenceName = "cms_device_fcmtopic_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "FCMTOPIC_ID")
    private Long fcmtopicId;
    @Basic
    @Column(name = "APP_USER_ID")
    private Long appUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFcmtopicId() {
        return fcmtopicId;
    }

    public void setFcmtopicId(Long fcmtopicId) {
        this.fcmtopicId = fcmtopicId;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsDeviceFcmtopic that = (CmsDeviceFcmtopic) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fcmtopicId != null ? !fcmtopicId.equals(that.fcmtopicId) : that.fcmtopicId != null) return false;
        if (appUserId != null ? !appUserId.equals(that.appUserId) : that.appUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fcmtopicId != null ? fcmtopicId.hashCode() : 0);
        result = 31 * result + (appUserId != null ? appUserId.hashCode() : 0);
        return result;
    }
}
