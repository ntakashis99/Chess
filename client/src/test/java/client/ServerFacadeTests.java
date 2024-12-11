package client;

import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.*;
import server.Server;
import service.InvalidUserException;
import spark.utils.Assert;
import ui.ResponseException;
import ui.ServerFacade;

import java.util.ArrayList;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade serverFacade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        serverFacade = new ServerFacade("http://localhost:" + Integer.toString(port));
        try {
            serverFacade.clear();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown(){
        try {
            serverFacade.clear();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }

    @Test
    public void registerPositive(){
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void registerFail(){
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(ResponseException.class, ()->serverFacade.register("nephi","bad","nephi@gmail"));
    }

    @Test
    public void loginPositive() {
        AuthData log;
        AuthData reg;
        try {
            reg = serverFacade.register("nephi", "bad", "nephi@gmail");
            log = serverFacade.login("nephi", "bad");
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(log.username(), reg.username());
        Assertions.assertNotNull(log.authToken());

    }
    @Test
    public void loginFail(){
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(ResponseException.class,()->serverFacade.login("None","none"));
    }

    @Test
    public void logoutPositive(){
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
            serverFacade.logout();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(ResponseException.class,()->serverFacade.join("Black",1));
    }
    @Test
    public void logoutFail(){
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
            serverFacade.logout();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(ResponseException.class, ()->serverFacade.logout());
    }

    @Test
    public void createPositive() {
        ArrayList<GameData> list;
        try {
            var a = serverFacade.register("nephi", "bad", "nephi@gmail");
            serverFacade.create("newGame");
            list = serverFacade.list();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(list.isEmpty());

    }
    @Test
    public void createFail(){
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(ResponseException.class, ()->serverFacade.create(null));
    }

    @Test
    public void joinPositive(){
        ArrayList<GameData> list;
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
            serverFacade.create("newGame");
            serverFacade.join("BLACK",1);
            list = serverFacade.list();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(list.getFirst().blackUsername(),"nephi");

    }
    @Test
    public void joinFail(){
        ArrayList<GameData> list;
        try {
            var a = serverFacade.register("nephi","bad","nephi@gmail");
            serverFacade.create("newGame");
            serverFacade.join("BLACK",1);
            list = serverFacade.list();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(ResponseException.class,()->serverFacade.join(null,0));
    }
    @Test
    public void listPositive(){
        ArrayList<GameData> list;
        try {
            var a = serverFacade.register("nephi", "bad", "nephi@gmail");
            serverFacade.create("newGame");
            list = serverFacade.list();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(list.isEmpty());


    }
    @Test
    public void listFail(){
        Assertions.assertThrows(ResponseException.class,()->serverFacade.list());
    }


    @Test
    public void clearPositive(){
        ArrayList<GameData> list;
        try {
            var a = serverFacade.register("nephi", "bad", "nephi@gmail");
            serverFacade.create("newGame");
            serverFacade.clear();
            a = serverFacade.register("nephi", "bad", "nephi@gmail");
            list = serverFacade.list();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(0,list.size());
    }


}
