package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import service.requestresult.LoginResponse;
import model.UserData;
import service.requestresult.RegisterResponse;

import java.util.UUID;


public class UserService {
    private UserDAO userDao = null;
    private AuthDAO authDao = null;
    private GameDAO gameDao = null;

    public UserService(UserDAO userDao, AuthDAO authDao, GameDAO gamedao){
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gamedao;
    }

    public LoginResponse login(UserData user) throws DataAccessException {
        UserData userdata = userDao.getUser(user);

        if(userdata==null){
            throw new InvalidUserException("Username is not Valid");
        }

        //Add the password check here;
        if(userdata.password()!=user.password()){
            throw new InvalidUserException("Password is not Valid");
        }

        else{
            //create authdata and return new loginresponse of the information

            AuthData authdata = authDao.createAuth(new AuthData(UUID.randomUUID().toString(), user.username()));

            return new LoginResponse(userdata.username(), authdata.authToken());
        }

    }

    public RegisterResponse register(UserData user) throws DataAccessException{
        UserData userdata = userDao.getUser(user);
        if(userdata!=null){
            throw new InvalidUserException("Error: already taken");
        }
        else{
            userDao.createUser(user);
            String auth = UUID.randomUUID().toString();
            AuthData newAuth = authDao.createAuth(new AuthData(auth,user.username()));
            return new RegisterResponse(newAuth.username(), newAuth.authToken());
        }
    }
}

