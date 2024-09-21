package chess;

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
    //public Collection<ChessMove> checkDiag();

}
