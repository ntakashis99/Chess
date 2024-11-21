package Service;

import dataaccess.*;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import service.GameService;
import service.InvalidUserException;
import service.UserService;
import service.requestresult.LoginResponse;
import service.requestresult.RegisterResponse;

import javax.print.DocFlavor;

public class UserServiceTest {

    private static UserService userService;


    @Test
    void login() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResponse response = userService.register(data);

        LoginResponse in = userService.login(data);
        Assertions.assertEquals(in.username(),"Nephi");
        Assertions.assertNotNull(in.authtoken());
    }

    @Test
    void loginFail() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResponse response = userService.register(data);

        LoginResponse in = userService.login(data);
        Assertions.assertThrows(InvalidUserException.class,()->userService.login(new UserData("Nephi","11","no")));
    }

    @Test
    void testRegister() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserService userService = new UserService(userDAO,authDAO,gameDAO);


        UserData data = new UserData("Nephi","1111","Nephi@1111");
        RegisterResponse response = userService.register(data);
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
        RegisterResponse response = userService.register(data);


        Assertions.assertThrows(InvalidUserException.class,()->userService.register(data));
    }
}
