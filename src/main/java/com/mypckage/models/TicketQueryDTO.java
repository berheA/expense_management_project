package com.mypackage.models;

public class TicketQueryDTO {

    public int userID = -1; //default value for userID, if not provided - this is when a manager would like to query all Reimbursements for all employees
    public String queryType;
}
