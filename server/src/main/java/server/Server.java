package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import spark.*;

import java.service.UserService;
import java.util.Map;

public class Server {

    private UserService userService;

    public Server(){
        UserDAO userDAO= new MemoryUserDAO();

        this.userService = new UserService(userDAO);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/session/:username/:password", this::login);


        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    private Object login(Request req, Response res) throws DataAccessException {
        var user = new Gson().fromJson(req.body(), model.UserData.class);
        var userdata = userService.getUser(user);

        //new Gson().toJson(Map.of(req))
        return null;
    }

}
