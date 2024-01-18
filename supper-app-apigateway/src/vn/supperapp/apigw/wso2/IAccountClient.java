package vn.supperapp.apigw.wso2;



import vn.supperapp.apigw.wso2.objs.AccountResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAccountClient {
    @GET("/services/ACCOUNT_DataService/select_with_key_ACCOUNT_operation")
    Call<AccountResponse> getAccount(@Query("ACCOUNT_ID") Long accountId);

}
