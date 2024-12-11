package ui;

import chess.ChessGame;
import chess.ChessMove;
import model.GameData;

import java.util.ArrayList;
import java.util.Scanner;

public class GameRepl {

    ServerFacade serverFacade;
    public GameRepl(String url) {
        this.serverFacade = new ServerFacade(url);
    }


    public Client.States run() {
        String welcomePrompt =
                """
                \uD83D\uDC36
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

        var input = scanner.nextLine();
        while(!input.equals("3")){
            switch(input){
                case "1":
                case "2":
                case "3":
                default:
            }

        }
        return Client.States.QUIT;
    }

    public void printPrompt(){
        System.out.println("\n>>>");
    }


}
