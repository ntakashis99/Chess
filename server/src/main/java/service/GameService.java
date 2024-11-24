package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.GameData;
import service.requestresult.ListGameResponse;

import java.util.ArrayList;

public class GameService {

    private UserDAO userDao = null;
    private AuthDAO authDao = null;
    private GameDAO gameDao = null;

    public GameService(UserDAO userDao, AuthDAO authDao, GameDAO gameDao) {
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gameDao;
    }

    public ListGameResponse listGames(AuthData auth) throws DataAccessException {
        AuthData verified = authDao.getAuth(auth);
        return new ListGameResponse(gameDao.getGames(verified));
    }



}
