package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int[][] move_changes = new int[][]{{1, -1}, {0,-1}, {0,1}, {-1,0}, {1,0}, {-1, 1}, {-1, -1}, {1, 1}};
        for (int[] move : move_changes) {
            //Add boundary checking for positions
            if((myPosition.getRow() + move[0] > 8) ||(myPosition.getRow() + move[0] < 1 ) || (myPosition.getColumn()+ move[1] > 8) || (myPosition.getColumn()+ move[1] < 1)){
                break;
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
}
