package com.mypackage.repos;

import com.mypackage.models.UserDTO;
import com.mypackage.models.Users;

public interface LoginDAO {
    Users login(String username);
}
