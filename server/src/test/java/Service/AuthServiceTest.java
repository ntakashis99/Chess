package Service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.AuthService;
import service.InvalidUserException;
import service.UserService;
import service.requestresult.RegisterResponse;

public class AuthServiceTest {

    private static AuthService authService;

    @Test
    void logout() throws DataAccessException {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        AuthService authService = new AuthService(userDAO,authDAO,gameDAO);
        UserService userService = new UserService(userDAO,authDAO,gameDAO);

        RegisterResponse response = userService.register(new UserData("Nephi","1111","Nephi@1111"));

        authService.logout(new AuthData(response.authtoken(), response.username()));

        Assertions.assertThrows(InvalidUserException.class, ()->authService.logout(new AuthData(response.authtoken(), response.username())));

    }

    @Test
    void logoutFail() {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();

        AuthService authService = new AuthService(userDAO,authDAO,gameDAO);



    }
}
