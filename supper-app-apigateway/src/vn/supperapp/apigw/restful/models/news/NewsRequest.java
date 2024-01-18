package vn.supperapp.apigw.restful.models.news;

import vn.supperapp.apigw.restful.models.BaseRequest;

public class NewsRequest extends BaseRequest {
    private int page = 0;
    private int promotionType;
    private Long id;
    private String refId;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(int promotionType) {
        this.promotionType = promotionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
