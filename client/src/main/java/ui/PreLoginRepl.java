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

        Scanner scanner = new Scanner(System.in);

        var input = scanner.nextLine();

        while(!input.equals("4")){
            switch(input){
                case "1":
                    System.out.println("Enter username");
                    var username = scanner.nextLine();
                    System.out.println("Enter password");
                    var password = scanner.nextLine();
                    System.out.println("Enter email");
                    var email = scanner.nextLine();
                    try {
                        serverFacade.register(username,password,email);
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print(msg);
                    }
                case "2":

                case "3":

            }

        }

    }
}
