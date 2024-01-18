package vn.supperapp.apigw.restful.models.blog;


import com.fasterxml.jackson.databind.BeanProperty;
import vn.supperapp.apigw.db.dto.Blog;
import vn.supperapp.apigw.db.dto.DayOff;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

public class BlogResponse extends BaseResponse {

    private Blog blog;
    public BlogResponse() {
    }

    public BlogResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
