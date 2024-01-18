package vn.supperapp.apigw.wso2.service;

import vn.supperapp.apigw.wso2.IAccountClient;
import vn.supperapp.apigw.wso2.objs.AccountResponse;
import org.apache.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;


public class AccountService {

    private static final Logger logger = Logger.getLogger(AccountService.class.getSimpleName());

    public AccountResponse getAccount(Long accountId) throws Exception {

        logger.info("#getAccount - get account with id " + accountId);
        IAccountClient service = ServiceGenerator.getInstanceWSO2().createServiceWSO2(IAccountClient.class);
        Call<AccountResponse> call = service.getAccount(accountId);
        Response<AccountResponse> response = call.execute();
        logger.info("#getAccount - response status " + response.isSuccessful());
        if (response.isSuccessful()) {
            AccountResponse accountResponse = response.body();
            logger.info("#getAccount - response " + accountResponse.toString());
            return accountResponse;
        }
        logger.info("#getAccount - response null");
        return null;
    }

    public static void main(String[] args) throws Exception{
        AccountService as = new AccountService();
        AccountResponse response = as.getAccount(32137385l);

    }

}
