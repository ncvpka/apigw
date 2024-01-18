package vn.supperapp.apigw.restful.models.payrequest;

import vn.supperapp.apigw.db.dto.PaymentInfoUser;
import vn.supperapp.apigw.db.dto.PaymentRequest;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class PaymentInfoUserResponse extends BaseResponse {
    private List<PaymentInfoUser> list;
    private PaymentInfoUser paymentInfoUser;

    public PaymentInfoUserResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<PaymentInfoUser> getList() {
        return list;
    }

    public void setList(List<PaymentInfoUser> list) {
        this.list = list;
    }

    public PaymentInfoUser getPaymentInfoUser() {
        return paymentInfoUser;
    }

    public void setPaymentInfoUser(PaymentInfoUser paymentInfoUser) {
        this.paymentInfoUser = paymentInfoUser;
    }
}
