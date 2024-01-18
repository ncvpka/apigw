/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.bccs.objs;

/**
 *
 * @author TruongLe
 */
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace( prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "web", reference = "http://webservices.vas.viettel.com/")
})
public class GetExchangeRateRequest {
    @Element(name = "soapenv:Header")
    private String headerMessage = "";

    public GetExchangeRateRequest(String wsUser, String wsPass) {
        this.body = new Body(wsUser, wsPass);
    }

    @Element(name = "soapenv:Body")
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Root(name = "soapenv:Body")
    public static class Body{
        @Element(name = "web:getExchangeRate")
        private GetExchangeRate getExchangeRate;

        public Body(String wsUser, String wsPass) {
            this.getExchangeRate = new GetExchangeRate(wsUser, wsPass);
        }

        public GetExchangeRate getGetExchangeRate() {
            return getExchangeRate;
        }

        public void setGetExchangeRate(GetExchangeRate getExchangeRate) {
            this.getExchangeRate = getExchangeRate;
        }
    }

    @Root(name = "web:getExchangeRate")
    public static class GetExchangeRate {

        public GetExchangeRate(String wsUser, String wsPass) {
            this.wsUser = wsUser;
            this.wsPass = wsPass;
        }
        
        
        @Element(name = "wsUser")
        private String wsUser;

        @Element(name = "wsPass")
        private String wsPass;



    }
    
    
}

