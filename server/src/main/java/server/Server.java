package server;

import com.google.gson.Gson;
import dataaccess.*;
import model.AuthData;
import service.AuthService;
import service.GameService;
import service.InvalidUserException;
import service.requestresult.*;
import model.UserData;
import spark.*;

import service.UserService;

import java.util.Map;

public class Server {

    private UserService userService;
    private AuthService authService;
    private GameService gameService;
    private UserDAO userDao;
    private AuthDAO authDao;
    private GameDAO gameDao;

    public Server(){
        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        gameDao = new MemoryGameDAO();
        this.userService = new UserService(userDao,authDao,gameDao);
        this.authService = new AuthService(userDao,authDao,gameDao);
        this.gameService = new GameService(userDao,authDao,gameDao);
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
        JoinGameRequest tempReq = new Gson().fromJson(request.body(), JoinGameRequest.class);
        JoinGameRequest request1 = new JoinGameRequest(request.headers("authorization"), tempReq.playerColor(), tempReq.gameID());

        if (request1.authorization() == null || request1.playerColor() == null|| request1.gameID() < 1 || request1.gameID() > gameDao.getNumGames()) {
            response.status(400);
            return new Gson().toJson(new ErrorResponse("Error: bad request"));
        }
        CreateGameResult result;
        try {
            result = gameService.joinGame(request1);
        } catch (InvalidUserException e) {
            response.status(401);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        } catch (DataAccessException e) {
            response.status(403);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        }
        response.status(200);
        return new Gson().toJson(result);
    }

    private Object create(Request request, Response response) {
        CreateGameRequest tempReq = new Gson().fromJson(request.body(), CreateGameRequest.class);
        CreateGameRequest request1 = new CreateGameRequest(request.headers("authorization"), tempReq.gameName());
        int numGames = gameDao.getNumGames();
        if (request1.gameName().isEmpty()) {
            response.status(400);
            return new Gson().toJson(new ErrorResponse("Error: bad request"));
        }
        CreateGameResult response1;
        try {
            response1 = gameService.createGame(request1);
        } catch (InvalidUserException exception) {
            response.status(401);
            return new Gson().toJson(new ErrorResponse(exception.getMessage()));
        } catch (DataAccessException exception) {
            response.status(500);
            return new Gson().toJson(new ErrorResponse(exception.getMessage()));
        }
        response.status(200);
        return new Gson().toJson(response1);
    }

    private Object list(Request request, Response response) {
        ListGameResult response1 = null;
        try{
            response1 = gameService.listGames(request.headers("authorization"));
        } catch (InvalidUserException e) {
            response.status(401);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        } catch (DataAccessException e){
            response.status(500);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        }
        response.status(200);
        return new Gson().toJson(response1);
    }

    private Object logout(Request request, Response response) {
        try{
            authService.logout(request.headers("authorization"));
        } catch (InvalidUserException e) {
            response.status(401);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        } catch (DataAccessException e){
            response.status(500);
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        }
        response.status(200);
        return "{}";
    }

    private Object register(Request request, Response response) {
        UserData user = new Gson().fromJson(request.body(), model.UserData.class);
        RegisterResult response1 = null;
        if((user.username()==null)||(user.password()==null)||(user.email()==null)){
            response.status(400);
            return new Gson().toJson(new ErrorResponse("Error: bad request"));
        }
        try {
            response1 = userService.register(user);
        } catch (InvalidUserException e) {
            response.status(403);
            response.body(e.getMessage());
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        } catch (DataAccessException e) {
            response.status(500);
            response.body(e.getMessage());
            return new Gson().toJson(new ErrorResponse(e.getMessage()));
        }
        response.status(200);
        response.body(response1.toString());
        return new Gson().toJson(response1);
    }

    private Object clear(Request request, Response response) throws DataAccessException {
        try {
            userDao = new MemoryUserDAO();
            authDao = new MemoryAuthDAO();
            gameDao = new MemoryGameDAO();
            this.userService = new UserService(userDao, authDao, gameDao);
            this.authService = new AuthService(userDao, authDao, gameDao);
            this.gameService = new GameService(userDao, authDao, gameDao);
            response.status(200);
            return "{}";
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    private Object login(Request req, Response res) throws DataAccessException {
        UserData user = new Gson().fromJson(req.body(), model.UserData.class);
        LoginResult response = null;
        try {
            response = userService.login(user);
        }
        catch (InvalidUserException exception){
            res.status(401);
            return new Gson().toJson(new ErrorResponse(exception.getMessage()));
        }
        catch (DataAccessException exception) {
            res.status(500);
            res.body(exception.getMessage());
            return new Gson().toJson(new ErrorResponse(exception.getMessage()));
        }
        res.status(200);
        return new Gson().toJson(response);
    }

}
