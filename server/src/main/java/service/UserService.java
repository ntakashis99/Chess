package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import model.AuthData;
import service.requestresult.LoginResult;
import model.UserData;
import service.requestresult.RegisterResult;

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

    public LoginResult login(UserData user) throws DataAccessException {
        UserData userdata = userDao.getUser(user);

        if(userdata==null){
            throw new InvalidUserException("Error: unauthorized");
        }

        //Add the password check here;
        if(!userdata.password().equals(user.password())){
            throw new InvalidUserException("Error: unauthorized");
        }
        else{
            //create authdata and return new loginresponse of the information

            AuthData authdata = authDao.createAuth(new AuthData(UUID.randomUUID().toString(), user.username()));

            return new LoginResult(userdata.username(), authdata.authToken());
        }

    }

    public RegisterResult register(UserData user) throws DataAccessException{
        UserData userdata = userDao.getUser(user);
        if(userdata!=null){
            throw new InvalidUserException("Error: already taken");
        }
        else{
            userDao.createUser(user);
            String auth = UUID.randomUUID().toString();
            AuthData newAuth = authDao.createAuth(new AuthData(auth,user.username()));
            return new RegisterResult(newAuth.username(), newAuth.authToken());
        }
    }
}

