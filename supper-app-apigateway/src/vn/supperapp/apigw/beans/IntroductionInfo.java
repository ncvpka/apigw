package vn.supperapp.apigw.beans;

import java.util.List;

/**
 * @author TruongLe
 * @Date 15/04/2022
 * @see IntroductionInfo
 */

public class IntroductionInfo {
    private List<String> content;
    private String image;
    private String title;



    public IntroductionInfo() {
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
