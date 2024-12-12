package chess;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType pieceType;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.pieceType = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {return startPosition;}

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return pieceType;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s (%s)", startPosition, endPosition, pieceType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        ChessMove that = (ChessMove) o;
        return startPosition.equals(that.startPosition) && endPosition.equals(that.endPosition) && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        int result = startPosition.hashCode();
        result = 23 * result + endPosition.hashCode();
        if (pieceType != null) {
            result = 23 * result + pieceType.hashCode();
        }
        return result;
    }
}
