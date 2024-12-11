package ui;

import chess.ChessGame;
import chess.ChessMove;

import java.util.Scanner;

import static ui.Client.getInput;
import static ui.Client.printPrompt;

public class GameRepl {

    ServerFacade serverFacade;
    GamePrinter printer;

    public GameRepl(ServerFacade serverFacade) {
        this.serverFacade = serverFacade;
        this.printer = new GamePrinter(ChessGame.TeamColor.WHITE);
    }


    public Client.States run() {
        String welcomePrompt =
                """
                Time for Chess!
                Please input one of the following numbers to begin
                1 - Make move
                2 - Logout (of chess)
                3 - Leave game (exit the game)
                4 - Help (list options)
                """;
        ChessMove move;
        Scanner scanner = new Scanner(System.in);
        var defaultGame = new ChessGame();

        printer.print(defaultGame);

        var input = getInput(scanner,welcomePrompt);


        while(!input.equals("3")){
            switch(input){
                case "1":
                case "2":
                case "3":
                    return Client.States.POSTLOGIN;
                default:
                    input = getInput(scanner,welcomePrompt);
            }

        }
        try{
            serverFacade.logout();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        return Client.States.QUIT;
    }

    public void printPrompt(){
        System.out.println("\n>>>");
    }


}
