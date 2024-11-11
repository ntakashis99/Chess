package server;

import com.google.gson.Gson;
import dataaccess.*;
import model.LoginResponse;
import model.UserData;
import spark.*;

import service.UserService;

import java.util.Map;

public class Server {

    private UserService userService;
    private UserDAO userDao;
    private AuthDAO authDao;
    private GameDAO gameDao;

    public Server(){
        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        gameDao = new MemoryGameDAO();
        this.userService = new UserService(userDao,authDao,gameDao);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/session", this::login);
        Spark.delete("/db",this::clear);
        Spark.post("/user",this::register);
        Spark.delete("/session",this::logout);
        Spark.get("/game",this::list);
        Spark.post("/game",this::create);
        Spark.put("/game",this::join);


        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private Object join(Request request, Response response) {
        return null;
    }

    private Object create(Request request, Response response) {
        return null;
    }

    private Object list(Request request, Response response) {
        return null;
    }

    private Object logout(Request request, Response response) {
        return null;
    }

    private Object register(Request request, Response response) {
        return null;
    }

    private Object clear(Request request, Response response) {
        return null;
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    private Object login(Request req, Response res) throws DataAccessException {
        UserData user = new Gson().fromJson(req.body(), model.UserData.class);
        LoginResponse response = null;
        try {
            response = userService.login(user);
        } catch (DataAccessException exception) {
            res.status(401);
            res.body("Error: unauthorized");
            return new Gson().toJson(res);
        }
        res.status(200);
        res.body(String.valueOf(response));
        return new Gson().toJson(res);
    }

}
