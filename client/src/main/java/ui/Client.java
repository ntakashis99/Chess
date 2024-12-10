package ui;

public class Client {

    private final String URL;
    private final PreLoginRepl preRepl;
    private final PostLoginRepl postRepl;
    private final GameRepl gameRepl;



    public Client(String url){
        this.URL = url;
        preRepl = new PreLoginRepl();
        postRepl = new PostLoginRepl();
        gameRepl = new GameRepl();

        preRepl.run();
    }
}
