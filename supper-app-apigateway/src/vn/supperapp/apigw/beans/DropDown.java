package vn.supperapp.apigw.beans;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Date;

@SqlResultSetMapping(name = "DROPDOWN_MAPPING", classes = {@ConstructorResult(
        targetClass = DropDown.class, columns = {
        @ColumnResult(name = "title", type = String.class),
        @ColumnResult(name = "value", type = Long.class)
})})
public class DropDown {
    public static final String DROPDOWN_MAPPING = "DROPDOWN_MAPPING";
    private Long value;
    private String title;

    public DropDown(Long value, String title) {
        this.value = value;
        this.title = title;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
