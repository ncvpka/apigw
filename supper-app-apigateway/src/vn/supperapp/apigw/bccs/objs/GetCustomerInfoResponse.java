/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.bccs.objs;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * @author TruongLe
 */
@Root(name = "S:Envelope", strict = false)
@NamespaceList({
        @Namespace(prefix = "S", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetCustomerInfoResponse {
    @Element(name = "Body")
    private Body body;

    public Body getBody() {
        return body;
    }

    @Root(name = "Body")
    public static class Body {
        @Element(name = "wsViewAccontAndEmailResponse", required = false)
        private ViewAccontAndEmailResponse viewAccontAndEmailResponse;

        public ViewAccontAndEmailResponse getViewAccontAndEmailResponse() {
            return viewAccontAndEmailResponse;
        }
    }

    @Root(name = "wsViewAccontAndEmailResponse")
    @Namespace(prefix = "ns2", reference = "http://webservices.vas.viettel.com/")
    public static class ViewAccontAndEmailResponse {
        @Element(name = "return", required = false)
        private ReturnViewAccontAndEmail returnViewAccontAndEmail;

        public ReturnViewAccontAndEmail getReturnViewAccontAndEmail() {
            return returnViewAccontAndEmail;
        }

        public void setReturnViewAccontAndEmail(ReturnViewAccontAndEmail returnViewAccontAndEmail) {
            this.returnViewAccontAndEmail = returnViewAccontAndEmail;
        }
    }

    @Root(name = "return")
    public static class ReturnViewAccontAndEmail {

        @Element(name = "content", required = false)
        public String content;

        @Element(name = "errorCode", required = false)
        public Integer errorCode;

        @Element(name = "address", required = false)
        public String address;

        @Element(name = "contractDebt", required = false)
        public Float contractDebt;

        @Element(name = "contractNo", required = false)
        public String contractNo;

        @Element(name = "hotcharge", required = false)
        public Float hotcharge;

        @Element(name = "payer", required = false)
        public String payer;

        @Element(name = "payment", required = false)
        public Float payment;

        @Element(name = "service", required = false)
        public String service;

        @Element(name = "status", required = false)
        public String status;

        @Element(name = "subId", required = false)
        public String subId;

        @Element(name = "telFax", required = false)
        public String telFax;


    }


}



