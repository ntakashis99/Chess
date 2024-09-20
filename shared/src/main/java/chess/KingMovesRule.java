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
            int i = 0;
        }
        return moves;
    }
}
