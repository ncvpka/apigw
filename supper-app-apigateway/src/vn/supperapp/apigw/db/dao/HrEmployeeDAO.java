package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.BlogInfo;
import vn.supperapp.apigw.beans.HREmployeeInfoDetail;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.db.dto.Blog;
import vn.supperapp.apigw.db.dto.HrDepartment;
import vn.supperapp.apigw.db.dto.HrEmployee;
import vn.supperapp.apigw.db.dto.HrEmployeeEducation;
import vn.supperapp.apigw.restful.models.hr.HrEmployeeRequest;
import vn.supperapp.apigw.utils.CommonUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HrEmployeeDAO extends BaseDAO {

    public HrEmployeeDAO() {
        this.logger = LogManager.getLogger(HrEmployeeDAO.class);
    }

    public List<HrEmployee> getListEmployee(Session session, Long orgId) throws Exception {
        logger.info("#getListEmployee");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ROWNUM, A.id, A.fullName, A.birthday, A.phone, A.personNumber, A.gender, B.name as position, C.name as department, D.fullName as manager, E.name as typeWork \n" +
                    "FROM HrEmployee A \n" +
                    "LEFT JOIN HrPosition B ON (A.idPosition IS NULL or A.idPosition = B.id) \n" +
                    "LEFT JOIN HrDepartment C ON (A.departmentId IS NULL or A.departmentId = C.id) \n" +
                    "LEFT JOIN HrEmployee D ON (A.userIdManager IS NULL or A.userIdManager = D.id) \n" +
                    "LEFT JOIN HrTypeWork E ON (A.idTypeWork IS NULL or A.idTypeWork = E.id) \n");
            sql.append("WHERE A.orgId = :orgId");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return query.getResultList();
        } catch (Exception ex) {
            logger.error("#getListEmployee ERROR ", ex);
            throw ex;
        }
    }

    public PagingResult getListEmployee(Session session, Long orgId, int pageSize, int pageNumber, HrEmployeeRequest request) throws Exception {
        logger.info("#getListEmployee");
        Date fromDate = null;
        Date toDate = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From HR_EMPLOYEE e LEFT join APP_USER a");
            sql.append(" on e.APP_USER_ID = a.id  left join hr_position b on  e.id_position = b.id ");
            sql.append(" left join hr_department c on e.department_id = c.id ");
            sql.append(" LEFT JOIN HR_PARAM_VALUE tw ON e.id_type_work = tw.id ");
            sql.append(" left join organization o on  e.org_id = o.id  ");
            sql.append(" left join branch br on e.branch_id = br.id ");
            sql.append(" left join APP_USER au on e.user_id_manager = au.id ");
            sql.append(" Where 1 = 1 ");
            sql.append("   and e.org_id = :orgId");
            if(!CommonUtils.isNullOrEmpty(request.getKeyWord())){
                sql.append("   and e.full_name  like :keyWord");
            }
            if(request.getDepartmentId() != null){
                sql.append("   and c.id  = :idDepartment");
            }
            if(request.getIdPosition() != null){
                sql.append("   and e.id_position   = :idPosition");
            }
            if(!CommonUtils.isNullOrEmpty(request.getFromDate()) && !CommonUtils.isNullOrEmpty(request.getToDate())){
                sql.append(" and e.birthday between :fromDate and :toDate ");
            }
            if(request.getIdTypeWork() != null){
                sql.append("   and e.id_type_work  = :idTypeWork");
            }
            if(request.getBranchId() != null){
                sql.append("   and e.branch_id  = :branchId");
            }
            if(request.getMonth() != null){
                sql.append("   and to_char(e.birthday,'MM') = :month");
            }
            sql.append("   order by e.CREATE_AT desc ");

            StringBuilder sqlCount = new StringBuilder();
            sqlCount.append(" Select count(1) ");
            sqlCount.append(sql);

            StringBuilder sqlSelect = new StringBuilder();
            sqlSelect.append(" Select e.id, e.code, e.full_name as fullName, e.gender, e.app_user_id as userId, e.birthday as birthDay, e.email as email, e.current_address as address, e.person_number as personNumber,a.avatar_url as avatarUrl, e.phone AS phone, e.timekeeping_type as timeKeepingType, ");
            sqlSelect.append("  e.org_id as orgId, o.name as orgName, e.branch_id as branchId, br.name as branchName, e.status,  ");
            sqlSelect.append("  e.department_id as departmentId, c.name as departmentName,tw.name as typeWork, ");
            sqlSelect.append("  au.full_name as managerName, e.user_id_manager as managerId,  ");
            sqlSelect.append("   b.name as positionName, b.id as positionId ");
            sqlSelect.append(sql);

            NativeQuery<BigDecimal> queryCount = session.createNativeQuery(sqlCount.toString());
            if(!CommonUtils.isNullOrEmpty(request.getKeyWord())){
                queryCount.setParameter("keyWord", "%" + request.getKeyWord() + "%");
            }
            if(request.getDepartmentId() != null){
                queryCount.setParameter("idDepartment", request.getDepartmentId());
            }
            if(!CommonUtils.isNullOrEmpty(request.getFromDate()) && !CommonUtils.isNullOrEmpty(request.getToDate())){
                fromDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getFromDate());
                toDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getToDate());
                queryCount.setParameter("fromDate", fromDate);
                queryCount.setParameter("toDate", toDate);
            }
            if(request.getIdTypeWork() != null){
                queryCount.setParameter("idTypeWork", request.getIdTypeWork());
            }
            if(request.getBranchId() != null){
                queryCount.setParameter("branchId", request.getBranchId());
            }
            if(request.getMonth() != null){
                queryCount.setParameter("month",  "'" + request.getMonth() + "'");
            }
            if(request.getIdPosition() != null){
                queryCount.setParameter("idPosition", request.getIdPosition());
            }
            queryCount.setParameter("orgId", orgId);
            BigDecimal tmp = (BigDecimal) queryCount.uniqueResult();
            int count = 0;
            if (tmp != null) {
                count = tmp.intValue();
            }
            if (count == 0) {
                return new PagingResult(0, 0, 1, pageSize, null);
            }

            PagingResult result = new PagingResult();

            int totalRecords = count;
            result.setTotalRecords(totalRecords);
            result.setPageSize(pageSize);
            int totalPages = totalRecords / pageSize;
            if (totalPages * pageSize < totalRecords) {
                result.setTotalPages(totalPages + 1);
            } else {
                result.setTotalPages(totalPages);
            }
            result.setCurrentPage(pageNumber);
            if (pageNumber > result.getTotalPages()) {
                return result;
            }

            NativeQuery<HrEmployee> querySelect = session.createNativeQuery(sqlSelect.toString(), HrEmployee.EMPLOYEE_MAPPING);
            if(!CommonUtils.isNullOrEmpty(request.getKeyWord())){
                querySelect.setParameter("keyWord", "%" + request.getKeyWord() + "%");
            }
            if(request.getDepartmentId() != null){
                querySelect.setParameter("idDepartment", request.getDepartmentId());
            }
            if(!CommonUtils.isNullOrEmpty(request.getFromDate()) && !CommonUtils.isNullOrEmpty(request.getToDate())){
                fromDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getFromDate());
                toDate =new SimpleDateFormat("dd/MM/yyyy").parse(request.getToDate());
                querySelect.setParameter("fromDate", fromDate);
                querySelect.setParameter("toDate", toDate);
            }
            if(request.getIdTypeWork() != null){
                querySelect.setParameter("idTypeWork", request.getIdTypeWork());
            }
            if(request.getBranchId() != null){
                querySelect.setParameter("branchId", request.getBranchId());
            }
            if(request.getMonth() != null){
                querySelect.setParameter("month", "'" + request.getMonth() + "'");
            }
            if(request.getIdPosition() != null){
                querySelect.setParameter("idPosition", request.getIdPosition());
            }
            querySelect.setParameter("orgId", orgId);

           querySelect.setFirstResult(pageNumber * pageSize);
            querySelect.setMaxResults(pageSize);

            List<HrEmployee> list = querySelect.getResultList();
            result.setResults(list);
            return result;
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
