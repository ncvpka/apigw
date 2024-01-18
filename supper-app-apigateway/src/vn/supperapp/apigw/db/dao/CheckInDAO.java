/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.supperapp.apigw.beans.CheckInInfo;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dto.CheckIn;
import vn.supperapp.apigw.db.dto.CheckInImages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author taink
 */
public class CheckInDAO extends BaseDAO {

    public CheckInDAO() {
        this.logger = LogManager.getLogger(CheckInDAO.class);
    }
    public List<CheckInInfo> getCheckIn(Session session, Long userId, Long orgId) throws Exception {
        logger.info("#getCheckIn");
        CheckInInfo checkInInfor = new CheckInInfo();
        List<CheckInInfo> checkInInforList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        try {
            List<CheckInInfo> checkInList  = getListCheckInInfor(session,userId,orgId);
            if(checkInList.size() > 0)
            {
                List<Long> tmps = checkInList.stream().map(CheckInInfo::getId).collect(Collectors.toList());
                ids.addAll(tmps);
                List<CheckInImages> lstImages = null;
                if (ids != null && !ids.isEmpty()) {
                    lstImages = getListCheckInImages(session, ids);
                }
                List<CheckInImages> finalLstImages = lstImages;
                checkInList.forEach(it -> {
                        List<CheckInImages> tmpImages = finalLstImages.stream().filter(im -> im.getCheckInId() == it.getId()).collect(Collectors.toList());
                        it.setUrls(tmpImages);
                        it.setCountImages(tmpImages.size());
                    });
                return checkInList;
            }
            else
            {
                return null;
            }
        } catch (Exception ex) {
            logger.error("#getCheckIn ERROR ", ex);
            throw ex;
        }
    }

    public CheckInInfo getCheckInDetails(Session session, Long id, Long orgId) throws Exception {
        logger.info("#getCheckIn");
        CheckInInfo checkInInfo = new CheckInInfo();
        try {
            checkInInfo  = getCheckInInfoById(session,id,orgId);
            if(checkInInfo != null)
            {
                List<CheckInImages> lstImages = getListCheckInImagesByCheckInId(session, checkInInfo.getId());
                if(lstImages.size() > 0)
                {
                    checkInInfo.setUrls(lstImages);
                }
                return checkInInfo;
            }
            else
            {
                return null;
            }
        } catch (Exception ex) {
            logger.error("#getCheckIn ERROR ", ex);
            throw ex;
        }
    }

    public List<CheckInInfo> getListCheckInInfor(Session session, Long userId, Long orgId) throws Exception {
        logger.info("#getListAppImage");
        List<CheckInInfo> checkInInforList = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select ci from CheckIn ci where ci.userId = :userId AND ci.orgId = :orgId order by createTime desc");
            Query query = session.createQuery(sql.toString());
            query.setParameter("userId", userId);
            query.setParameter("orgId", orgId);
            List<CheckIn> checkInList = query.list();
            if(checkInList.size() > 0)
            {
                checkInList.forEach(it -> {
                    CheckInInfo checkInInfo = new CheckInInfo();
                    checkInInfo.setId(it.getId());
                    checkInInfo.setMsisdn(it.getMsisdn());
                    checkInInfo.setCreateTime(it.getCreateTime());
                    checkInInfo.setCreateBy(it.getCreateBy());
                    checkInInfo.setCheckInTime(it.getCheckInTime());
                    checkInInfo.setType(it.getType());
                    checkInInfo.setLatitude(it.getLatitude());
                    checkInInfo.setLongitude(it.getLongitude());
                    checkInInfo.setAddress(it.getAddress());
                    checkInInfo.setReferencesId(it.getReferencesId());
                    checkInInforList.add(checkInInfo);
                });
            }
            return checkInInforList;
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public List<CheckInImages> getListCheckInImages(Session session, List<Long> objIds) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From CheckInImages i ");
            sql.append(" Where 1 = 1 ");
            sql.append("And i.checkInId in (:objIds) ");
            sql.append(" Order by i.createTime desc ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("objIds", objIds);
            return query.list();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }

    public CheckIn getCheckInById(Session session, Long id, Long orgId) {
        logger.info("#getCheckInById START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From CheckIn ci ");
            sql.append(" Where 1 = 1 ");
            sql.append(" AND ci.id = :id ");
            sql.append(" AND ci.orgId = :orgId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);

            return (CheckIn) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getCheckInById Exception: ", e);
        }
        return null;
    }
    public List<CheckInImages> getCheckInImagesById(Session session, Long id, Long orgId) {
        logger.info("#getCheckInImagesById START -- ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From CheckInImages ci ");
            sql.append(" Where 1 = 1 ");
            sql.append(" AND ci.checkInId = :id ");
            sql.append(" AND ci.orgId = :orgId ");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);

            return (List<CheckInImages>) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("#getCheckInImagesById Exception: ", e);
        }
        return null;
    }

    public boolean deleteDetailsByCheckInId(Session session, Long id, Long orgId) {
        logger.info("#deleteDetailsByCheckInId START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE CHECK_IN_IMAGES WHERE CHECK_IN_ID = :id AND ORG_ID = :orgId");

            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);
            query.executeUpdate();

            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deletecheckInImages Exception: ", e);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return true;
    }
    public CheckInInfo getCheckInInfoById(Session session, Long id, Long orgId) throws Exception {
        logger.info("#getListAppImage");
        CheckInInfo checkInInfo = new CheckInInfo();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select ci from CheckIn ci where ci.id = :id AND ci.orgId = :orgId");
            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("orgId", orgId);
            CheckIn checkIn = (CheckIn) query.uniqueResult();
            if(checkIn != null)
            {
                    checkInInfo.setId(checkIn.getId());
                    checkInInfo.setMsisdn(checkIn.getMsisdn());
                    checkInInfo.setCreateTime(checkIn.getCreateTime());
                    checkInInfo.setCreateBy(checkIn.getCreateBy());
                    checkInInfo.setCheckInTime(checkIn.getCheckInTime());
                    checkInInfo.setType(checkIn.getType());
                    checkInInfo.setLatitude(checkIn.getLatitude());
                    checkInInfo.setLongitude(checkIn.getLongitude());
                    checkInInfo.setAddress(checkIn.getAddress());
                    checkInInfo.setReferencesId(checkIn.getReferencesId());
            }
            return checkInInfo;
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public List<CheckInImages> getListCheckInImagesByCheckInId(Session session, Long checkInId) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From CheckInImages i ");
            sql.append(" Where 1 = 1 ");
            sql.append("And i.checkInId = :checkInId ");
            Query query = session.createQuery(sql.toString());
            query.setParameter("checkInId", checkInId);
            return query.list();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
}
