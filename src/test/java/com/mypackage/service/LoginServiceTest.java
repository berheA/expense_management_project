package com.mypackage.service;
import com.mypackage.models.UserDTO;
import com.mypackage.models.Users;
import com.mypackage.repos.LoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


public class LoginServiceTest {

    private LoginService loginTest;

    @Mock
    private LoginDAO mockedDAO;
    private UserDTO testUser = new UserDTO();

    @BeforeEach
    public void setUp() {
        mockedDAO = Mockito.mock(LoginDAO.class);
        testUser.username = "agent";
        testUser.password = "password";
        loginTest = new LoginService(mockedDAO);
    }

    @Test
    @DisplayName("Null is returned when a user does not exist in DB or when incorrect username is provided")
    public void testLoginFailUserDoesNotExist() {
        Mockito.when(mockedDAO.login("agent")).thenReturn(null);
        assertNull(loginTest.login(testUser));
    }
    @Test
    @DisplayName("Null is returned when password does not match password in DB but user is in DB")
    public void testLoginFailBadPW() {
        Users user = new Users();
        user.setSecret("$argon2id$v=19$m=15360,t=2,p=1$dJPMlDO1PkHPDA9+Et1yVg$iMrA6hsJj2tmQzlrV0NgGjMJEyPSe0+fJUmmyhWhCno");
        testUser.password = "pass";
        Mockito.when(mockedDAO.login("agent")).thenReturn(user);
        assertNull(loginTest.login(testUser));
    }

    @Test
    @DisplayName("User logins in with correct username and password")
    public void testLoginSuccess() {
        Users user = new Users();
        testUser.password = "password";
        user.setSecret("$argon2id$v=19$m=15360,t=2,p=1$dJPMlDO1PkHPDA9+Et1yVg$iMrA6hsJj2tmQzlrV0NgGjMJEyPSe0+fJUmmyhWhCno");
        Mockito.when(mockedDAO.login("agent")).thenReturn(user);
        assertNotNull(loginTest.login(testUser));
    }

    @Test
    @DisplayName("User submits no username, get null as a return")
    public void testLoginNoUsername() {
        Users user = new Users();
        testUser.username = "";
        assertNull(loginTest.login(testUser));
    }

    @Test
    @DisplayName("User submits no password, get null as a return")
    public void testLoginNoPassword() {
        Users user = new Users();
        testUser.password = "";
        assertNull(loginTest.login(testUser));
    }



}


