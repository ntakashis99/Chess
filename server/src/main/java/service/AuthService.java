package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;

public class AuthService {

    private UserDAO userDao = null;
    private AuthDAO authDao = null;
    private GameDAO gameDao = null;

    public AuthService(UserDAO userDao, AuthDAO authDao, GameDAO gamedao){
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gamedao;
    }

    public void logout(AuthData auth) throws DataAccessException {
        AuthData verified = authDao.getAuth(auth);
        authDao.deleteAuth(verified);
    }
}
