package chess;

import java.util.Collection;
import java.util.List;

public class QueenMovesRule implements PieceMoveRule{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return List.of();
    }
}
