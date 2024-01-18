package vn.supperapp.apigw.db.dto;


import vn.supperapp.apigw.beans.HREmployeeEducationInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HR_EMPLOYEE_EDUCATION", schema = "SUPPERAPP", catalog = "")
@SqlResultSetMapping(name = "EDUCATION_DETAIL", classes = {@ConstructorResult(
        targetClass = HREmployeeEducationInfo.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "idLiteracy", type = Long.class),
        @ColumnResult(name = "literacy", type = String.class),
        @ColumnResult(name = "idDiploma", type = Long.class),
        @ColumnResult(name = "diploma", type = String.class),
        @ColumnResult(name = "idTypeTraining", type = Long.class),
        @ColumnResult(name = "typeTraining", type = String.class),
        @ColumnResult(name = "idCategorize", type = Long.class),
        @ColumnResult(name = "categorize", type = String.class),
        @ColumnResult(name = "idSpecialization", type = Long.class),
        @ColumnResult(name = "specialization", type = String.class),
        @ColumnResult(name = "idEngLevel", type = Long.class),
        @ColumnResult(name = "engLevel", type = String.class),
        @ColumnResult(name = "startDate", type = Date.class),
        @ColumnResult(name = "endDate", type = Date.class),
        @ColumnResult(name = "idComputerSkill", type = Long.class),
        @ColumnResult(name = "computerSkill", type = String.class),
        @ColumnResult(name = "address", type = String.class),
        @ColumnResult(name = "createAt", type = Date.class),
})})
public class HrEmployeeEducation {
    public static final String TABLE_NAME = "HR_EMPLOYEE_EDUCATION";
    public static final String SEQ_NAME = "HR_EMPLOYEE_EDUCATION_SEQ";
    public static final String EDUCATION_DETAIL = "EDUCATION_DETAIL";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_employee_education_generator")
    @SequenceGenerator(name = "hr_employee_education_generator", allocationSize = 1, sequenceName = "HR_EMPLOYEE_EDUCATION_SEQ")
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "ID_USER")
    private Long idUser;
    @Basic
    @Column(name = "START_DATE")
    private Date startDate;
    @Basic
    @Column(name = "END_DATE")
    private Date endDate;
    @Basic
    @Column(name = "ID_LITERACY")
    private Long idLiteracy;
    @Basic
    @Column(name = "ID_TRAIN_TYPE")
    private Long idTrainType;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "ID_SPECIALIZED")
    private Long idSpecialized;
    @Basic
    @Column(name = "ID_CATEGORIZE")
    private Long idCategorize;
    @Basic
    @Column(name = "LANGUAGE_LEVEL")
    private Long languageLevel;
    @Basic
    @Column(name = "TECH_LEVEL")
    private Long techLevel;
    @Basic
    @Column(name = "ORG_ID")
    private Long orgId;
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic
    @Column(name = "CREATE_AT")
    private Date createAt;
    @Basic
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Basic
    @Column(name = "UPDATE_AT")
    private Date updateAt;
    @Basic
    @Column(name = "ID_DIPLOMA")
    private Long idDiploma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getIdLiteracy() {
        return idLiteracy;
    }

    public void setIdLiteracy(Long idLiteracy) {
        this.idLiteracy = idLiteracy;
    }

    public Long getIdTrainType() {
        return idTrainType;
    }

    public void setIdTrainType(Long idTrainType) {
        this.idTrainType = idTrainType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdSpecialized() {
        return idSpecialized;
    }

    public void setIdSpecialized(Long idSpecialized) {
        this.idSpecialized = idSpecialized;
    }

    public Long getIdCategorize() {
        return idCategorize;
    }

    public void setIdCategorize(Long idCategorize) {
        this.idCategorize = idCategorize;
    }

    public Long getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(Long languageLevel) {
        this.languageLevel = languageLevel;
    }

    public Long getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(Long techLevel) {
        this.techLevel = techLevel;
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

    public Long getIdDiploma() {
        return idDiploma;
    }

    public void setIdDiploma(Long idDiploma) {
        this.idDiploma = idDiploma;
    }
}
