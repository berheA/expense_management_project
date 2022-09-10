package com.mypackage.controllers;
import com.mypackage.models.Reimb;
import com.mypackage.models.Roles;
import com.mypackage.service.EmployeeService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API used by only an Employee
 */
public class EmployeeController extends Controller {
    private EmployeeService service = new EmployeeService();
    private Logger log = LoggerFactory.getLogger(Controller.class);

    /**
     * Adds a request
     * Automatic status will be pending when submitted
     */
    private Handler addRequest = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            Reimb reimb = ctx.bodyAsClass(Reimb.class);
            reimb.setAuthor((int) ctx.req.getSession().getAttribute("UserID"));
            reimb = service.addRequest(reimb);
            if(reimb == null) {
                ctx.status(400);
            } else {
                ctx.json(reimb);
                ctx.status(201);
            }
        } else {
            ctx.status(401);
        }

    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/addRequest", addRequest, Roles.EMPLOYEE);
    }
}
