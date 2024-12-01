package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int[][] move_changes = new int[][]{{1, -1}, {0,-1}, {0,1}, {-1,0}, {1,0}, {-1, 1}, {-1, -1}, {1, 1}};
        return checkMoves(board,myPosition,move_changes);
    }
}
