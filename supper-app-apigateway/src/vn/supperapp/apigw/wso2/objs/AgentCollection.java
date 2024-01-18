package vn.supperapp.apigw.wso2.objs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgentCollection {

    @SerializedName("fieldMap")
    private List<AgentLocation> listAgentLocation;

    public List<AgentLocation> getListAgentLocation() {
        return listAgentLocation;
    }

    public void setListAgentLocation(List<AgentLocation> listAgentLocation) {
        this.listAgentLocation = listAgentLocation;
    }
}
