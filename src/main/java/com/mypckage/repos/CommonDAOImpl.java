package com.mypackage.repos;

import com.mypackage.models.Reimb;
import com.mypackage.models.Users;
import com.mypackage.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class CommonDAOImpl implements CommonDAO {

    public CommonDAOImpl() {
    }


    /**
     * Method used by Employee since they can only view their Reimbursements
     * This could be used by a manager if they are searching by a specific employee
     * @param userID
     * @param queryType
     * @return
     */
    @Override
    public ArrayList<Reimb> viewAll(int userID, String queryType) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql;
            PreparedStatement statement;

            if(queryType.equalsIgnoreCase("all")) {
                sql = "SELECT * FROM ers_reimbursement " +
                        "INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = " +
                        "ers_reimbursement_status.reimb_status_id " +
                        "INNER JOIN ers_reimbursement_type  ON ers_reimbursement.reimb_type_id = " +
                        "ers_reimbursement_type.reimb_type_id " +
                        "WHERE ers_reimbursement.reimb_author =CAST (? AS INT);";
                statement = conn.prepareStatement(sql);
                statement.setString(1, Integer.toString(userID));
            } else {
                queryType = queryType.toUpperCase(Locale.ROOT);
                sql = "SELECT * FROM ers_reimbursement " +
                        "INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = " +
                        "ers_reimbursement_status.reimb_status_id " +
                        "INNER JOIN ers_reimbursement_type  ON ers_reimbursement.reimb_type_id = " +
                        "ers_reimbursement_type.reimb_type_id " +
                        "WHERE ers_reimbursement.reimb_author =CAST(? AS INT) AND reimb_status = ?;";
                statement = conn.prepareStatement(sql);
                statement.setString(1, Integer.toString(userID));
                statement.setString(2, queryType);
            }

            ArrayList<Reimb> reimbs = null;
            ResultSet result = statement.executeQuery();
                reimbs = new ArrayList<>();
                Reimb r = new Reimb();
                while (result.next()) {
                    r.setId(result.getInt("reimb_id"));
                    r.setAmount(result.getDouble("reimb_amount"));
                    r.setTimeSubmitted(result.getString("reimb_submitted"));
                    r.setTimeResolved(result.getString("reimb_resolved"));
                    r.setDescription(result.getString("reimb_description"));
                    //reimb_receipt
                    r.setAuthor(result.getInt("reimb_author"));
                    r.setResolver(result.getInt("reimb_resolver"));
                    r.setStatusID(result.getString("reimb_status"));
                    r.setTypeID(result.getString("reimb_type"));
                    reimbs.add(r);
                }

            return reimbs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used by Financial manager, since they can view all employees
     * @param queryType
     * @return
     */
    @Override
    public ArrayList<Users> viewAll(String queryType) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql;
            PreparedStatement statement;

            if(queryType.equalsIgnoreCase("all")) {
                sql = "SELECT * FROM ers_users " +
                        "INNER JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id;";

                statement = conn.prepareStatement(sql);
            } else {
                sql = "SELECT " +
                        "DISTINCT ers_users.ers_username,ers_reimbursement.reimb_author, " +
                        "ers_users.ers_email, ers_users.ers_firstname, ers_users.ers_lastname, ers_users" +
                        ".ers_users_id, " +
                        "ers_user_roles.ers_user_role " +
                        "FROM ers_users " +
                        "INNER JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id " +
                        "INNER JOIN ers_reimbursement ON ers_users.ers_users_id = ers_reimbursement.reimb_author " +
                        "INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = " +
                        "ers_reimbursement_status.reimb_status_id " +
                        "WHERE ers_reimbursement_status.reimb_status = ?; ";

                statement = conn.prepareStatement(sql);
                statement.setString(1, queryType.toUpperCase(Locale.ROOT));
            }

            ResultSet result = statement.executeQuery();

            ArrayList<Users> users = new ArrayList<>();
            Users u;
            while(result.next()) {
                u= new Users();
                u.setUserID(result.getInt("ers_users_id"));
                u.setUserName(result.getString("ers_username"));
                u.setFirstName(result.getString("ers_firstname"));
                u.setLastName(result.getString("ers_lastname"));
                u.setEmail(result.getString("ers_email"));
                u.setRoleID(result.getString("ers_user_role"));
                u.setReimbs(new ArrayList<>());
                users.add(u);
            }

            //now go through each user in the users array, and append their reimbursements
            for(Users r: users) {
                ArrayList<Reimb> reimbs = viewAll(r.getUserID(), queryType);
                r.setReimbs(reimbs);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
