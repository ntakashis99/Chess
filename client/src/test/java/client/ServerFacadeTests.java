package client;

import org.junit.jupiter.api.*;
import server.Server;
import ui.ResponseException;
import ui.ServerFacade;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade serverFacade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(8080);
        System.out.println("Started test HTTP server on " + port);
        serverFacade = new ServerFacade("http://localhost:8080");
        try {
            serverFacade.clear();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    //@AfterEach
    //Remember to clear the server (maybe add this to before each

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

}
