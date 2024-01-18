package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.HREmployeeEducationInfo;
import vn.supperapp.apigw.beans.HREmployeeInfoDetail;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.HrDepartment;
import vn.supperapp.apigw.db.dto.HrEmployee;
import vn.supperapp.apigw.db.dto.HrEmployeeEducation;
import vn.supperapp.apigw.db.dto.Shift;
import vn.supperapp.apigw.restful.models.hr.HrEmployeeRequest;
import vn.supperapp.apigw.utils.CommonUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HrEmployeeEducationDAO extends BaseDAO {

    public HrEmployeeEducationDAO() {
        this.logger = LogManager.getLogger(HrEmployeeEducationDAO.class);
    }

    public HrEmployeeEducation getById(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getByIdEducation");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrEmployeeEducation s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);
            query.setMaxResults(1);

            return (HrEmployeeEducation) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getByIdEducation ERROR ", ex);
            throw ex;
        }
    }

    public List<HREmployeeEducationInfo> getListEducation(Session session, Long userId, Long orgId) throws Exception {
        logger.info("#getListEducation");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select e.id as id, li.id as idLiteracy, li.name as literacy, di.id as idDiploma, di.name as diploma, tt.id as idTypeTraining, tt.name as typeTraining, c.id as idCategorize, c.name as categorize, sp.id as idSpecialization, \n" +
                    " sp.name as specialization, lang.id as idEngLevel, lang.name as engLevel,\n" +
                    " e.start_date as startDate, e.end_date as endDate, te.id as idComputerSkill, te.name as computerSkill, e.address as address, e.create_at as  createAt from hr_employee_education e  \n" +
                    " LEFT JOIN HR_PARAM_VALUE li ON e.id_literacy = li.id \n" +
                    " LEFT JOIN HR_PARAM_VALUE di ON e.id_diploma = di.id \n" +
                    " LEFT JOIN HR_PARAM_VALUE tt ON e.id_train_type = tt.id \n" +
                    " LEFT JOIN HR_PARAM_VALUE sp ON e.id_specialized = sp.id \n" +
                    " LEFT JOIN HR_PARAM_VALUE c ON e.id_categorize = c.id \n " +
                    " LEFT JOIN HR_PARAM_VALUE lang ON e.language_level = lang.id\n" +
                    " LEFT JOIN HR_PARAM_VALUE te ON e.tech_level = te.id \n" +
                    " where 1=1 and e.id_user = :userId and e.org_id = :orgId");

            NativeQuery<HREmployeeEducationInfo> querySelect = session.createNativeQuery(sql.toString(), HrEmployeeEducation.EDUCATION_DETAIL);
            querySelect.setParameter("userId", userId);
            querySelect.setParameter("orgId", orgId);

            return querySelect.getResultList();
        } catch (Exception ex) {
            logger.error("#getListEmployee ERROR ", ex);
            throw ex;
        }
    }
    public HREmployeeInfoDetail getEmployeeDetail(Session session, Long orgId, Long id) throws Exception {
        logger.info("#getListEmployee");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT e.id AS id,\n" +
                    "       e.code AS code,\n" +
                    "       e.full_name AS fullName,\n" +
                    "       e.birthday AS birthDay,\n" +
                    "       e.date_start_work AS dateStartWork,\n" +
                    "       e.gender AS gender,\n" +
                    "       e.place_of_birth AS placeOfBirth,\n" +
                    "       e.home_town AS homeTown,\n" +
                    "       tw.name AS typeWork,\n" +
                    "       tw.id AS idTypeWork,\n" +
                    "       na.id AS idNationality,\n" +
                    "       na.name AS nationality,\n" +
                    "       re.id AS idReligion,\n" +
                    "       re.name AS religion,\n" +
                    "       ma.id AS idMaritalStatus,\n" +
                    "       ma.name AS maritalStatus,\n" +
                    "       po.name AS positionName,\n" +
                    "       po.id AS positionId,\n" +
                    "       e.person_number AS personNumber,\n" +
                    "       e.date_person_number AS datePersonNumber,\n" +
                    "       e.place_person_number AS placePersonNumber,\n" +
                    "       e.CURRENT_ADDRESS AS address,\n" +
                    "       e.phone AS phone,\n" +
                    "       e.email AS email,\n" +
                    "       e.email_company AS emailCompany,\n" +
                    "       e.branch_id AS branchId,\n" +
                    "       br.name AS branchName,\n" +
                    "       e.department_id AS departmentId,\n" +
                    "       c.name AS departmentName,\n" +
                    "       e.timekeeping_type AS timeKeepingType,\n" +
                    "       e.type_salary AS typeSalary,\n" +
                    "       e.salary AS salary,\n" +
                    "       e.status AS status,\n" +
                    "       e.org_id AS orgId,\n" +
                    "       o.name AS orgName,\n" +
                    "       e.create_at AS createAt,\n" +
                    "       e.app_user_id AS userId,\n" +
                    "       e.is_full_saturday AS isFullSartuday,\n" +
                    "       ba.id AS bankId,\n" +
                    "       ba.name AS bankName,\n" +
                    "       e.bank_number AS bankNumber,\n" +
                    "       e.note AS note,\n" +
                    "       e.mst AS mst,\n" +
                    "       e.date_singing AS dateSinging,\n" +
                    "       e.end_date AS endDate,\n" +
                    "       e.home_phone AS homePhone,\n" +
                    "       et.id AS idEthnic,\n" +
                    "       et.name AS nameEthnic,\n" +
                    "       e.permanent_residence AS permanentResidence,\n" +
                    "       e.insurance_premium AS insurancePremium,\n" +
                    "       e.avatar AS avatar,\n" +
                    "       e.username AS username,\n" +
                    "       e.rank AS rank,\n" +
                    "       au.full_name AS managerName,\n" +
                    "       e.user_id_manager AS managerId\n" +
                    "FROM HR_EMPLOYEE e\n" +
                    "LEFT JOIN APP_USER a ON e.APP_USER_ID = a.id\n" +
                    "LEFT JOIN hr_department c ON (e.department_id = c.id)\n" +
                    "LEFT JOIN organization o ON (e.org_id IS NULL\n" +
                    "                             OR e.org_id = o.id)\n" +
                    "LEFT JOIN branch br ON (e.branch_id IS NULL\n" +
                    "                        OR e.branch_id = br.id)\n" +
                    "LEFT JOIN APP_USER au ON e.user_id_manager = au.id\n" +
                    "LEFT JOIN HR_PARAM_VALUE na ON e.id_nationnality = na.id\n" +
                    "LEFT JOIN HR_PARAM_VALUE re ON e.id_religion = re.id\n" +
                    "LEFT JOIN HR_PARAM_VALUE ma ON e.id_marital_status = ma.id\n" +
                    "LEFT JOIN HR_PARAM_VALUE et ON e.ethnic_id = et.id\n" +
                    "LEFT JOIN HR_PARAM_VALUE po ON e.id_position = po.id\n" +
                    "LEFT JOIN HR_PARAM_VALUE tw ON e.id_type_work = tw.id\n" +
                    "LEFT JOIN BANK ba ON e.bank_id = ba.id\n" +
                    "WHERE 1 = 1\n" +
                    "  AND e.org_id = :orgId\n" +
                    "  AND e.id = :id");

            NativeQuery<HREmployeeInfoDetail> querySelect = session.createNativeQuery(sql.toString(), HrDepartment.EMPLOYEE_MAPPING);
            querySelect.setParameter("orgId", orgId);
            querySelect.setParameter("id", id);
            return querySelect.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListEmployee ERROR ", ex);
            throw ex;
        }
    }
    public HrEmployee getEmployeeById(Session session, Long id, Long orgId) throws Exception {
        logger.info("#getPaymentRequestById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrEmployee d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.id = :id");
            sql.append("   And d.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);
            return (HrEmployee) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getPaymentRequestById ERROR ", ex);
            throw ex;
        }
    }
    public int getMaxId(Session session) {
        logger.info("#getTotalRecordUnread START -- ");
        int total = 0;
        try {
            Query query = session.createNativeQuery("SELECT NVL(MAX(P.ID), 0) AS MAX_VAL\n" +
                    " FROM hr_employee P");
            total = ((Number) query.getSingleResult()).intValue();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getTotalRecordUnread Exception: ", e);
        }
        return 0;
    }
    public HrEmployee getEmployeeByAppUser(Session session, Long appUser, Long orgId) throws Exception {
        logger.info("#getPaymentRequestById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HrEmployee d ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And d.appUserId = :id");
            sql.append("   And d.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("appUserId", appUser);
            query.setParameter("orgId", orgId);
            return (HrEmployee) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getPaymentRequestById ERROR ", ex);
            throw ex;
        }
    }

}
