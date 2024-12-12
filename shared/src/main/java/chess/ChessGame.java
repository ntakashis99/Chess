package chess;

import com.sun.source.tree.WhileLoopTree;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private static TeamColor turn;
    private ChessBoard board;

    public ChessGame() {
        turn = TeamColor.WHITE;
        board = new ChessBoard();
        board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return turn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        turn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {

        ChessPiece piece = board.getPiece(startPosition);

        if(piece==null){
            return null;
        }

        //Make temp board, check if the move is legal ect
        ChessBoard tempBoard = new ChessBoard();

        //make this board equal to the other
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                ChessPosition currentPos = new ChessPosition(i,j);
                var currPiece = board.getPiece(currentPos);
                tempBoard.addPiece(currentPos,currPiece);
            }
        }


        Collection<ChessMove> moves = piece.pieceMoves(tempBoard,startPosition);
        Collection<ChessMove> finalMoves = new ArrayList<>();
        for(var move:moves){
            var p1 = board.getPiece(move.getEndPosition());
            var p2 = board.getPiece(move.getStartPosition());
            board.addPiece(move.getEndPosition(),null);
            board.addPiece(move.getStartPosition(),null);
            if(move.getPromotionPiece()!=null){
                piece = new ChessPiece(piece.getTeamColor(),move.getPromotionPiece());
                board.addPiece(move.getEndPosition(),piece);
            }
            else {
                board.addPiece(move.getEndPosition(), piece);
            }
            boolean inCheck = isInCheck(piece.getTeamColor());
            if(!inCheck){
                finalMoves.add(move);
            }
            board.addPiece(move.getStartPosition(),p2);
            board.addPiece(move.getEndPosition(),p1);
        }

        setBoard(tempBoard);
        return finalMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        var startPos = move.getStartPosition();
        var endPos = move.getEndPosition();

        ChessPiece piece = board.getPiece(startPos);
        if(piece==null){
            throw new InvalidMoveException("Not a valid move");
        }

        var validMoves = validMoves(startPos);
        if(!validMoves.contains(move)){
            throw new InvalidMoveException("Not a valid move");
        }

        if(piece.getTeamColor()!=turn){
            throw new InvalidMoveException("Not your move");
        }

        if(move.getPromotionPiece()!=null){
            piece = new ChessPiece(piece.getTeamColor(),move.getPromotionPiece());
        }

        board.addPiece(startPos,null);
        board.addPiece(endPos,piece);

        if(turn==TeamColor.WHITE){
            setTeamTurn(TeamColor.BLACK);
        }
        else if(turn==TeamColor.BLACK){
            setTeamTurn(TeamColor.WHITE);
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //Iterate through all pieces to find king
        ChessPosition kingpos = null;
        outerloop:
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                ChessPosition currentPos = new ChessPosition(i,j);
                var currPiece = board.getPiece(currentPos);
                if(currPiece==null){
                    continue;
                }
                if((currPiece.getTeamColor() == teamColor) && currPiece.getPieceType()== ChessPiece.PieceType.KING){
                    kingpos = currentPos;
                    break outerloop;
                }
            }
        }

        if(kingpos==null){
            return false;
        }


        //Another nested for loop to find all legal moves, see if king pos is in there
        for(int i=1; i<=8;i++){
            for(int j=1;j<=8;j++){
                ChessPosition currentPos = new ChessPosition(i,j);
                var currPiece = board.getPiece(currentPos);
                if(currPiece==null){
                    continue;
                }
                if((currPiece.getTeamColor() != teamColor)){
                    var attacks = currPiece.pieceMoves(board,currentPos);
                    for(var move:attacks){
                        ChessPosition endPos = move.getEndPosition();
                        boolean condition = (endPos.getRow()==kingpos.getRow() && endPos.getColumn()==kingpos.getColumn());
                        if(condition){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)){
            return false;
        }
        else{
            for(int i=1; i<=8;i++){
                for(int j=1;j<=8;j++){
                    var a = checkMoveBlock(i,j,teamColor);
                    if(a.equals("n")){
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public String checkMoveBlock(int i, int j, TeamColor teamColor){
        ChessPosition currentPos = new ChessPosition(i,j);
        var currPiece = board.getPiece(currentPos);
        if(currPiece==null){
            return "?";
        }
        if((currPiece.getTeamColor() == teamColor)){
            var moves = currPiece.pieceMoves(board,currentPos);
            for(var move:moves){
                var p1 = board.getPiece(move.getEndPosition());
                var p2 = board.getPiece(move.getStartPosition());
                board.addPiece(move.getEndPosition(),null);
                board.addPiece(move.getStartPosition(),null);
                board.addPiece(move.getEndPosition(),currPiece);
                boolean inCheck = isInCheck(currPiece.getTeamColor());
                if(!inCheck){
                    return "n";
                }
                board.addPiece(move.getStartPosition(),p2);
                board.addPiece(move.getEndPosition(),p1);
            }
        }
        return "?";
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)){
            return false;
        }

        for(int i=1; i<=8;i++){
            for(int j=1;j<=8;j++){
                ChessPosition currentPos = new ChessPosition(i,j);
                var currPiece = board.getPiece(currentPos);
                if(currPiece==null){
                    continue;
                }
                if((currPiece.getTeamColor() == teamColor)){
                    if(!validMoves(currentPos).isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}
