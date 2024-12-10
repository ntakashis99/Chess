package ui;

import java.util.Scanner;

public class PreLoginRepl {

    ServerFacade serverFacade;

    public PreLoginRepl(String url) {
        this.serverFacade = new ServerFacade(url);
    }


    //Run shouldn't return void, return the new status
    public void run() {
        String welcomePrompt =
                """
                \uD83D\uDC36
                Welcome to Chess!
                Please input one of the following numbers to begin
                1 -  Register (a new account)
                2 - Login (an existing user)
                3 - Help (list options)
                4 - Quit (exit the chess application)
                """;
        System.out.println(welcomePrompt);

        String username;
        String password;
        String email;

        Scanner scanner = new Scanner(System.in);

        var input = scanner.nextLine();

        while(!input.equals("4")){
            switch(input){
                case "1":
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("Enter password");
                    password = scanner.nextLine();
                    System.out.println("Enter email");
                    email = scanner.nextLine();
                    try {
                        serverFacade.register(username,password,email);
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                case "2":
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("Enter password");
                    password = scanner.nextLine();
                    try {
                        serverFacade.login(username,password);
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                default:
                    System.out.println("Please input one of the following numbers to begin\n" +
                            "                1 -  Register (a new account)\n" +
                            "                2 - Login (an existing user)\n" +
                            "                3 - Help (list options)\n" +
                            "                4 - Quit (exit the chess application)");
                    input = scanner.nextLine();
            }


        }

    }
}
