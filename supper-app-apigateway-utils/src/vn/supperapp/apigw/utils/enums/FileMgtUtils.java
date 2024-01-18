package vn.supperapp.apigw.utils.enums;

import vn.supperapp.apigw.utils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;

public class FileMgtUtils {

    private static final Logger logger = LogManager.getLogger(FileMgtUtils.class);
    
    public static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }
    
    public static String checkAndCreateFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
    public static String saveFileTo(String folderPath, String fileName, InputStream fileInputStream) {
        try {
            if (CommonUtils.isNullOrEmpty(folderPath) || CommonUtils.isNullOrEmpty(fileName)) {
                logger.info("Parameters are NOT valid ");
                return null;
            }
           String folderPathNew = checkAndCreateFolder(folderPath);
            File tmpFolder = new File(folderPathNew);
            if (!tmpFolder.exists()) {
                logger.info("Folder is NOT exists: " + folderPath);
                return null;
            }
            
            String tmpFullFilePath = String.format("%s/%s", tmpFolder.getAbsolutePath(), fileName);
            File f = new File(tmpFullFilePath);
            FileUtils.copyInputStreamToFile(fileInputStream, f);
            logger.info("Success save file to: " + tmpFullFilePath);
            
            return tmpFullFilePath;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
