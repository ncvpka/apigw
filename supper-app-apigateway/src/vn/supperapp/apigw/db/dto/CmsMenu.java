package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

@Entity
@Table(name = "CMS_MENU", schema = "SUPPERAPP", catalog = "")
public class CmsMenu {
    public static final String TABLE_NAME = "CMS_MENU";
    public static final String SEQ_NAME = "CMS_MENU_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cms_menu_generator")
    @SequenceGenerator(name = "cms_menu_generator", allocationSize = 1, sequenceName = "cms_menu_SEQ")
    @Column(name = "ID", nullable = false)
    private short id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PARENT")
    private Short parent;
    @Basic
    @Column(name = "ROUTE")
    private String route;
    @Basic
    @Column(name = "ORDER")
    private Byte order;
    @Basic
    @Column(name = "DATA")
    private String data;
    @Basic
    @Column(name = "ICON")
    private String icon;
    @Basic
    @Column(name = "IS_ACTIVE")
    private Byte isActive;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getParent() {
        return parent;
    }

    public void setParent(Short parent) {
        this.parent = parent;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsMenu that = (CmsMenu) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
