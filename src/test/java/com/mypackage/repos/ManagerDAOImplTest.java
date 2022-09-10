package com.mypackage.repos;

import com.mypackage.models.EditTicketDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ManagerDAOImplTest {
    private ManagerDAO managerDAO = new ManagerDAOImpl();
    private EditTicketDTO editTicketDTO;


    @Test
    @Order(1)
    @DisplayName("Manager is able to successfully edit a reimbursement, will return a updated Reimb")
    void editTestSuccess(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Approved"; //2==approved, 3==denied
        editTicketDTO.reimbID = 3;
        editTicketDTO.resolverID=2;
        assertNotNull(managerDAO.editRequests(editTicketDTO));
    }

    @Test
    @Order(2)
    @DisplayName("Manager can deny and since following order, manager can change an approved to a deny")
    void editTestSuccessDenied(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Denied"; //2==approved, 3==denied
        editTicketDTO.reimbID = 3;
        editTicketDTO.resolverID=2;
        assertNotNull(managerDAO.editRequests(editTicketDTO));
    }

    @Test
    @Order(3)
    @DisplayName("Manager can deny and since following order, manager can change a deny to approve")
    void editTestSuccessChangeDenyToApprove(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Approved"; //2==approved, 3==denied
        editTicketDTO.reimbID = 3;
        editTicketDTO.resolverID =2;
        assertNotNull(managerDAO.editRequests(editTicketDTO));
    }

    @Test
    @DisplayName("manager tries to make reimb pending but this should fail")
    void editTestFailTryingToPendingAgain(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Pending"; //2==approved, 3==denied
        editTicketDTO.reimbID = 3;
        editTicketDTO.resolverID=2;
        assertNull(managerDAO.editRequests(editTicketDTO));
    }

    @Test
    @DisplayName("manager fails to provide ticket status")
    void editTestFailNoStatusProvided(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = ""; //2==approved, 3==denied
        editTicketDTO.reimbID = 3;
        editTicketDTO.resolverID=2;
        assertNull(managerDAO.editRequests(editTicketDTO));
    }

    @Test
    @DisplayName("bad reimbID provided")
    void editTestFailBadReimbID(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Pending"; //2==approved, 3==denied
        editTicketDTO.reimbID = 0;
        editTicketDTO.resolverID=2;
        assertNull(managerDAO.editRequests(editTicketDTO));
    }

    @Test
    @DisplayName("bad status type provided")
    void editTestFailBadTypeDoesNotExist(){
        editTicketDTO = new EditTicketDTO();
        editTicketDTO.status = "Waiting"; //2==approved, 3==denied
        editTicketDTO.reimbID = 3;
        editTicketDTO.resolverID=2;
        assertNull(managerDAO.editRequests(editTicketDTO));
    }
}
