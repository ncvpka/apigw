package vn.supperapp.apigw.bccs.service;

import vn.supperapp.apigw.bccs.IBccsClient;
import vn.supperapp.apigw.bccs.objs.GetCustomerInfoRequest;
import vn.supperapp.apigw.bccs.objs.GetCustomerInfoResponse;
import vn.supperapp.apigw.bccs.objs.GetExchangeRateRequest;
import vn.supperapp.apigw.bccs.objs.GetExchangeRateResponse;
import org.apache.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;


public class PaymentService {

    private static final Logger logger = Logger.getLogger(PaymentService.class.getSimpleName());

    public GetExchangeRateResponse getExchangeRate() throws Exception {

        logger.info("#getExchangeRate");
        IBccsClient service = ServiceGenerator.getInstanceBCCS().createServiceBCCS(IBccsClient.class);
        Call<GetExchangeRateResponse> call = service.getExchangeRate(new GetExchangeRateRequest(ServiceGenerator.getInstanceBCCS().config.getWsUser(), ServiceGenerator.getInstanceBCCS().config.getWsPass()));
        Response<GetExchangeRateResponse> response = call.execute();
        logger.info("#getExchangeRate - response status " + response.isSuccessful());
        if (response.isSuccessful()) {
            GetExchangeRateResponse accountResponse = response.body();
            logger.info("#getExchangeRate - response " + accountResponse.toString());
            return accountResponse;
        }
        logger.info("#getExchangeRate - response null");
        return null;
    }

    public GetCustomerInfoResponse getCustomerInfo(String accountId) throws Exception {

        logger.info("#getCustomerInfo");
        IBccsClient service = ServiceGenerator.getInstanceBCCS().createServiceBCCS(IBccsClient.class);
        Call<GetCustomerInfoResponse> call = service.getCustomerInformation(new GetCustomerInfoRequest(ServiceGenerator.getInstanceBCCS().config.getWsUser(),
                ServiceGenerator.getInstanceBCCS().config.getWsPass(), accountId));
        Response<GetCustomerInfoResponse> response = call.execute();
        logger.info("#getCustomerInfo - response status " + response.isSuccessful());
        if (response.isSuccessful()) {
            GetCustomerInfoResponse accountResponse = response.body();
            logger.info("#getCustomerInfo - response " + accountResponse.toString());
            return accountResponse;
        }
        logger.info("#getCustomerInfo - response null");
        return null;
    }

    public static void main(String[] args) throws Exception{
        PaymentService as = new PaymentService();
        GetExchangeRateResponse response = as.getExchangeRate();
        if (response !=null && response.getBody().getExchangeRateInfoResponse().getReturnExchangeRate().errorCode == 0){
            logger.info("#getExchangeRate - value:" + response.getBody().getExchangeRateInfoResponse().getReturnExchangeRate().content);
        }else{
            logger.info("#getExchangeRate - value:" + response.getBody().getExchangeRateInfoResponse().getReturnExchangeRate().content);
        }

        GetCustomerInfoResponse getCustomerInfoResponse = as.getCustomerInfo("33851111");
        if (getCustomerInfoResponse != null && getCustomerInfoResponse.getBody().getViewAccontAndEmailResponse().getReturnViewAccontAndEmail().errorCode == 0){
            logger.info("#getCustomerInfo - value:" + getCustomerInfoResponse.getBody().getViewAccontAndEmailResponse().getReturnViewAccontAndEmail().payer);
        }else{
            logger.info("#getCustomerInfo - value:" + getCustomerInfoResponse.getBody().getViewAccontAndEmailResponse().getReturnViewAccontAndEmail().content);
        }

    }

}
