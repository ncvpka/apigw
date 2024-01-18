package vn.supperapp.apigw.db.dto;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "EKYC_INFO", schema = "SUPPERAPP", catalog = "")
public class EkycInfo {
    public static final String TABLE_NAME = "EKYC_INFO";
    public static final String SEQ_NAME = "EKYC_INFO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ekyc_info_generator")
    @SequenceGenerator(name = "ekyc_info_generator", allocationSize = 1, sequenceName = "ekyc_info_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic
    @Column(name = "FRONT_IMG_IDCARD_URL")
    private String frontImgIdcardUrl;
    @Basic
    @Column(name = "BACK_IMG_IDCARD_URL")
    private String backImgIdcardUrl;
    @Basic
    @Column(name = "SELFIE_IMG_URL")
    private String selfieImgUrl;
    @Basic
    @Column(name = "FRONT_STATUS")
    private Long frontStatus;
    @Basic
    @Column(name = "BACK_STATUS")
    private Long backStatus;
    @Basic
    @Column(name = "EKYC_STATUS")
    private Long ekycStatus;
    @Basic
    @Column(name = "DOCUMENT_TYPE")
    private String documentType;
    @Basic
    @Column(name = "DOCUMENT_ID")
    private String documentId;
    @Basic
    @Column(name = "ID_TYPE")
    private Long idType;
    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Basic
    @Column(name = "BIRTHPLACE")
    private String birthplace;
    @Basic
    @Column(name = "SEX")
    private Long sex;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "APARTMENT_NO")
    private String apartmentNo;
    @Basic
    @Column(name = "STREET")
    private String street;
    @Basic
    @Column(name = "WARD")
    private String ward;
    @Basic
    @Column(name = "DISTRICT")
    private String district;
    @Basic
    @Column(name = "PROVINCE")
    private String province;
    @Basic
    @Column(name = "NATIONALITY")
    private String nationality;
    @Basic
    @Column(name = "ISSUE_PLACE")
    private String issuePlace;
    @Basic
    @Column(name = "ISSUE_DATE")
    private Date issueDate;
    @Basic
    @Column(name = "ISSUE_COUNTRY")
    private String issueCountry;
    @Basic
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;
    @Basic
    @Column(name = "TAX_NUMBER")
    private String taxNumber;
    @Basic
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Basic
    @Column(name = "TRANS_ID")
    private String transId;
    @Basic
    @Column(name = "CONTRACT_URL")
    private String contractUrl;
    @Basic
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Basic
    @Column(name = "REFERENCE_CODE")
    private String referenceCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrontImgIdcardUrl() {
        return frontImgIdcardUrl;
    }

    public void setFrontImgIdcardUrl(String frontImgIdcardUrl) {
        this.frontImgIdcardUrl = frontImgIdcardUrl;
    }

    public String getBackImgIdcardUrl() {
        return backImgIdcardUrl;
    }

    public void setBackImgIdcardUrl(String backImgIdcardUrl) {
        this.backImgIdcardUrl = backImgIdcardUrl;
    }

    public String getSelfieImgUrl() {
        return selfieImgUrl;
    }

    public void setSelfieImgUrl(String selfieImgUrl) {
        this.selfieImgUrl = selfieImgUrl;
    }

    public Long getFrontStatus() {
        return frontStatus;
    }

    public void setFrontStatus(Long frontStatus) {
        this.frontStatus = frontStatus;
    }

    public Long getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(Long backStatus) {
        this.backStatus = backStatus;
    }

    public Long getEkycStatus() {
        return ekycStatus;
    }

    public void setEkycStatus(Long ekycStatus) {
        this.ekycStatus = ekycStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueCountry() {
        return issueCountry;
    }

    public void setIssueCountry(String issueCountry) {
        this.issueCountry = issueCountry;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EkycInfo that = (EkycInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (frontImgIdcardUrl != null ? !frontImgIdcardUrl.equals(that.frontImgIdcardUrl) : that.frontImgIdcardUrl != null)
            return false;
        if (backImgIdcardUrl != null ? !backImgIdcardUrl.equals(that.backImgIdcardUrl) : that.backImgIdcardUrl != null)
            return false;
        if (selfieImgUrl != null ? !selfieImgUrl.equals(that.selfieImgUrl) : that.selfieImgUrl != null) return false;
        if (frontStatus != null ? !frontStatus.equals(that.frontStatus) : that.frontStatus != null) return false;
        if (backStatus != null ? !backStatus.equals(that.backStatus) : that.backStatus != null) return false;
        if (ekycStatus != null ? !ekycStatus.equals(that.ekycStatus) : that.ekycStatus != null) return false;
        if (documentType != null ? !documentType.equals(that.documentType) : that.documentType != null) return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (idType != null ? !idType.equals(that.idType) : that.idType != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (apartmentNo != null ? !apartmentNo.equals(that.apartmentNo) : that.apartmentNo != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (ward != null ? !ward.equals(that.ward) : that.ward != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null) return false;
        if (issuePlace != null ? !issuePlace.equals(that.issuePlace) : that.issuePlace != null) return false;
        if (issueDate != null ? !issueDate.equals(that.issueDate) : that.issueDate != null) return false;
        if (issueCountry != null ? !issueCountry.equals(that.issueCountry) : that.issueCountry != null) return false;
        if (expiryDate != null ? !expiryDate.equals(that.expiryDate) : that.expiryDate != null) return false;
        if (taxNumber != null ? !taxNumber.equals(that.taxNumber) : that.taxNumber != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (transId != null ? !transId.equals(that.transId) : that.transId != null) return false;
        if (contractUrl != null ? !contractUrl.equals(that.contractUrl) : that.contractUrl != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (referenceCode != null ? !referenceCode.equals(that.referenceCode) : that.referenceCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (frontImgIdcardUrl != null ? frontImgIdcardUrl.hashCode() : 0);
        result = 31 * result + (backImgIdcardUrl != null ? backImgIdcardUrl.hashCode() : 0);
        result = 31 * result + (selfieImgUrl != null ? selfieImgUrl.hashCode() : 0);
        result = 31 * result + (frontStatus != null ? frontStatus.hashCode() : 0);
        result = 31 * result + (backStatus != null ? backStatus.hashCode() : 0);
        result = 31 * result + (ekycStatus != null ? ekycStatus.hashCode() : 0);
        result = 31 * result + (documentType != null ? documentType.hashCode() : 0);
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (apartmentNo != null ? apartmentNo.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (ward != null ? ward.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (issuePlace != null ? issuePlace.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (issueCountry != null ? issueCountry.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (taxNumber != null ? taxNumber.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (transId != null ? transId.hashCode() : 0);
        result = 31 * result + (contractUrl != null ? contractUrl.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (referenceCode != null ? referenceCode.hashCode() : 0);
        return result;
    }
}
