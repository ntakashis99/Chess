package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int[][] diags = {{1,1},{1,-1},{-1,1},{-1,-1}};
        return checkDirections(board,myPosition,diags);
    }
}
