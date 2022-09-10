package com.mypackage.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommonDAOImplTest {
    private CommonDAO dao = new CommonDAOImpl();
    private int userID;
    private String queryType;

    @Test
    @DisplayName("Test when an employee would like to look at all of their pending requests")
    void viewAllTestPENDING(){
        userID = 2;
        queryType = "PENDING";
        assertEquals(true,dao.viewAll(userID, queryType).size() > 0);
    }
    @Test
    @DisplayName("Test when an employee would like to look at all of their Denied requests, but no denied available")
    void viewAllTestDENIED(){
        userID = 2;
        queryType = "DENIED";
        assertEquals(true,dao.viewAll(userID, queryType).size() > 0);
    }

    @Test
    @DisplayName("Test when an employee would like to look at all of their approved requests, but no denied available")
    void viewAllTestApproved(){
        userID = 2;
        queryType = "APPROVED";
        assertEquals(true,dao.viewAll(userID, queryType).size() == 0);
    }

    @Test
    @DisplayName("Test when a employee would like to look at all of their requests")
    void viewAllTestQueries(){
        userID=2;
        queryType = "ALL";
        assertEquals(true,dao.viewAll(userID, queryType).size() > 0);
    }


}
