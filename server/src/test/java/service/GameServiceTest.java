package service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.requestresult.CreateGameRequest;
import service.requestresult.CreateGameResult;
import service.requestresult.JoinGameRequest;
import service.requestresult.RegisterResult;

public class GameServiceTest {
    @Test
    void listGames() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);
        RegisterResult r;
        r = userService.register(new UserData("nephi","1111","1@g"));

        gameService.createGame(new CreateGameRequest(r.authToken(),"game1"));
        Assertions.assertNotNull(gameService.listGames(r.authToken()));

    }

    @Test
    void listGamesFail() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        RegisterResult r;
        r = userService.register(new UserData("nephi","1111","1@g"));
        gameService.createGame(new CreateGameRequest(r.authToken(),"game1"));
        Assertions.assertThrows(InvalidUserException.class,()->gameService.createGame(new CreateGameRequest(null,null)));


    }

    @Test
    void createGame() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        RegisterResult r;
        r = userService.register(new UserData("nephi","1111","1@g"));

        gameService.createGame(new CreateGameRequest(r.authToken(),"game1"));
        Assertions.assertNotNull(gameService.listGames(r.authToken()));
    }

    @Test
    void createGameFail() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        Assertions.assertThrows(InvalidUserException.class,()->gameService.createGame(new CreateGameRequest(null,null)));
    }

    @Test
    void joinGame() throws Exception {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        RegisterResult r;
        r = userService.register(new UserData("nephi","1111","1@g"));
        gameService.createGame(new CreateGameRequest(r.authToken(),"game1"));

        CreateGameResult gameRes = gameService.joinGame(new JoinGameRequest(r.authToken(),"WHITE",1));

        var ans = gameService.listGames(r.authToken()).games().getFirst().whiteUsername();

        Assertions.assertEquals("nephi", ans);
    }

    @Test
    void joinGameFail() throws Exception {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        RegisterResult r;
        r = userService.register(new UserData("nephi","1111","1@g"));
        gameService.createGame(new CreateGameRequest(r.authToken(),"game1"));

        CreateGameResult gameRes = gameService.joinGame(new JoinGameRequest(r.authToken(),"WHITE",1));


        RegisterResult r2 = userService.register(new UserData("hana","1234","no@g"));
        Assertions.assertThrows(DataAccessException.class,()->gameService.joinGame(new JoinGameRequest(r2.authToken(),"WHITE",1)));


    }
}
