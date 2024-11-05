package service;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.LoginResponse;
import model.UserData;


public class UserService {
    private UserDAO userDao = null;

    public UserService(UserDAO userDao){
        this.userDao = userDao;
    }

    public LoginResponse login(UserData user) throws DataAccessException {
        UserData userdata =  userDao.getUser(user);

        if(userdata==null){
            throw new InvalidUserException("Username is not Valid");
        }

        //Add the password check here;
        if(userdata.password()!=user.password()){
            throw new InvalidUserException("Password is not Valid");
        }

        else{




            return null;
        }
    }
}

