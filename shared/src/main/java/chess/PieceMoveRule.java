package chess;

import java.util.Collection;

public interface PieceMoveRule {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
}
