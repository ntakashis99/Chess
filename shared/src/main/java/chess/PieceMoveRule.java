package chess;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.Collection;

public abstract class PieceMoveRule {
    public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
    public Collection<ChessMove> check_moves(ChessBoard board, ChessPosition myPosition, int[][] move_changes){
        Collection<ChessMove> moves = new ArrayList<>();
        for (int[] move : move_changes) {
            //Add boundary checking for positions
            if((myPosition.getRow() + move[0] > 8) ||(myPosition.getRow() + move[0] < 1 ) || (myPosition.getColumn()+ move[1] > 8) || (myPosition.getColumn()+ move[1] < 1)){
                continue;
            }
            else {
                ChessPosition newPosition = new ChessPosition(myPosition.getRow() + move[0], myPosition.getColumn() + move[1]);
                ChessPiece piece = board.getPiece(newPosition);
                if (piece != null) {
                    if (piece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        moves.add(new ChessMove(myPosition, newPosition, null));
                    }
                }
                else {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
            }
        }
        return moves;
    }

    //For Bishop,Rook, Queen
    public Collection<ChessMove> checkDirections(ChessBoard board, ChessPosition myPosition,int[][] directions){
        Collection<ChessMove> moves = new ArrayList<>();
        //Check a direction
        for (int[] direction : directions) {
            //Check bounds
            ChessPosition current_pos = myPosition;
            while(!(current_pos.getRow() + direction[0] > 8) && !(current_pos.getRow() + direction[0] < 1 ) && !(current_pos.getColumn()+ direction[1] > 8) && !(current_pos.getColumn()+ direction[1] < 1)){
                ChessPosition newPosition = new ChessPosition(current_pos.getRow() + direction[0], current_pos.getColumn() + direction[1]);
                ChessPiece piece = board.getPiece(newPosition);
                if (piece != null) {
                    if (piece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        moves.add(new ChessMove(myPosition, newPosition, null));
                        break;
                    }
                    else{
                        break;
                    }
                }
                else {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
                current_pos = newPosition;
            }

            // if it's null, add position and check further
            // if enemy piece, add and don't check further
            // if it's your piece, don't add

        }
        return moves;
    }

}
