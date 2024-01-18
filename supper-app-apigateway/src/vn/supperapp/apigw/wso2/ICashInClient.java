package vn.supperapp.apigw.wso2;

import vn.supperapp.apigw.wso2.objs.CashInServiceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICashInClient {

    @GET("/services/Mobile_DataService/FIND_AGENT_BY_DISTANCE_operator")
    Call<CashInServiceResponse> getListAgent(@Query("P_LAT") double lat, @Query("P_LON") double lon, @Query("P_DISTANCE") double distance);
}
