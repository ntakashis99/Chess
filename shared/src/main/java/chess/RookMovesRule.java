package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int[][] orthogonal = {{0,1},{0,-1},{1,0},{-1,0}};
        return checkDirections(board,myPosition,orthogonal);
    }
}
