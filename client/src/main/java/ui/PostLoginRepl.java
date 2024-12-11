package ui;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println(welcomePrompt);
        printPrompt();

        String gameName;
        int gameID;
        ChessGame.TeamColor color;
        ArrayList<GameData> list;

        Scanner scanner = new Scanner(System.in);

        var input = scanner.nextLine();

        while(!input.equals("6")){
            switch(input){
                case "1":
                    System.out.println("\nInput a game name");
                    printPrompt();
                    gameName = scanner.nextLine();
                    try {
                        serverFacade.create(gameName);
                        System.out.println("\nSuccess!");
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                    continue;
                case "2":
                    try {
                        list = serverFacade.list();
                        for(var game:list){
                            System.out.print(game);
                        }
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                    continue;
                case "3":
                    System.out.println("\nEnter game color (W or B)");
                    printPrompt();
                    if(scanner.nextLine()=="W"||scanner.nextLine()=="w"){
                        color = ChessGame.TeamColor.WHITE;
                    }
                    if(scanner.nextLine()=="B"||scanner.nextLine()=="b"){
                        color = ChessGame.TeamColor.BLACK;
                    }else{
                        System.out.println("\nPlease enter a valid color");
                        continue;
                    }
                    System.out.println("\nEnter a gameID");
                    printPrompt();
                    gameID = Integer.parseInt(scanner.nextLine());
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
                    System.out.println("\nPlease input one of the following numbers to begin\n" +
                            "                1 - Create (a chess game)\n" +
                            "                2 - List (all current games)\n" +
                            "                3 - Join (an existing game)\n" +
                            "                4 - Observe (an existing game)\n" +
                            "                5 - Logout (of chess)\n" +
                            "                6 - Quit (exit the chess application)\n" +
                            "                7 - Help (list options)");
                    input = scanner.nextLine();
            }
        }

        try {
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
