package vn.supperapp.apigw.restful.models.common;

import vn.supperapp.apigw.beans.IntroductionInfo;
import vn.supperapp.apigw.beans.TipMessageInfo;
import vn.supperapp.apigw.db.dto.TransactionTemplate;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class FeatureInfoResponse<T> extends BaseResponse {
    private TipMessageInfo messageInfo;
    private List<TransactionTemplate> recentTrans;
    private List<T> data;
    private IntroductionInfo introduction;
    public FeatureInfoResponse() {
    }

    public FeatureInfoResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public TipMessageInfo getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(TipMessageInfo messageInfo) {
        this.messageInfo = messageInfo;
    }

    public List<TransactionTemplate> getRecentTrans() {
        return recentTrans;
    }

    public void setRecentTrans(List<TransactionTemplate> recentTrans) {
        this.recentTrans = recentTrans;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public IntroductionInfo getIntroduction() {
        return introduction;
    }

    public void setIntroduction(IntroductionInfo introduction) {
        this.introduction = introduction;
    }
}
