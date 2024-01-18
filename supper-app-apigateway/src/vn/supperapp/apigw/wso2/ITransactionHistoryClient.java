package vn.supperapp.apigw.wso2;

import vn.supperapp.apigw.wso2.objs.TransactionDetailResponse;
import vn.supperapp.apigw.wso2.objs.TransactionHistoryResponse;
import vn.supperapp.apigw.wso2.objs.TransactionTypeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITransactionHistoryClient {

    @GET("/services/MobileApp_DataService.SOAP12Endpoint/select_transaction_type_operation")
    Call<TransactionTypeResponse> getTransactionType();

    @GET("/services/MobileApp_DataService/select_transaction_history_operation")
    Call<TransactionHistoryResponse> getListTransHistory(@Query("fromDate") String fromDate, @Query("toDate") String toDate, @Query("carriedPhone") String carriedPhone, @Query("transType") String transType,
                                                         @Query("offset") int offset, @Query("limit") int limit);

    @GET("/services/MobileApp_DataService.SOAP12Endpoint/select_transaction_detail_by_transaction_id_operation")
    Call<TransactionDetailResponse> getDetailTransaction(@Query("transactionId") String transactionId);

}
