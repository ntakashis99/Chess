package chess;

import java.util.Collection;
import java.util.List;

public class QueenMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int[][] queen = {{-1,1},{-1,0},{-1,-1},{1,1},{1,0},{1,-1},{0,1},{0,-1}};
        return checkDirections(board,myPosition,queen);
    }
}
