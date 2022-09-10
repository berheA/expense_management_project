package com.mypackage.service;

import com.mypackage.exceptions.MyException;
import com.mypackage.models.Reimb;
import com.mypackage.repos.EmployeeDAO;
import com.mypackage.repos.EmployeeDAOImpl;

public class EmployeeService {
    //private EmployeeDAO dao = new EmployeeDAOImpl();

private  EmployeeDAO empDAO;
    public EmployeeService() {
        empDAO=new EmployeeDAOImpl();
    }

    public EmployeeService(EmployeeDAO empDAO) {
        this.empDAO = empDAO;
    }

    /**
     * used by the Employee to submit a request for reimbursement
     * @param reimb
     * @return the Reimb if successfully added, else null
     */
    public Reimb addRequest(Reimb reimb){
        try {
            Validator.isValidAmount(reimb.getAmount());
            Validator.isValidType(reimb.getTypeID());
            Reimb rr = empDAO.addRequest(reimb);
            if (rr == null) {
                return null;
            }
            return rr;
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
