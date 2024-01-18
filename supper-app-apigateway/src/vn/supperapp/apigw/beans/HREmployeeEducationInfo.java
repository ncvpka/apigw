package vn.supperapp.apigw.beans;

import java.util.Date;

public class HREmployeeEducationInfo {
    private Long id;
    private Long idLiteracy;
    private String literacy;
    private Long idDiploma;
    private String diploma;
    private Long idTypeTraining;
    private String typeTraining;
    private Long idCategorize;
    private String categorize;
    private Long idSpecialization;
    private String specialization;
    private Long idEngLevel;
    private String engLevel;
    private Date startDate;
    private Date endDate;
    private Long idComputerSkill;
    private String computerSkill;
    private String address;
    private Date createAt;

    public HREmployeeEducationInfo() {
    }

    public HREmployeeEducationInfo(Long id, Long idLiteracy, String literacy, Long idDiploma, String diploma, Long idTypeTraining, String typeTraining, Long idCategorize, String categorize, Long idSpecialization, String specialization, Long idEngLevel, String engLevel, Date startDate, Date endDate, Long idComputerSkill, String computerSkill, String address, Date createAt) {
        this.id = id;
        this.idLiteracy = idLiteracy;
        this.literacy = literacy;
        this.idDiploma = idDiploma;
        this.diploma = diploma;
        this.idTypeTraining = idTypeTraining;
        this.typeTraining = typeTraining;
        this.idCategorize = idCategorize;
        this.categorize = categorize;
        this.idSpecialization = idSpecialization;
        this.specialization = specialization;
        this.idEngLevel = idEngLevel;
        this.engLevel = engLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idComputerSkill = idComputerSkill;
        this.computerSkill = computerSkill;
        this.address = address;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLiteracy() {
        return literacy;
    }

    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getTypeTraining() {
        return typeTraining;
    }

    public void setTypeTraining(String typeTraining) {
        this.typeTraining = typeTraining;
    }

    public String getCategorize() {
        return categorize;
    }

    public void setCategorize(String categorize) {
        this.categorize = categorize;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEngLevel() {
        return engLevel;
    }

    public void setEngLevel(String engLevel) {
        this.engLevel = engLevel;
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

    public String getComputerSkill() {
        return computerSkill;
    }

    public void setComputerSkill(String computerSkill) {
        this.computerSkill = computerSkill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getIdLiteracy() {
        return idLiteracy;
    }

    public void setIdLiteracy(Long idLiteracy) {
        this.idLiteracy = idLiteracy;
    }

    public Long getIdDiploma() {
        return idDiploma;
    }

    public void setIdDiploma(Long idDiploma) {
        this.idDiploma = idDiploma;
    }

    public Long getIdTypeTraining() {
        return idTypeTraining;
    }

    public void setIdTypeTraining(Long idTypeTraining) {
        this.idTypeTraining = idTypeTraining;
    }

    public Long getIdCategorize() {
        return idCategorize;
    }

    public void setIdCategorize(Long idCategorize) {
        this.idCategorize = idCategorize;
    }

    public Long getIdSpecialization() {
        return idSpecialization;
    }

    public void setIdSpecialization(Long idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public Long getIdEngLevel() {
        return idEngLevel;
    }

    public void setIdEngLevel(Long idEngLevel) {
        this.idEngLevel = idEngLevel;
    }

    public Long getIdComputerSkill() {
        return idComputerSkill;
    }

    public void setIdComputerSkill(Long idComputerSkill) {
        this.idComputerSkill = idComputerSkill;
    }
}
