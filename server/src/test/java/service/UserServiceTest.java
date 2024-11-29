package service;

import dataaccess.*;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.requestresult.LoginResult;
import service.requestresult.RegisterResult;

public class UserServiceTest {

    private static UserService userService;


    @Test
    void login() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResult response = userService.register(data);

        LoginResult in = userService.login(data);
        Assertions.assertEquals(in.username(),"Nephi");
        Assertions.assertNotNull(in.authToken());
    }

    @Test
    void loginFail() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResult response = userService.register(data);

        LoginResult in = userService.login(data);
        Assertions.assertThrows(InvalidUserException.class,()->userService.login(new UserData("Nephi","11","no")));
    }

    @Test
    void testRegister() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResult response = userService.register(data);
        Assertions.assertEquals(response.username(),"Nephi");
        Assertions.assertNotNull(response.authtoken());
    }

    @Test
    void registerFail() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResult response = userService.register(data);


        Assertions.assertThrows(InvalidUserException.class,()->userService.register(data));
    }
}
