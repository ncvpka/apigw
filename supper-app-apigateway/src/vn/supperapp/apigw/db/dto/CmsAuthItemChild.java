package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

@Entity
@Table(name = "CMS_AUTH_ITEM_CHILD", schema = "SUPPERAPP", catalog = "")
public class CmsAuthItemChild {

    public static final String TABLE_NAME = "CMS_AUTH_ITEM_CHILD";
    public static final String SEQ_NAME = "CMS_AUTH_ITEM_CHILD_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_auth_item_child_generator")
    @SequenceGenerator(name = "cms_auth_item_child_generator", allocationSize = 1, sequenceName = "cms_auth_item_child_SEQ")
    @Basic
    @Column(name = "PARENT")
    private String parent;
    @Basic
    @Column(name = "CHILD")
    private String child;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsAuthItemChild that = (CmsAuthItemChild) o;

        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (child != null ? !child.equals(that.child) : that.child != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (child != null ? child.hashCode() : 0);
        return result;
    }
}
