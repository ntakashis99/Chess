package ui;

import java.util.Scanner;

public class PreLoginRepl {

    public void run() {
        String welcomePrompt =
                """
                \uD83D\uDC36
                Welcome to Chess!
                Please input one of the following numbers to begin
                1. Register (a new account)
                2. Login (an existing user)
                3. Help (list options)
                4. Quit (exit the chess application)
                """;
        System.out.println(welcomePrompt);

        Scanner scanner = new Scanner(System.in);


    }
}
