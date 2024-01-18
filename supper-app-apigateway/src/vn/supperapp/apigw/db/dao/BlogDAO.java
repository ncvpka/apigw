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
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dto.AppImage;
import vn.supperapp.apigw.db.dto.Blog;
import vn.supperapp.apigw.db.dto.BlogTag;
import vn.supperapp.apigw.db.dto.Shift;
import vn.supperapp.apigw.objs.UploadConfigInfo;
import vn.supperapp.apigw.utils.enums.FileMgtUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taink
 */
public class BlogDAO extends BaseDAO {

    public BlogDAO() {
        this.logger = LogManager.getLogger(BlogDAO.class);
    }

    public Blog getBlogById(Session session, Long id, Long orgId, String language) throws Exception {
        logger.info("#getListAppImage");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From Blog s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.orgId = :orgId");
            sql.append("   And s.id = :id");

            Query query = session.createQuery(sql.toString());
            query.setParameter("orgId", orgId);
            query.setParameter("id", id);

            return (Blog) query.uniqueResult();
        } catch (Exception ex) {
            logger.error("#getBlog ERROR ", ex);
            throw ex;
        }
    }
    public List<AppImage> getAppImageByBlogId(Session session, Long id, String language) throws Exception {
        logger.info("#getBlogTagById");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" From AppImage s ");
            sql.append(" Where 1 = 1 ");
            sql.append("   And s.objId = :id");
            sql.append("   And s.objType = 'BLOG'");

            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);

            List<AppImage> list = (List<AppImage>) query.getResultList();
            if (list == null)
            {
                list = new ArrayList<AppImage>();
            }
            return list;
        } catch (Exception ex) {
            logger.error("#getBlogTagById ERROR ", ex);
            throw ex;
        }
    }
    public boolean deleteAllImageByBlogId(Session session, Long id) {
        logger.info("#deletecheckInImages START -- ");
        try {

            Transaction t = session.getTransaction();
            if (t == null || !t.isActive()) {
                t = session.beginTransaction();
            }

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE APP_IMAGE WHERE OBJ_ID = :id AND OBJ_TYPE = 'BLOG'");
            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("id", id);
            query.executeUpdate();
            session.flush();
            t.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            logger.error("#deletecheckInImages Exception: ", e);
        }
        return true;
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
