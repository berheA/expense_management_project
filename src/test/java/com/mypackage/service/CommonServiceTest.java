package com.mypackage.service;


import com.mypackage.models.Reimb;
import com.mypackage.models.TicketQueryDTO;
import com.mypackage.repos.CommonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommonServiceTest {

    private CommonService commonService;

    @Mock
    private CommonDAO dao;
    private TicketQueryDTO ticketQueryDTO = new TicketQueryDTO();
    private String role;

    @BeforeEach
    public void setUp() {
        dao = Mockito.mock(CommonDAO.class);
        commonService = new CommonService(dao);
    }
    @Test
    @DisplayName("Arraylist of reimbs will be returned when valid query information provided")
    public void testViewAllSuccess() {
        ticketQueryDTO.queryType = "All";
        role = "Employee";
        ticketQueryDTO.userID = 1;
        Mockito.when(dao.viewAll(ticketQueryDTO.userID, role)).thenReturn(new ArrayList<Reimb>());
        assertNotNull(commonService.viewTickets(ticketQueryDTO, role));
    }

    @Test
    @DisplayName("Null will be returned when bad userID provided")
    public void testViewAllFailBadID() {
        ticketQueryDTO.queryType = "All";
        role = "Employee";
        ticketQueryDTO.userID = -1;
        Mockito.when(dao.viewAll(ticketQueryDTO.userID, role)).thenReturn(null);
        assertNull(commonService.viewTickets(ticketQueryDTO, role));
    }

    @Test
    @DisplayName("Null is return, when bad query type")
    public void testViewAllFailQueryType() {
        ticketQueryDTO.queryType = "Waiting";
        role = "Employee";
        ticketQueryDTO.userID = 1;
        Mockito.when(dao.viewAll(ticketQueryDTO.userID, role)).thenReturn(null);
        assertNull(commonService.viewTickets(ticketQueryDTO, role));
    }

    @Test
    @DisplayName("null returned when bad query type provided")
    public void testViewAllFailQuery2() {
        ticketQueryDTO.queryType = "Waiting";
        ticketQueryDTO.userID = 1;
        Mockito.when(dao.viewAll(ticketQueryDTO.queryType)).thenReturn(null);
        assertNull(commonService.viewTickets(ticketQueryDTO));
    }

}
