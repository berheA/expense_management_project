package com.mypackage.repos;

import com.mypackage.models.Reimb;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeDAOImplTest {

    private  EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    private Reimb reimb;

    @Test
    @DisplayName("Employee Implementation Testing, when a user successfully adds a reimbursement request")
    public void EmployeeSuccess(){
        reimb = new Reimb();
        reimb.setAmount(400.02);
        reimb.setAuthor(1);
        reimb.setTypeID("FOOD");
        assertNotNull(employeeDAO.addRequest(reimb));
    }


    @Test
    @DisplayName("Invalid type selected")
    public void EmployeeFailBadType(){
        reimb = new Reimb();
        reimb.setAmount(400.02);
        reimb.setAuthor(1);
        reimb.setTypeID("Parking");
        assertNull(employeeDAO.addRequest(reimb));
    }

    @Test
    @DisplayName("Invalid amount")
    public void EmployeeFailNegativeAmount(){
        reimb = new Reimb();
        reimb.setAmount(-400.02);
        reimb.setAuthor(1); //from session
        reimb.setTypeID("Food");
        assertNull(employeeDAO.addRequest(reimb));
    }
}
