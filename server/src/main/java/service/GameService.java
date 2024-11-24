package service;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.GameData;
import service.requestresult.CreateGameRequest;
import service.requestresult.CreateGameResult;
import service.requestresult.ListGameResult;

public class GameService {

    private UserDAO userDao = null;
    private AuthDAO authDao = null;
    private GameDAO gameDao = null;

    public GameService(UserDAO userDao, AuthDAO authDao, GameDAO gameDao) {
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gameDao;
    }

    public ListGameResult listGames(AuthData auth) throws DataAccessException {
        AuthData verified = authDao.getAuth(auth);
        return new ListGameResult(gameDao.getGames(verified));
    }

    public CreateGameResult createGame(CreateGameRequest request) throws DataAccessException{
        AuthData verified = authDao.getAuth(new AuthData(request.authorization(),null));
        int num_games = gameDao.getNumGames();
        GameData data = new GameData(num_games+1,null,null,request.gameName(),new ChessGame());
        gameDao.createGame(data);
        return new CreateGameResult(data.gameID());
    }



}
