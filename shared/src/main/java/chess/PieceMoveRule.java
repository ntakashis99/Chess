package chess;

import java.util.Collection;

public abstract class PieceMoveRule {
    public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);

    //public Collection<ChessMove> checkDiag();
}
