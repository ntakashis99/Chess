package chess;

import java.util.Collection;
import java.util.List;

public class KnightMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int[][] knightMoves = {{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
        return checkMoves(board,myPosition,knightMoves);
    }
}
