package vn.supperapp.apigw.clients.epayment.objs;

public class EPaymentResponse {
    private int status; //0: Success
    private String code;
    private String message;
    private Object data;
    private EMoneyCustomerInfo customer;
    private TransDetail txDetail;
    private TransDetail txRevert;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public EMoneyCustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(EMoneyCustomerInfo customer) {
        this.customer = customer;
    }

    public TransDetail getTxDetail() {
        return txDetail;
    }

    public void setTxDetail(TransDetail txDetail) {
        this.txDetail = txDetail;
    }

    public TransDetail getTxRevert() {
        return txRevert;
    }

    public void setTxRevert(TransDetail txRevert) {
        this.txRevert = txRevert;
    }

    public static EPaymentResponse build(int status, String code, String message) {
        EPaymentResponse response = new EPaymentResponse();
        response.setStatus(status);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static EPaymentResponse build(EPaymentResponse response, int status, String code, String message) {
        response.setStatus(status);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
