package vn.supperapp.apigw.wso2.service;

import vn.supperapp.apigw.wso2.ITransactionHistoryClient;
import vn.supperapp.apigw.wso2.objs.TransactionDetailResponse;
import vn.supperapp.apigw.wso2.objs.TransactionHistoryResponse;
import vn.supperapp.apigw.wso2.objs.TransactionTypeResponse;
import org.apache.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class TransactionHistoryService {

    private static final Logger logger = Logger.getLogger(TransactionHistoryService.class.getSimpleName());

    public TransactionHistoryResponse getListTransactionHistory(String fromDate, String toDate, String carriedPhone, String transType, int offset, int limit) {
        logger.info("#getListTransactionHistory START -- ");
        try {

            ITransactionHistoryClient service = ServiceGenerator.getInstanceWSO2().createServiceWSO2(ITransactionHistoryClient.class);
            Call<TransactionHistoryResponse> call = service.getListTransHistory(fromDate, toDate, carriedPhone, transType, offset, limit);
            Response<TransactionHistoryResponse> response = call.execute();
            logger.info("#getListTransactionHistory - response status " + response.isSuccessful());
            if (response.isSuccessful()) {
                TransactionHistoryResponse transactionHistoryResponse = response.body();
                logger.info("#getListTransactionHistory - response " + transactionHistoryResponse.toString());
                return transactionHistoryResponse;
            }

        } catch (Exception e) {
            logger.error("#getListAllService - Error: ", e);
            e.printStackTrace();
        }
        return null;
    }

    public TransactionTypeResponse getTransactionType() {
        logger.info("#getTransactionType START -- ");
        try {

            ITransactionHistoryClient service = ServiceGenerator.getInstanceWSO2().createServiceWSO2(ITransactionHistoryClient.class);
            Call<TransactionTypeResponse> call = service.getTransactionType();
            Response<TransactionTypeResponse> response = call.execute();
            logger.info("#getTransactionType - response status " + response.isSuccessful());
            if (response.isSuccessful()) {
                TransactionTypeResponse typeResponse = response.body();
                logger.info("#getTransactionType - response " + typeResponse.toString());
                return typeResponse;
            }

        } catch (Exception e) {
            logger.error("#getTransactionType - Error: ", e);
            e.printStackTrace();
        }
        return null;
    }

    public TransactionDetailResponse getDetailTransaction(String transactionId) {
        logger.info("#getDetailTransaction START -- ");
        try {

            ITransactionHistoryClient service = ServiceGenerator.getInstanceWSO2().createServiceWSO2(ITransactionHistoryClient.class);
            Call<TransactionDetailResponse> call = service.getDetailTransaction(transactionId);
            Response<TransactionDetailResponse> response = call.execute();
            logger.info("#getDetailTransaction - response status " + response.isSuccessful());
            if (response.isSuccessful()) {
                TransactionDetailResponse typeResponse = response.body();
                logger.info("#getDetailTransaction - response " + typeResponse.toString());
                return typeResponse;
            }

        } catch (Exception e) {
            logger.error("#getDetailTransaction - Error: ", e);
            e.printStackTrace();
        }
        return null;
    }

}
