/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.bccs.objs;

/**
 * @author TruongLe
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "web", reference = "http://webservices.vas.viettel.com/")
})
public class GetCustomerInfoRequest {
    @Element(name = "soapenv:Header")
    private String headerMessage = "";

    public GetCustomerInfoRequest(String wsUser, String wsPass, String account) {
        this.body = new Body(wsUser, wsPass, account);
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
    public static class Body {
        @Element(name = "web:wsViewAccontAndEmail")
        private ViewAccontAndEmail viewAccontAndEmail;

        public Body(String wsUser, String wsPass, String accountId) {
            this.viewAccontAndEmail = new ViewAccontAndEmail(wsUser, wsPass, accountId);
        }

        public ViewAccontAndEmail getViewAccontAndEmail() {
            return viewAccontAndEmail;
        }

        public void setViewAccontAndEmail(ViewAccontAndEmail viewAccontAndEmail) {
            this.viewAccontAndEmail = viewAccontAndEmail;
        }
    }

    @Root(name = "web:getExchangeRate")
    public static class ViewAccontAndEmail {

        public ViewAccontAndEmail(String wsUser, String wsPass, String account) {
            this.User = wsUser;
            this.Pass = wsPass;
            this.Account = account;
            this.Email = "";
        }


        @Element(name = "User")
        private String User;

        @Element(name = "Pass")
        private String Pass;

        @Element(name = "Account")
        private String Account;

        @Element(name = "Email")
        private String Email;


    }


}

