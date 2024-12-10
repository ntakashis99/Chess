import chess.*;
import ui.Client;

public class Main {
    public static void main(String[] args) {
//        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client ♕\nType help to get started");
        //Open the main menu repl loop
        Client ui = new Client("http://localhost:8080");
        ui.run();
    }
}