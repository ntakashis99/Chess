package ui;

import java.util.Scanner;

public class PreLoginRepl {

    ServerFacade serverFacade;

    public PreLoginRepl(ServerFacade serverFacade) {
        this.serverFacade = serverFacade;
    }


    //Run shouldn't return void, return the new status
    public Client.States run() {
        String welcomePrompt =
                """
                Welcome to Chess!
                Please input one of the following numbers to begin
                1 -  Register (a new account)
                2 - Login (an existing user)
                3 - Help (list options)
                4 - Quit (exit the chess application)
                """;
        System.out.println(welcomePrompt);

        printPrompt();


        String username;
        String password;
        String email;

        Scanner scanner = new Scanner(System.in);

        var input = scanner.nextLine();

        //JUST FOR THE FIRST TIME RIGHT NOW

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
                        return Client.States.POSTLOGIN;
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print("\nThere was an error with your input. Please try again\n");
                    }
                case "2":
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("Enter password");
                    password = scanner.nextLine();
                    try {
                        serverFacade.login(username,password);
                        return Client.States.POSTLOGIN;
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        if(e.getStatusCode()==500){
                            System.out.print("\nYou are not registered. Please register an account first\n");
                        }
                        else {
                            System.out.print("\nThere was an error with your input. Please try again\n");
                        }
                    }

                default:
                    System.out.println("Please input one of the following numbers (with no whitespace) to begin\n" +
                            "                1 -  Register (a new account)\n" +
                            "                2 - Login (an existing user)\n" +
                            "                3 - Help (list options)\n" +
                            "                4 - Quit (exit the chess application)");
                    printPrompt();
                    input = scanner.nextLine();
            }

        }
        return Client.States.QUIT;
    }

    public void printPrompt() {
        System.out.println("\n>>>");
    }
}
