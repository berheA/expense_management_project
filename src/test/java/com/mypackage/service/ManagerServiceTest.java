package com.mypackage.service;

import com.mypackage.models.EditTicketDTO;
import com.mypackage.models.Reimb;
import com.mypackage.repos.EmployeeDAO;
import com.mypackage.repos.ManagerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ManagerServiceTest {

    private ManagerService managerService= new ManagerService();
    @Mock
    private ManagerDAO managerDAO;
    private EditTicketDTO editTicketDTO;

    @BeforeEach
    public void setUp(){
        managerDAO = Mockito.mock(ManagerDAO.class);
        editTicketDTO= new EditTicketDTO();
        managerService= new ManagerService(managerDAO);

    }
    @Test
    @DisplayName("Manager Test")
    public void managerSecuss(){
        editTicketDTO.status = "Approved";
        editTicketDTO.reimbID = 3;
        Mockito.when(managerDAO.editRequests(editTicketDTO)).thenReturn(new Reimb());
        assertNotNull(managerService.editRequests(editTicketDTO));
    }

    @Test
    @DisplayName("Manager Test")
    public void managerFailure(){
        Mockito.when(managerDAO.editRequests(editTicketDTO)).thenReturn(null);
        assertNull(managerService.editRequests(editTicketDTO));
    }

        @Test
    @DisplayName("bad reimb id returns null")
    void editTicketFailBadReimbID(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Approved"; //2==approved, 3==denied
        editTicketDTO.reimbID = 0;
        assertNull(managerService.editRequests((editTicketDTO)));
    }

    @Test
    @DisplayName("bad status type provided - null is returned")
    void editTicketFailBadStatus(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Waiting"; //2==approved, 3==denied
        editTicketDTO.reimbID = 2;
        assertNull(managerService.editRequests((editTicketDTO)));
    }


    @Test
    @DisplayName("tries to reset to pending")
    void editTicketFailPendingAgain(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Pending"; //2==approved, 3==denied
        editTicketDTO.reimbID = 2;
        assertNull(managerService.editRequests((editTicketDTO)));
    }

    @Test
    @Order(1)
    @DisplayName("can approve again")
    void editTicketCanApproveAgain(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Approved"; //2==approved, 3==denied
        editTicketDTO.reimbID = 2;
        Mockito.when(managerDAO.editRequests(editTicketDTO)).thenReturn(new Reimb());
        assertNotNull(managerService.editRequests((editTicketDTO)));
    }

    @Test
    @Order(2)
    @DisplayName("can deny again")
    void editTicketDenyAgain(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Denied"; //2==approved, 3==denied
        editTicketDTO.reimbID = 2;
        Mockito.when(managerDAO.editRequests(editTicketDTO)).thenReturn(new Reimb());
        assertNotNull(managerService.editRequests((editTicketDTO)));
    }
}
