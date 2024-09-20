package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesRule implements PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int[][] move_changes = new int[][]{{1, -1}, {-1, 1}, {-1, -1}, {1, 1}};
        for (int[] move : move_changes) {
            //Add boundary checking for positions
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() + move[0], myPosition.getColumn()+ move[1]);
            ChessPiece piece = board.getPiece(newPosition);
            if(piece != null) {
                if(piece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    moves.add(new ChessMove(myPosition,newPosition,null));
                }
            }
        }
        return moves;
    }
}
