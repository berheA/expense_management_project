package com.mypackage.repos;

import com.mypackage.models.Reimb;
import com.mypackage.models.Users;

import java.util.ArrayList;

public interface CommonDAO {

    ArrayList<Reimb> viewAll(int userID, String queryType);
    ArrayList<Users> viewAll(String queryType);
}
