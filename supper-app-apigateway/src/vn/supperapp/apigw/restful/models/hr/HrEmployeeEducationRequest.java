package vn.supperapp.apigw.restful.models.hr;

import vn.supperapp.apigw.restful.models.BaseRequest;

import java.util.Date;

public class HrEmployeeEducationRequest extends BaseRequest {

    private Long userId;
    private Long educationId;
    private Long idLiteracy;
    private Long idDiploma;
    private Long idTypeTraining;
    private Long idCategorize;
    private Long idSpecialization;
    private Long idEngLevel;
    private String startDate;
    private String endDate;
    private Long idComputerSkill;
    private String place;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getIdComputerSkill() {
        return idComputerSkill;
    }

    public void setIdComputerSkill(Long idComputerSkill) {
        this.idComputerSkill = idComputerSkill;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
