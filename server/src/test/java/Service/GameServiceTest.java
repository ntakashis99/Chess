package Service;

import chess.ChessGame;
import dataaccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.GameService;
import service.InvalidUserException;
import service.UserService;
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

        gameService.createGame(new CreateGameRequest(r.authtoken(),"game1"));
        Assertions.assertNotNull(gameService.listGames(new AuthData(r.authtoken(),null)));

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
        gameService.createGame(new CreateGameRequest(r.authtoken(),"game1"));
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

        gameService.createGame(new CreateGameRequest(r.authtoken(),"game1"));
        Assertions.assertNotNull(gameService.listGames(new AuthData(r.authtoken(),null)));
    }

    @Test
    void createGameFail() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        GameService gameService = new GameService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        RegisterResult r;
        r = userService.register(new UserData("nephi","1111","1@g"));
        gameService.createGame(new CreateGameRequest(r.authtoken(),"game1"));
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
        gameService.createGame(new CreateGameRequest(r.authtoken(),"game1"));

        CreateGameResult gameRes = gameService.joinGame(new JoinGameRequest(r.authtoken(),"WHITE",1));

        Assertions.assertEquals("nephi", gameService.listGames(new AuthData(r.authtoken(), "nephi")).games().get(0).whiteUsername());
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
        gameService.createGame(new CreateGameRequest(r.authtoken(),"game1"));

        CreateGameResult gameRes = gameService.joinGame(new JoinGameRequest(r.authtoken(),"WHITE",1));

        Assertions.assertEquals("nephi", gameService.listGames(new AuthData(r.authtoken(), "nephi")).games().get(0).whiteUsername());

    }
}
