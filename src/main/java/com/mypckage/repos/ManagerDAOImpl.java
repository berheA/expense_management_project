package com.mypackage.repos;
import com.mypackage.models.EditTicketDTO;
import com.mypackage.models.Reimb;
import com.mypackage.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ManagerDAOImpl implements ManagerDAO{

    /**
     * Manager can either approve or deny a request. Will return the updated Reimbursement
     * @param editTicketDTO
     * @return
     */
    @Override
    public Reimb editRequests(EditTicketDTO editTicketDTO) {
        if (!editTicketDTO.status.equalsIgnoreCase("pending")) {
            try (Connection conn = ConnectionUtil.getConnection()) {
                String statusID = "SELECT reimb_status_id FROM ers_reimbursement_status WHERE reimb_status =?;";
                PreparedStatement s = conn.prepareStatement(statusID);
                s.setString(1, editTicketDTO.status.toUpperCase(Locale.ROOT));
                ResultSet r = s.executeQuery();

                String currentStatus = "SELECT reimb_status_id FROM ers_reimbursement WHERE reimb_id=CAST(? AS INT);";
                PreparedStatement s2 = conn.prepareStatement(currentStatus);
                s2.setString(1, Integer.toString(editTicketDTO.reimbID));
                ResultSet r2 = s2.executeQuery();

                    if (r.next()) {
                        if(r2.next() && !(r.getString("reimb_status_id").equalsIgnoreCase(r2.getString("reimb_status_id")))) {
                        String sql = "UPDATE ers_reimbursement SET reimb_status_id=CAST (? AS INT), reimb_resolved = (SELECT " +
                                "CURRENT_TIMESTAMP), reimb_resolver= CAST (? AS INT) " +
                                "WHERE reimb_id = CAST (? AS INT) RETURNING *;";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1, r.getString("reimb_status_id"));
                        statement.setString(2, Integer.toString(editTicketDTO.resolverID));
                        statement.setString(3, Integer.toString(editTicketDTO.reimbID));
                        ResultSet result = statement.executeQuery();
                        Reimb reimb = null;
                        if (result.next()) {
                            reimb = new Reimb(result.getInt("reimb_id"), result.getDouble("reimb_amount"),
                                    result.getString("reimb_submitted"), result.getString("reimb_resolved"),
                                    result.getString("reimb_description"), result.getBlob("reimb_receipt"),
                                    result.getInt("reimb_author"), result.getInt("reimb_resolver"),
                                    editTicketDTO.status, "");
                        }
                        if (reimb != null) {
                            sql = "SELECT ers_reimbursement_type.reimb_type FROM " +
                                    "ers_reimbursement INNER JOIN ers_reimbursement_type ON " +
                                    "ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id " +
                                    "WHERE ers_reimbursement.reimb_id = CAST (? AS INT);";
                            statement = conn.prepareStatement(sql);
                            statement.setString(1, Integer.toString(reimb.getId()));
                            result = statement.executeQuery();
                            if (result.next()) {
                                reimb.setTypeID(result.getString("reimb_type"));
                            }
                        }
                        return reimb;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
