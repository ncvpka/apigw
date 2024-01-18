/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.supperapp.apigw.db.dao;

import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.supperapp.apigw.db.dto.Account;
import vn.supperapp.apigw.db.dto.UserBank;

/**
 * @author Taink
 */
public class UserBankDAO extends BaseDAO {

    public UserBankDAO() {
        this.logger = LogManager.getLogger(UserBankDAO.class);
    }

}
