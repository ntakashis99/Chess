package ui;

import chess.ChessGame;
import chess.ChessMove;
import model.GameData;

import java.util.ArrayList;
import java.util.Scanner;

public class GameRepl {

    ServerFacade serverFacade;
    GamePrinter printer;

    public GameRepl(String url) {
        this.serverFacade = new ServerFacade(url);
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
        System.out.println(welcomePrompt);
        printPrompt();

        ChessMove move;


        Scanner scanner = new Scanner(System.in);


        var defaultGame = new ChessGame();
        printer.print(defaultGame);

        var input = scanner.nextLine();
        while(!input.equals("3")){
            switch(input){
                case "1":
                case "2":
                case "3":
                    return Client.States.POSTLOGIN;
                default:
            }

        }
        return Client.States.QUIT;
    }

    public void printPrompt(){
        System.out.println("\n>>>");
    }


}
