package ui;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.Scanner;

import static ui.Client.getInput;
import static ui.Client.printPrompt;

public class PostLoginRepl {

    private ServerFacade serverFacade;
    private GamePrinter printer;
    public PostLoginRepl(ServerFacade serverFacade) {
        this.serverFacade = serverFacade;
        this.printer = new GamePrinter(ChessGame.TeamColor.WHITE);
    }

    public Client.States run() {
        String welcomePrompt =
                """
                Hello User!
                Please input one of the following numbers to begin
                1 - Create (a chess game)
                2 - List (all current games)
                3 - Join (an existing game)
                4 - Observe (an existing game)
                5 - Logout (of chess)
                6 - Quit (exit the chess application)
                7 - Help (list options)
                """;

        String gameName;
        int gameID;
        ChessGame.TeamColor color;
        ArrayList<GameData> list;

        Scanner scanner = new Scanner(System.in);

        var input = getInput(scanner,welcomePrompt);

        while(!input.equals("6")){
            switch(input){
                case "1":
                    gameName = getInput(scanner,"\nInput a game name");
                    try {
                        serverFacade.create(gameName);
                        System.out.println("\nSuccess!");
                        input = "7";
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                    continue;
                case "2":
                    try {
                        list = serverFacade.list();
                        if(list.size()==0){
                            System.out.println("\nThere are no games\n");
                            input="7";
                        }
                        for(var game:list){
                            System.out.print(game + "\n");
                        }
                        input = "7";
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                    continue;
                case "3":

                    String user_color = getInput(scanner,"\nEnter game color (W or B)");
                    if(user_color.equals("W")||user_color.equals("w")){
                        color = ChessGame.TeamColor.WHITE;
                    }
                    else if(user_color.equals("B")||user_color.equals("b")){
                        color = ChessGame.TeamColor.BLACK;
                    }else{
                        System.out.println("\nPlease enter a valid color");
                        continue;
                    }
                    gameID = Integer.parseInt(getInput(scanner,"\nEnter a gameID"));
                    try {
                        gameID = serverFacade.join(String.valueOf(color),gameID);
                        System.out.println("\nSuccess!");
                        return Client.States.GAME;
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                    continue;
                    //Print the board in the game repl

                case "4":
                    //Just print the default board
                    var defaultGame = new ChessGame();
                    printer.print(defaultGame);
                    input = "7";
                    continue;
                case "5":
                    try{
                        serverFacade.logout();
                    }catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                    return Client.States.PRELOGIN;
                default:
                    input = getInput(scanner,"\nPlease input one of the following numbers to begin\n" +
                            "                1 - Create (a chess game)\n" +
                            "                2 - List (all current games)\n" +
                            "                3 - Join (an existing game)\n" +
                            "                4 - Observe (an existing game)\n" +
                            "                5 - Logout (of chess)\n" +
                            "                6 - Quit (exit the chess application)\n" +
                            "                7 - Help (list options)");
            }
        }

        try {
            serverFacade.logout();
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        return Client.States.QUIT;
    }
}
