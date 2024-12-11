package ui;


public class Client {

    private final String url;
    private final PreLoginRepl preRepl;
    private final PostLoginRepl postRepl;
    private final GameRepl gameRepl;
    private Client.States status;



    public Client(String url){
        this.url = url;
        preRepl = new PreLoginRepl(this.url);
        postRepl = new PostLoginRepl(this.url);
        gameRepl = new GameRepl(this.url);
        status = States.PRELOGIN;
    }

    public void run(){
        //Add a loop because you need to loop potentially infinitely through the three menu options.
        while(status!=States.QUIT){
            switch(this.status){
                case QUIT: {
                    return;
                }
                case PRELOGIN: {
                    status = preRepl.run();
                }
                case POSTLOGIN: {
                    status = postRepl.run();
                }
                case GAME: {
                    status = gameRepl.run();
                }
            }
        }

    }

    enum States{
        POSTLOGIN,
        PRELOGIN,
        GAME,
        QUIT
    }
}
