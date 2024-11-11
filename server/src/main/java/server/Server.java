package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import model.LoginResponse;
import model.UserData;
import spark.*;

import service.UserService;

import java.util.Map;

public class Server {

    private UserService userService;

    private UserDAO userDao;

    public Server(){
        userDao = new MemoryUserDAO();

        this.userService = new UserService(userDao);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/session", this::login);


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
        UserData user = new Gson().fromJson(req.body(), model.UserData.class);
        LoginResponse response = userService.login(user);

        res.body(String.valueOf(response));
        return res;
    }

}
