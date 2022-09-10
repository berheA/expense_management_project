package com.mypackage.service;

import com.mypackage.models.Reimb;
import com.mypackage.repos.EmployeeDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeTest;

    @Mock
    private EmployeeDAO mockedDAO;
    private Reimb testReimb;


    @BeforeEach
    public void setUp() {
        mockedDAO = Mockito.mock(EmployeeDAO.class);
        testReimb= new Reimb();
        employeeTest = new EmployeeService(mockedDAO);
    }
@Test
    @DisplayName("Employee Test")
    public void employeeFailure(){
        Mockito.when(mockedDAO.addRequest(testReimb)).thenReturn(null);
        assertNull(employeeTest.addRequest(testReimb));
}
    @Test
    @DisplayName("Employee Test Success")
    public void employeeSuccess(){
        testReimb.setAmount(400.02);
        testReimb.setTypeID("Food");
        Mockito.when(mockedDAO.addRequest(testReimb)).thenReturn(new Reimb());
        assertNotNull(employeeTest.addRequest(testReimb));
    }

    @Test
    @DisplayName("Employee fails to provide Reimb type")
    public void EmployeeFailNoTypeProvided(){
        testReimb.setAmount(400.02);
        testReimb.setTypeID("");
        assertNull(employeeTest.addRequest(testReimb));
    }

    @Test
    @DisplayName("Employee fails to provide Reimb allowed type")
    public void EmployeeFailBadTypeProvided(){
        testReimb.setAmount(400.02);
        testReimb.setTypeID("Parking");
        assertNull(employeeTest.addRequest(testReimb));
    }

    @Test
    @DisplayName("Employee fails to provide valid amount")
    public void EmployeeFailBadAmount(){
        testReimb.setAmount(0);
        testReimb.setTypeID("Food");
        assertNull(employeeTest.addRequest(testReimb));
    }

}
