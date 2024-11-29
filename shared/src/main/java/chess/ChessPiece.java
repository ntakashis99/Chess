package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessPosition position;
    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.type = type;
        this.pieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //Get piece at moves and board
        ChessPiece piece = board.getPiece(myPosition);
        //Call piece moves on that piece
        //PieceMoveRule.pieceMoves(board, myPosition)

        if(piece.getPieceType() == PieceType.KING){
            KingMovesRule rule = new KingMovesRule();
            return rule.pieceMoves(board, myPosition);
        }

        if(piece.getPieceType() == PieceType.QUEEN){
            QueenMovesRule rule = new QueenMovesRule();
            return rule.pieceMoves(board, myPosition);
        }

        if(piece.getPieceType() == PieceType.BISHOP){
            BishopMovesRule rule = new BishopMovesRule();
            return rule.pieceMoves(board, myPosition);
        }

        if(piece.getPieceType() == PieceType.KNIGHT){
            KnightMovesRule rule = new KnightMovesRule();
            return rule.pieceMoves(board, myPosition);
        }

        if(piece.getPieceType() == PieceType.ROOK){
            RookMovesRule rule = new RookMovesRule();
            return rule.pieceMoves(board, myPosition);
        }

        if(piece.getPieceType() == PieceType.PAWN){
            PawnMovesRule rule = new PawnMovesRule();
            return rule.pieceMoves(board, myPosition);
        }

        //Switch statement on the piece type, call PAWN.pieceMoves(ssss)
        return null;
    }

    //Override equals, tostring hashcode
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        ChessPiece that = (ChessPiece) o;

        boolean b = (this.pieceColor == that.pieceColor) && (this.type == that.type) && (this.position == that.position);
        return b;
    }

    @Override
    public String toString(){
        return String.format("Type: %s, Color: %s, Position: %s",type,pieceColor,position);
    }

    @Override
    public int hashCode(){
        int a = 31;
        a = a * position.hashCode();
        a = a * type.hashCode() + pieceColor.hashCode();
        return a;
    }
}

