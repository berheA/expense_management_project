package com.mypackage.service;

import com.mypackage.exceptions.MyException;
import com.mypackage.models.UserDTO;
import com.mypackage.models.Users;
import com.mypackage.repos.LoginDAO;
import com.mypackage.repos.LoginDAOImpl;

public class LoginService {

    private LoginDAO dao;

    public LoginService() {
        dao = new LoginDAOImpl();
    }

    //Constructor used in testing
    public LoginService(LoginDAO dao) {
        this.dao = dao;
    }

    /**
     * Called by controller layer, takes a username and password.
     * This method will call to the dao layer using the username to grab user information
     * Then the hashed pw is compared with the inputted (validated) pw using Argon2Hasher
     * If they match, the User's information is returned
     * @param user inputted username
     * @return valid User in database else empty user
     */
    public Users login(UserDTO user){
        try {
            Validator.isValidUserName(user.username);
            Validator.isValidSecret(user.password);
            Users userOut = dao.login(user.username);
            if (userOut != null) {
                if (Argon2Hasher.verify(userOut.getSecret(), user.password)) {
                    return userOut;
                }
            }
            return null;
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
