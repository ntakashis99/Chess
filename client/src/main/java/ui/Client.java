package ui;


import org.junit.platform.commons.util.StringUtils;

import java.util.Scanner;

public class Client {

    private final String url;
    private final PreLoginRepl preRepl;
    private final PostLoginRepl postRepl;
    private final GameRepl gameRepl;
    private Client.States status;



    public Client(String url){
        this.url = url;
        ServerFacade serverFacade = new ServerFacade(this.url);
        //pass the faceade to all repl, so they have the same object
        preRepl = new PreLoginRepl(serverFacade);
        postRepl = new PostLoginRepl(serverFacade);
        gameRepl = new GameRepl(serverFacade);

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
                    continue;
                }
                case POSTLOGIN: {
                    status = postRepl.run();
                    continue;
                }
                case GAME: {
                    status = gameRepl.run();
                }
            }
        }

    }

    public enum States{
        POSTLOGIN,
        PRELOGIN,
        GAME,
        QUIT
    }
    public static String getInput(Scanner scanner,String prompt){
        System.out.println(prompt);
        printPrompt();
        String input = scanner.nextLine();

        while(StringUtils.containsWhitespace(input)){
            System.out.println("\nInput cannot contain whitespace. Try again\n");
            System.out.println(prompt);
            printPrompt();
            input = scanner.nextLine();
        }
        return input;
    }

    public static void printPrompt() {
        System.out.println("\n>>>");
    }

}
