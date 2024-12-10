package ui;

public class Client {

    private final String URL;
    private final PreLoginRepl preRepl;
    private final PostLoginRepl postRepl;
    private final GameRepl gameRepl;
    private String status;



    public Client(String url){
        this.URL = url;
        preRepl = new PreLoginRepl(this.URL);
        postRepl = new PostLoginRepl(this.URL);
        gameRepl = new GameRepl(this.URL);
    }

    public void run(){
        //Add a loop because you need to loop potentially infinitely through the three menu options.
        preRepl.run();

    }
}
