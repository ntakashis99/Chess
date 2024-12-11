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
        for(int i = 1;i<=8;i++){
            for(int j=1;j<=8;j++){
                var loc = moveCursorToLocation(i,j);
                if((i+j)%2==0){
                    var piece = game.getBoard().getPiece(new ChessPosition(i,j));
                    if(piece == null){
                        out.print(loc + SET_BG_COLOR_WHITE);
                        continue;
                    }
                    if(piece.getTeamColor()== ChessGame.TeamColor.WHITE) {
                        switch (piece.getPieceType()) {
                            case ROOK:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + "R");
                                continue;
                            case PAWN:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + "P");
                                continue;
                            case KNIGHT:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + "N");
                                continue;
                            case KING:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + "K");
                                continue;
                            case QUEEN:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + "Q");
                                continue;
                            case BISHOP:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_RED + "B");
                                continue;
                        }
                    }
                    else{
                        switch (piece.getPieceType()) {
                            case ROOK:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + "R");
                                continue;
                            case PAWN:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + "P");
                                continue;
                            case KNIGHT:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + "N");
                                continue;
                            case KING:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + "K");
                                continue;
                            case QUEEN:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + "Q");
                                continue;
                            case BISHOP:
                                out.print(loc + SET_BG_COLOR_WHITE + SET_TEXT_COLOR_BLUE + "B");
                                continue;
                            }
                        }

                }else{
                    var piece = game.getBoard().getPiece(new ChessPosition(i,j));
                    if(piece == null){
                        out.print(loc + SET_BG_COLOR_BLACK);
                        continue;
                    }
                    if(piece.getTeamColor()== ChessGame.TeamColor.WHITE) {
                        switch (piece.getPieceType()) {
                            case ROOK:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + "R");
                                continue;
                            case PAWN:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + "P");
                                continue;
                            case KNIGHT:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + "N");
                                continue;
                            case KING:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + "K");
                                continue;
                            case QUEEN:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + "Q");
                                continue;
                            case BISHOP:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_RED + "B");
                                continue;
                        }
                    }
                    else{
                        switch (piece.getPieceType()) {
                            case ROOK:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + "R");
                                continue;
                            case PAWN:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + "P");
                                continue;
                            case KNIGHT:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + "N");
                                continue;
                            case KING:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + "K");
                                continue;
                            case QUEEN:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + "Q");
                                continue;
                            case BISHOP:
                                out.print(loc + SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE + "B");
                                continue;
                        }
                    }
                }

            }
        }

    }

}
