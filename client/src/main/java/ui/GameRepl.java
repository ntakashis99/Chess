package ui;

import chess.ChessGame;
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
                1 - Create (a chess game)
                2 - List (all current games)
                3 - Join (an existing game)
                4 - Observe (an existing game)
                5 - Logout (of chess)
                6 - Quit (exit the chess application)
                7 - Help (list options)
                """;
        System.out.println(welcomePrompt);
        printPrompt();

        String gameName;
        int gameID;
        ChessGame.TeamColor color;
        ArrayList<GameData> list;

        Scanner scanner = new Scanner(System.in);

        var input = scanner.nextLine();
    }


}
