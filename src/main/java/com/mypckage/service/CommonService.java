package com.mypackage.service;

import com.mypackage.exceptions.MyException;
import com.mypackage.models.Reimb;
import com.mypackage.models.TicketQueryDTO;
import com.mypackage.models.Users;
import com.mypackage.repos.CommonDAO;
import com.mypackage.repos.CommonDAOImpl;

import java.util.ArrayList;

public class CommonService<E> {
    private CommonDAO dao;

    public CommonService() {
        dao = new CommonDAOImpl();
    }

    public CommonService(CommonDAO dao) {
        this.dao = dao;
    }

    /**
     * A manager or employee may use this
     * An employee can choose to view all, pending, denied, and approved reimbursements that belongs to the employee.
     * A manager can choose to view all, pending, denied, and approved reimbursements of a specific employee
     * @param ticketQueryDTO
     * @return An arrayList of Reimbursements
     */
    public ArrayList viewTickets(TicketQueryDTO ticketQueryDTO, String role) {
        try {
            Validator.isValidUserId(ticketQueryDTO.userID);
            Validator.isValidQueryType(ticketQueryDTO.queryType);
            return dao.viewAll(ticketQueryDTO.userID, ticketQueryDTO.queryType);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Used by the manager to query tickets for all employees. The manager can view all, pending, denied, and
     * approved reimbursements.
     * @param ticketQueryDTO
     * @return an arrayList of Reimbursements
     */
    public ArrayList viewTickets(TicketQueryDTO ticketQueryDTO) {
        try {
            Validator.isValidQueryType(ticketQueryDTO.queryType);
            return dao.viewAll(ticketQueryDTO.queryType); //for manager
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
