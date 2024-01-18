package vn.supperapp.apigw.bccs;



import vn.supperapp.apigw.bccs.objs.GetCustomerInfoRequest;
import vn.supperapp.apigw.bccs.objs.GetCustomerInfoResponse;
import vn.supperapp.apigw.bccs.objs.GetExchangeRateRequest;
import vn.supperapp.apigw.bccs.objs.GetExchangeRateResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface IBccsClient {
    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/WsTopupOnline")
    Call<GetExchangeRateResponse> getExchangeRate(@Body GetExchangeRateRequest getExchangeRateRequest);

    @Headers({
            "Content-Type: text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/WsTopupOnline")
    Call<GetCustomerInfoResponse> getCustomerInformation(@Body GetCustomerInfoRequest getCustomerInfoRequest);
}
