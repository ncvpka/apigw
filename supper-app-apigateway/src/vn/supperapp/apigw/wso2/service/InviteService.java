package vn.supperapp.apigw.wso2.service;

import vn.supperapp.apigw.wso2.InviteClient;
import vn.supperapp.apigw.wso2.objs.InviteResponse;
import org.apache.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class InviteService {
    private static final Logger logger = Logger.getLogger(InviteService.class.getSimpleName());

    public InviteResponse getListAccountIsEWallet(String msisdnArr) {
        logger.info("#getListAccountIsEWallet START -- ");
        try {

            InviteClient service = ServiceGenerator.getInstanceWSO2().createServiceWSO2(InviteClient.class);
            Call<InviteResponse> call = service.getListAccountIsEWallet(msisdnArr);
            Response<InviteResponse> response = call.execute();
            logger.info("#getListAccountIsEWallet - response status " + response.isSuccessful());
            if (response.isSuccessful()) {
                InviteResponse typeResponse = response.body();
                logger.info("#getListAccountIsEWallet - response " + typeResponse.toString());
                return typeResponse;
            }

        } catch (Exception e) {
            logger.error("#getListAccountIsEWallet - Error: ", e);
            e.printStackTrace();
        }
        return null;
    }

}
