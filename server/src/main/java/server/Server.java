package server;

import com.google.gson.Gson;
import dataaccess.*;
import model.AuthData;
import service.InvalidUserException;
import service.requestresult.LoginResponse;
import model.UserData;
import service.requestresult.RegisterResponse;
import spark.*;

import service.UserService;

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
        AuthData auth = new Gson().fromJson(request.body(), model.AuthData.class);
//        try{
//
//        } catch (InvalidUserException e) {
//
//        }
        return null;
    }

    private Object register(Request request, Response response) {
        UserData user = new Gson().fromJson(request.body(), model.UserData.class);
        RegisterResponse response1 = null;
        if((user.username()==null)||(user.password()==null)||(user.email()==null)){
            response.status(400);
            response.body("Error: bad request");
            return response;
        }
        try {
            response1 = userService.register(user);
        } catch (DataAccessException e) {
            response.status(401);
            response.body(e.getMessage());
            return response;
        }
        response.status(200);
        response.body(response1.toString());
        return response;
    }

    private Object clear(Request request, Response response) throws DataAccessException {
        try {
            userDao.deleteUser();
            authDao.deleteAuth();
            gameDao.deleteGame();
        } catch (DataAccessException e){
            response.status(500);
            response.body(e.getMessage());
            return response;
        }
        response.status(200);
        response.body("{}");
        return response;
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
        }
        catch (InvalidUserException exception){
            res.status(401);
            res.body(exception.getMessage());
            return res;
        }
        catch (DataAccessException exception) {
            res.status(500);
            res.body(exception.getMessage());
            return res;
        }
        res.status(200);
        res.body(String.valueOf(response));
        return res;
    }

}
