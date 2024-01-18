package vn.supperapp.apigw.wso2.service;

import vn.supperapp.apigw.wso2.ICashInClient;
import vn.supperapp.apigw.wso2.objs.CashInServiceResponse;
import org.apache.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class CashInService {

    private static final Logger logger = Logger.getLogger(TransactionHistoryService.class.getSimpleName());

    public CashInServiceResponse getListAgentWso2(double lat, double lon, double distance) {
        logger.info("#getListAgentWso2 START -- ");
        try {

            ICashInClient service = ServiceGenerator.getInstanceWSO2().createServiceWSO2(ICashInClient.class);
            Call<CashInServiceResponse> call = service.getListAgent(lat, lon, distance);
            Response<CashInServiceResponse> response = call.execute();
            logger.info("#getListAgentWso2 - response status " + response.isSuccessful());
            if (response.isSuccessful()) {
                CashInServiceResponse cashInServiceResponse = response.body();
                logger.info("#getListAgentWso2 - response " + cashInServiceResponse.toString());
                return cashInServiceResponse;
            }

        } catch (Exception e) {
            logger.error("#getListAgentWso2 - Error: ", e);
            e.printStackTrace();
        }
        return null;
    }

}
