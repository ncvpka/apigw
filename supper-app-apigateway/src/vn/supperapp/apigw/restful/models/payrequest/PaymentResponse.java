package vn.supperapp.apigw.restful.models.payrequest;

import vn.supperapp.apigw.db.dto.Branch;
import vn.supperapp.apigw.db.dto.PaymentRequest;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class PaymentResponse extends BaseResponse {
    private List<PaymentRequest> list;
    private PaymentRequest payment;
    private List<Branch> branchList;

    public PaymentResponse(ErrorCode error, String language) {
        super(error, language);
    }

    public List<PaymentRequest> getList() {
        return list;
    }

    public void setList(List<PaymentRequest> list) {
        this.list = list;
    }

    public PaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(PaymentRequest payment) {
        this.payment = payment;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
