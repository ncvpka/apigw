package vn.supperapp.apigw.wso2;

import vn.supperapp.apigw.wso2.objs.InviteResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InviteClient {

    @POST("/services/MobileApp_DataService.SOAP12Endpoint/select_phonenumber_ewallet_operation")
    Call<InviteResponse> getListAccountIsEWallet(@Query("phoneNumbers") String msisdn);

}
