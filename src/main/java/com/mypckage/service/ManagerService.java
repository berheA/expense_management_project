package com.mypackage.service;

import com.mypackage.exceptions.MyException;
import com.mypackage.models.EditTicketDTO;
import com.mypackage.models.Reimb;
import com.mypackage.repos.ManagerDAO;
import com.mypackage.repos.ManagerDAOImpl;

public class ManagerService {
    private ManagerDAO dao;

    public ManagerService() {
        dao= new ManagerDAOImpl();
    }

    public ManagerService(ManagerDAO dao) {
        this.dao = dao;
    }

    /**
     * Allows a manager to edit a request
     * @param editTicketDTO
     * @return the edited request with updated status
     */
    public Reimb editRequests(EditTicketDTO editTicketDTO){
        try {
            Validator.isValidUserId(editTicketDTO.reimbID);
            Validator.isValidEditType(editTicketDTO.status);
            Reimb edit = dao.editRequests(editTicketDTO);
            if (edit != null) {
                return edit;
            }
            return null;
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
