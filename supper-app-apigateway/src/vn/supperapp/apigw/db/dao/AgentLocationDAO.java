package vn.supperapp.apigw.db.dao;

import vn.supperapp.apigw.beans.AgentLocationInfo;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgentLocationDAO extends BaseDAO {
    public AgentLocationDAO() {
        this.logger = LogManager.getLogger(AgentLocationDAO.class);
    }

    public List<AgentLocationInfo> getListAgent(Session session, Double latitude, Double longitude, Double distance) throws Exception {
        logger.info("#getListAgent");
        List<AgentLocationInfo> list = new ArrayList<>();
        CallableStatement callableStatement;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("CALL FIND_AGENT_BY_DISTANCE(?,?,?,?)");
            SessionImpl sessionImpl = (SessionImpl) session;
            Connection conn = sessionImpl.connection();
            callableStatement = conn.prepareCall(sql.toString());

            callableStatement.setDouble(1, latitude);
            callableStatement.setDouble(2, longitude);
            callableStatement.setDouble(3, distance);
            callableStatement.registerOutParameter(4, -10);
            callableStatement.executeUpdate();
            AgentLocationInfo agentLocationInfo;
            ResultSet rs = (ResultSet) callableStatement.getObject(4);
            while (rs.next()) {
                agentLocationInfo = new AgentLocationInfo();
                agentLocationInfo.setName(rs.getString("NAME"));
                agentLocationInfo.setAgentCode(rs.getString("AGENT_CODE"));
                agentLocationInfo.setAppUserId(rs.getLong("APP_USER_ID"));
                agentLocationInfo.setAddress(rs.getString("ADDRESS"));
                agentLocationInfo.setAreaCode(rs.getString("AREA_CODE"));
                agentLocationInfo.setCountry(rs.getString("COUNTRY"));
                agentLocationInfo.setDistance(rs.getDouble("DISTANCE"));
                agentLocationInfo.setContractNo(rs.getString("CONTRACT_NO"));
                agentLocationInfo.setDistrict(rs.getString("DISTRICT"));
                agentLocationInfo.setLatitude(rs.getDouble("LATITUDE"));
                agentLocationInfo.setPhone(rs.getString("PHONE"));
                agentLocationInfo.setProvince(rs.getString("PROVINCE"));
                agentLocationInfo.setLongitude(rs.getDouble("LONGITUDE"));
                list.add(agentLocationInfo);
            }
        } catch (Exception ex) {
            logger.error("#getListAgent ERROR ", ex);
            throw ex;
        }
        return list;
    }
}
