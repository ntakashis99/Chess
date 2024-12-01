package chess;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.Collection;

public abstract class PieceMoveRule {
    public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
    public Collection<ChessMove> checkMoves(ChessBoard board, ChessPosition myPosition, int[][] moveChanges){
        Collection<ChessMove> moves = new ArrayList<>();
        for (int[] move : moveChanges) {
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
            ChessPosition currentPos = myPosition;
            while(!(currentPos.getRow() + direction[0] > 8) && !(currentPos.getRow() + direction[0] < 1 ) && !(currentPos.getColumn()+ direction[1] > 8) && !(currentPos.getColumn()+ direction[1] < 1)){
                ChessPosition newPosition = new ChessPosition(currentPos.getRow() + direction[0], currentPos.getColumn() + direction[1]);
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
                currentPos = newPosition;
            }

            // if it's null, add position and check further
            // if enemy piece, add and don't check further
            // if it's your piece, don't add

        }
        return moves;
    }

}
