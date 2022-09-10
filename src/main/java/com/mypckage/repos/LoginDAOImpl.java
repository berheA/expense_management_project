package com.mypackage.repos;

import com.mypackage.models.Users;
import com.mypackage.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO{

    /**
     * Used by Finance Manager and employee to login
     */
    @Override
    public Users login(String username) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM ers_users INNER JOIN ers_user_roles ON ers_users.user_role_id = " +
                    "ers_user_roles.ers_user_role_id " +
                    "WHERE ers_username=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            Users user = null;
            if(result.next()) {
                user = new Users();
                user.setUserID(result.getInt("ers_users_id"));
                user.setUserName(result.getString("ers_username"));
                user.setFirstName(result.getString("ers_firstname"));
                user.setLastName(result.getString("ers_lastname"));
                user.setEmail(result.getString("ers_email"));
                user.setSecret(result.getString("ers_password"));
                user.setRoleID(result.getString("ers_user_role"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
