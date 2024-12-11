package ui;

import java.util.Scanner;

import static ui.Client.getInput;
import static ui.Client.printPrompt;

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
                    username = getInput(scanner,"Enter username");
                    password = getInput(scanner,"Enter password");
                    email = getInput(scanner,"Enter email");
                    try {
                        serverFacade.register(username,password,email);
                        return Client.States.POSTLOGIN;
                    } catch (ResponseException e) {
                        var msg = e.toString();
                        System.out.print("\nThere was an error with your input. Please try again\n");

                    }
                case "2":
                    username = getInput(scanner,"Enter username");
                    password = getInput(scanner,"Enter password");
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
                    input = getInput(scanner,"Please input one of the following numbers (with no whitespace) to begin\n" +
                            "                1 -  Register (a new account)\n" +
                            "                2 - Login (an existing user)\n" +
                            "                3 - Help (list options)\n" +
                            "                4 - Quit (exit the chess application)");
            }

        }
        return Client.States.QUIT;
    }


}
