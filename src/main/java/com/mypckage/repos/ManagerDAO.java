package com.mypackage.repos;

import com.mypackage.models.EditTicketDTO;
import com.mypackage.models.Reimb;

public interface ManagerDAO {

    Reimb editRequests(EditTicketDTO editTicketDTO);
}
