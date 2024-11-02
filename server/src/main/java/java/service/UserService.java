package java.service;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.UserData;


public class UserService {
    private UserDAO userDao = null;

    public UserService(UserDAO userDao){
        this.userDao = userDao;
    }

    public UserData getUser(UserData username) throws DataAccessException {
        UserData data = userDao.readUser(username);
        return data;
    }
}

