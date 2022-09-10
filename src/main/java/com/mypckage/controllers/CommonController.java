package com.mypackage.controllers;

import com.mypackage.models.Reimb;
import com.mypackage.models.Roles;
import com.mypackage.models.TicketQueryDTO;
import com.mypackage.service.CommonService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * API of common paths used by both Employee and Finance Manager
 */
public class CommonController extends Controller {
    private Logger log = LoggerFactory.getLogger(Controller.class);
    private CommonService service = new CommonService();

    /**
     * For an employee to view their past ticket, the request will need to contain their user ID, queryType (ALL,
     * PENDING, APPROVED, DENIED) and role (EMPLOYEE - for the AccessManager)
     * These values will be stored in a DTO object
     */
    private Handler viewTickets = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String role = (String) ctx.req.getSession().getAttribute("Role");
            TicketQueryDTO ticketQueryDTO = ctx.bodyAsClass(TicketQueryDTO.class);
            ArrayList<Reimb> reimbs = null;

            if (ticketQueryDTO.userID != -1) {
                reimbs = service.viewTickets(ticketQueryDTO, role);
            } else {
                reimbs = service.viewTickets(ticketQueryDTO);
            }

            if (reimbs == null) {
                ctx.status(400);
            } else {
                ctx.json(reimbs);
                ctx.status(200);
            }
        } else {
            ctx.status(401);
        }
    };

    private Handler employeeViewTickets = (ctx) -> {
        if(ctx.req.getSession(false)!=null) {
            String role = (String) ctx.req.getSession().getAttribute("Role");
            TicketQueryDTO ticketQueryDTO = new TicketQueryDTO();
            ArrayList<Reimb> reimbs = null;

            ticketQueryDTO.userID = (int) ctx.req.getSession().getAttribute("UserID");
            ticketQueryDTO.queryType = ctx.pathParam("queryType");

            reimbs = service.viewTickets(ticketQueryDTO, role);


            if (reimbs == null) {
                ctx.status(400);
            } else {
                ctx.json(reimbs);
                ctx.status(200);
            }
        } else {
            ctx.status(401);
        }

    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/tickets", viewTickets, Roles.MANAGER);
        app.get("/tickets/{queryType}", employeeViewTickets, Roles.EMPLOYEE);

    }
}
