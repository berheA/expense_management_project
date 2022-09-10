package com.mypackage;
import com.mypackage.controllers.*;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
 * Start of Javalin App
 */
public class App {

    private static Javalin app;

    public static void main(String[] args) {

        app = Javalin.create( c -> {
            c.accessManager(new AccessManagerConfigure());
            c.addStaticFiles("src/../FrontEnd-Angular/src", Location.EXTERNAL);


        });

        configure(new ManagerController(), new EmployeeController(), new CommonController(), new LoginController());
        app.start(7000);

    }

    public static void configure(Controller... controllers) {
        for(Controller c: controllers){
            c.addRoutes(app);
        }
    }


}