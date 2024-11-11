package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

public class AuthService {

    private UserDAO userDao = null;
    private AuthDAO authDao = null;
    private GameDAO gameDao = null;

    public AuthService(UserDAO userDao, AuthDAO authDao, GameDAO gamedao){
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gamedao;
    }
}
