package com.mypackage.repos;

import com.mypackage.models.Reimb;
import com.mypackage.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class EmployeeDAOImpl implements EmployeeDAO{

    /**
     * Method that will allow an employee to add a new request
     * this should set the time of the request and set the statusID to Pending as the default
     */
    @Override
    public Reimb addRequest(Reimb reimb) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String typeID = "SELECT reimb_type_id FROM ers_reimbursement_type WHERE reimb_type =?;";
            PreparedStatement s = conn.prepareStatement(typeID);
            s.setString(1, reimb.getTypeID().toUpperCase(Locale.ROOT));
            ResultSet r = s.executeQuery();

            if(r.next()) {

                String sql =
                        "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_type_id) " +
                                "VALUES ( CAST (? AS NUMERIC), (SELECT CURRENT_TIMESTAMP), CAST (? AS INTEGER) , CAST (? " +
                                "AS INTEGER) ) RETURNING *;";

                PreparedStatement statement = conn.prepareStatement(sql);

                //TODO : add description and receipt...
                int count = 0;
                statement.setString(++count, Double.toString(reimb.getAmount()));
                statement.setString(++count, Integer.toString(reimb.getAuthor()));
                statement.setString(++count, r.getString("reimb_type_id"));
                ResultSet result = statement.executeQuery();
                Reimb reimb_added = null;
                if (result.next()) {
                reimb_added = new Reimb(result.getInt("reimb_id"),
                        result.getDouble("reimb_amount"),
                        result.getString("reimb_submitted"),
                        result.getString("reimb_resolved"),
                        result.getString("reimb_description"),
                        result.getBlob("reimb_receipt"),
                        result.getInt("reimb_author"),
                        result.getInt("reimb_resolver"),
                        "PENDING",
                        reimb.getTypeID());
                }
                return reimb_added;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
