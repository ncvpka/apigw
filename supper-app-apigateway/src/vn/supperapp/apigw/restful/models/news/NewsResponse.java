package vn.supperapp.apigw.restful.models.news;

import vn.supperapp.apigw.beans.NewsPromotionInfo;
import vn.supperapp.apigw.beans.PagingResult;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class NewsResponse extends BaseResponse {
    private List<NewsPromotionInfo> hotNews;
    private List<NewsPromotionInfo> hotDeal;
    private List<NewsPromotionInfo> bigVourcher;
    private List<NewsPromotionInfo> promotion;
    private List<NewsPromotionInfo> newsPromotionInfoList;
    private PagingResult news;
    private PagingResult data;
    private NewsPromotionInfo newsInfo;

    public NewsResponse() {
    }

    public NewsPromotionInfo getNewsInfo() {
        return newsInfo;
    }

    public void setNewsInfo(NewsPromotionInfo newsInfo) {
        this.newsInfo = newsInfo;
    }

    public NewsResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<NewsPromotionInfo> getHotNews() {
        return hotNews;
    }

    public void setHotNews(List<NewsPromotionInfo> hotNews) {
        this.hotNews = hotNews;
    }

    public PagingResult getNews() {
        return news;
    }

    public void setNews(PagingResult news) {
        this.news = news;
    }

    public List<NewsPromotionInfo> getHotDeal() {
        return hotDeal;
    }

    public void setHotDeal(List<NewsPromotionInfo> hotDeal) {
        this.hotDeal = hotDeal;
    }

    public List<NewsPromotionInfo> getBigVourcher() {
        return bigVourcher;
    }

    public void setBigVourcher(List<NewsPromotionInfo> bigVourcher) {
        this.bigVourcher = bigVourcher;
    }

    public List<NewsPromotionInfo> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<NewsPromotionInfo> promotion) {
        this.promotion = promotion;
    }

    public PagingResult getData() {
        return data;
    }

    public void setData(PagingResult data) {
        this.data = data;
    }

    public List<NewsPromotionInfo> getCategoryPromotion() {
        return newsPromotionInfoList;
    }

    public void setCategoryPromotion(List<NewsPromotionInfo> categoryPromotion) {
        this.newsPromotionInfoList = categoryPromotion;
    }
}
