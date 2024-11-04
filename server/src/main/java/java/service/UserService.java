package java.service;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.UserData;


public class UserService {
    private UserDAO userDao = null;

    public UserService(UserDAO userDao){
        this.userDao = userDao;
    }

    public UserData getUser(UserData user) throws DataAccessException {
        return userDao.getUser(user);
    }
}

