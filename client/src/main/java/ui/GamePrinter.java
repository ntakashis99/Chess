package ui;

import chess.ChessGame;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class GamePrinter {

    ChessGame.TeamColor view;
    GamePrinter(ChessGame.TeamColor color){
        view = color;
    }

    public void print(){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


    }

}
