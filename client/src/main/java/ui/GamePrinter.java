package ui;

import chess.ChessGame;
import chess.ChessPosition;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static chess.ChessPiece.PieceType.*;
import static ui.EscapeSequences.*;

public class GamePrinter {

    ChessGame.TeamColor view;
    public GamePrinter(ChessGame.TeamColor color){
        view = color;
    }

    public void print(ChessGame game){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);

        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                printSpace(i,j,out,game);
            }
            //reset background color
            out.print(RESET_BG_COLOR + "\n");
        }

    }

    public void printSpace(int i, int j, PrintStream out, ChessGame game){
        var loc = moveCursorToLocation(i,j);
        if((i+j)%2==0){
            var piece = game.getBoard().getPiece(new ChessPosition(i,j));
            if(piece == null){
                out.print(loc + SET_BG_COLOR_WHITE + "   ");
            }else if(piece.getTeamColor()== ChessGame.TeamColor.WHITE) {
                switch (piece.getPieceType()) {
                    case ROOK:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + " R ");
                        return;
                    case PAWN:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + " P ");
                        return;
                    case KNIGHT:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + " N ");
                        return;
                    case KING:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + " K ");
                        return;
                    case QUEEN:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + " Q ");
                        return;
                    case BISHOP:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + " B ");
                }
            }
            else{
                switch (piece.getPieceType()) {
                    case ROOK:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + " R ");
                        return;
                    case PAWN:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + " P ");
                        return;
                    case KNIGHT:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + " N ");
                        return;
                    case KING:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + " K ");
                        return;
                    case QUEEN:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + " Q ");
                        return;
                    case BISHOP:
                        out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + " B ");
                }
            }

        }else{
            var piece = game.getBoard().getPiece(new ChessPosition(i,j));
            if(piece == null) {
                out.print(loc + SET_BG_COLOR_BLACK + "   ");
            }
            else if(piece.getTeamColor()== ChessGame.TeamColor.WHITE) {
                switch (piece.getPieceType()) {
                    case ROOK:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + " R ");
                        return;
                    case PAWN:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + " P ");
                        return;
                    case KNIGHT:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + " N ");
                        return;
                    case KING:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + " K ");
                        return;
                    case QUEEN:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + " Q ");
                        return;
                    case BISHOP:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + " B ");
                }
            }
            else{
                switch (piece.getPieceType()) {
                    case ROOK:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + " R ");
                        return;
                    case PAWN:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + " P ");
                        return;
                    case KNIGHT:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + " N ");
                        return;
                    case KING:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + " K ");
                        return;
                    case QUEEN:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + " Q ");
                        return;
                    case BISHOP:
                        out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + " B ");
                }
            }
        }
    }

}
