package vn.supperapp.apigw.objs;

import java.util.List;

public class UploadConfigInfo {
    private String baseDocumentFolder;
    private String documentFolder;
    private String blogFolder;
    private String checkInFolder;
    private List<String> listFileExt;
    private long maxFileSize;

    public String getBaseDocumentFolder() {
        return baseDocumentFolder;
    }

    public void setBaseDocumentFolder(String baseDocumentFolder) {
        this.baseDocumentFolder = baseDocumentFolder;
    }

    public String getDocumentFolder() {
        return documentFolder;
    }

    public void setDocumentFolder(String documentFolder) {
        this.documentFolder = documentFolder;
    }

    public String getBlogFolder() {
        return blogFolder;
    }

    public void setBlogFolder(String blogFolder) {
        this.blogFolder = blogFolder;
    }

    public String getCheckInFolder() {
        return checkInFolder;
    }

    public void setCheckInFolder(String checkInFolder) {
        this.checkInFolder = checkInFolder;
    }

    public List<String> getListFileExt() {
        return listFileExt;
    }

    public void setListFileExt(List<String> listFileExt) {
        this.listFileExt = listFileExt;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
