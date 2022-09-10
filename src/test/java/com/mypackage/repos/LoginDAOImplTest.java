package com.mypackage.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDAOImplTest {

    private LoginDAO loginDAO = new LoginDAOImpl();
    String username;

    @Test
    @DisplayName("Example of when a user in DB logs in, returned user should not be null")
    void loginTest(){
        username = "bobby";
        assertNotNull(loginDAO.login(username));
    }

    @Test
    @DisplayName("Example of when a user not in DB logs in, returned user should be null")
    void loginFail(){
        username = "Rob";
        assertNull(loginDAO.login(username));
    }

    @Test
    @DisplayName("No username was provided")
    void loginFailNoUsername(){
        assertNull(loginDAO.login(null));
    }
}
