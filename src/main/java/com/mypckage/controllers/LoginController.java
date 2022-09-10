package com.mypackage.controllers;
import com.mypackage.models.UserDTO;
import com.mypackage.models.Users;
import com.mypackage.service.LoginService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * First layer for when a user logs in
 */
public class LoginController extends Controller {

    private LoginService service = new LoginService();
    private Logger log = LoggerFactory.getLogger(Controller.class);
    public LoginController(){}

    private Handler login = (ctx) -> {
        UserDTO user = ctx.bodyAsClass(UserDTO.class);
        //log.info("User " + user.username + " is trying to login...");
        Users user_out = service.login(user);
        if(user_out == null) {
            //log.info("Invalid credentials, unable to login.");
            ctx.req.getSession().invalidate();
            ctx.status(401);
        } else {
            //log.info("Login is successful!");
            ctx.json(user_out); //test
            HttpSession session = ctx.req.getSession();
            session.setAttribute("Role", user_out.getRoleID());
            session.setAttribute("UserID", user_out.getUserID());
            //can set other session attributes if needed...
            ctx.status(200);
        }
    };

    private Handler logout = (ctx) -> {
        if(ctx.req.getSession(false) == null) {
            //log.info("No one is currently signed in...unable to logout");
            ctx.status(400); //cannot log out if you never logged in the first place
        } else {
            //log.info("Successfully logged out");
            ctx.req.getSession().invalidate();
            ctx.status(200);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", login);
        app.post("/logout", logout);
    }
}
