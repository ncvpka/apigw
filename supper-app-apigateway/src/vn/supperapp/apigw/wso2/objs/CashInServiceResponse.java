package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;


public class CashInServiceResponse {

    @SerializedName("jsonObject")
    AgentCollection agentCollection;

    public AgentCollection getAgentCollection() {
        return agentCollection;
    }

    public void setAgentCollection(AgentCollection agentCollection) {
        this.agentCollection = agentCollection;
    }
}
