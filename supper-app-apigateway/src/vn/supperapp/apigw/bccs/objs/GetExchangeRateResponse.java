/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.bccs.objs;

import org.simpleframework.xml.*;

import java.util.List;

/**
 *
 * @author TruongLe
 */
@Root(name = "S:Envelope", strict = false)
@NamespaceList({
        @Namespace(prefix = "S", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetExchangeRateResponse {
    @Element(name = "Body")
    private Body body;

    public Body getBody() {
        return body;
    }

    @Root(name = "Body")
    public static class Body{
        @Element(name = "getExchangeRateResponse", required= false)
        private ExchangeRateInfoResponse exchangeRateInfoResponse;

        public ExchangeRateInfoResponse getExchangeRateInfoResponse() {
            return exchangeRateInfoResponse;
        }
    }

    @Root(name = "getExchangeRateResponse")
    @Namespace(prefix = "ns2", reference = "http://webservices.vas.viettel.com/")
    public static class ExchangeRateInfoResponse {
        @Element(name = "return", required = false)
        private ReturnExchangeRate returnExchangeRate;

        public ReturnExchangeRate getReturnExchangeRate() {
            return returnExchangeRate;
        }

        public void setReturnExchangeRate(ReturnExchangeRate returnExchangeRate) {
            this.returnExchangeRate = returnExchangeRate;
        }
    }

    @Root(name = "return")
    public static class ReturnExchangeRate {

        @Element(name = "content", required = false)
        public String content;

        @Element(name = "errorCode", required = false)
        public Integer errorCode;




    }


}



