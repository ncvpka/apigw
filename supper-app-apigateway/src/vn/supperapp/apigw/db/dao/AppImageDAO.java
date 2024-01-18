/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.Shift;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.utils.enums.FileMgtUtils;

import java.io.InputStream;

/**
 * @author Taink
 */
public class AppImageDAO extends BaseDAO {

    public AppImageDAO() {
        this.logger = LogManager.getLogger(AppImageDAO.class);
    }

    public Shift getShiftAdministrative(Session session, Long orgId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Shift s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.status = 1");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.timekeepingType = 2");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);

            return (Shift) query.setMaxResults(1);
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public Shift getShiftById(Session session, Long id, Long orgId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Shift s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.status = 1");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (Shift) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getListAppImage ERROR ", ex);
            throw ex;
        }
    }
    public String uploadFile(String accountNumber, String fileUploadType, UploadConfigInfo uploadConfig, FormDataBodyPart file) {
        try {
            String fileExt = file.getMediaType().getSubtype();
            String fileName = String.format("%s_%s_%d.%s", fileUploadType, accountNumber, System.currentTimeMillis(), fileExt);
            String folderPath = String.format("%s/%s", uploadConfig.getBlogFolder(), accountNumber);

            String fullFolderPath = String.format("%s/%s", uploadConfig.getBaseDocumentFolder(), folderPath);

            InputStream fileIs = file.getEntityAs(InputStream.class);
            String ftmp = FileMgtUtils.saveFileTo(fullFolderPath, fileName, fileIs);
            IOUtils.closeQuietly(fileIs);

            return String.format("%s/%s", folderPath, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#uploadFile - EXCEPTION: ", ex);
        }
        return null;
    }

    public boolean validateFileFormat(UploadConfigInfo config, FormDataBodyPart file) {
        try {
            String fType = file.getMediaType().getType();
            String fSubType = file.getMediaType().getSubtype().toUpperCase();
            return "image".equals(fType) && config.getListFileExt().contains(fSubType);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#validateFileFormat - EXCEPTION: ", ex);
        }
        return false;
    }
}
