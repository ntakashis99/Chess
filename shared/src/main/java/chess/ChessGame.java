package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private static TeamColor color;
    private ChessBoard board;

    public ChessGame() {
        color = TeamColor.WHITE;
        board = new ChessBoard();
        board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return color;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        color = team;
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
        ChessBoard temp_board = new ChessBoard();

        //make this board equal to the other
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                ChessPosition current_pos = new ChessPosition(i,j);
                var curr_piece = board.getPiece(current_pos);
                temp_board.addPiece(current_pos,curr_piece);
            }
        }


        Collection<ChessMove> moves = piece.pieceMoves(temp_board,startPosition);
        Collection<ChessMove> final_moves = new ArrayList<>();
        for(var move:moves){
            var p1 = board.getPiece(move.getEndPosition());
            var p2 = board.getPiece(move.getStartPosition());
            board.addPiece(move.getEndPosition(),null);
            board.addPiece(move.getStartPosition(),null);
            board.addPiece(move.getEndPosition(),piece);
            boolean inCheck = isInCheck(piece.getTeamColor());
            if(!inCheck){
                final_moves.add(move);
            }
            board.addPiece(move.getStartPosition(),p2);
            board.addPiece(move.getEndPosition(),p1);
        }

        setBoard(temp_board);
        return final_moves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        var start_pos = move.getStartPosition();
        var end_pos = move.getEndPosition();

        ChessPiece piece = board.getPiece(start_pos);
        if(piece==null){
            throw new InvalidMoveException("Not a valid move");
        }

        var valid_moves = validMoves(start_pos);
        if(!valid_moves.contains(end_pos)){
            throw new InvalidMoveException("Not a valid move");
        }


        board.addPiece(start_pos,null);
        board.addPiece(end_pos,piece);
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
                ChessPosition current_pos = new ChessPosition(i,j);
                var curr_piece = board.getPiece(current_pos);
                if(curr_piece==null){
                    continue;
                }
                if((curr_piece.getTeamColor() == teamColor) && curr_piece.getPieceType()== ChessPiece.PieceType.KING){
                    kingpos = current_pos;
                    break outerloop;
                }
            }
        }


        //Another nested for loop to find all legal moves, see if king pos is in there
        for(int i=1; i<=8;i++){
            for(int j=1;j<=8;j++){
                ChessPosition current_pos = new ChessPosition(i,j);
                var curr_piece = board.getPiece(current_pos);
                if(curr_piece==null){
                    continue;
                }
                if((curr_piece.getTeamColor() != teamColor)){
                    var attacks = curr_piece.pieceMoves(board,current_pos);
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
                    ChessPosition current_pos = new ChessPosition(i,j);
                    var curr_piece = board.getPiece(current_pos);
                    if(curr_piece==null){
                        continue;
                    }
                    if((curr_piece.getTeamColor() == teamColor)){
                        var moves = curr_piece.pieceMoves(board,current_pos);
                        for(var move:moves){
                            var p1 = board.getPiece(move.getEndPosition());
                            var p2 = board.getPiece(move.getStartPosition());
                            board.addPiece(move.getEndPosition(),null);
                            board.addPiece(move.getStartPosition(),null);
                            board.addPiece(move.getEndPosition(),curr_piece);
                            boolean inCheck = isInCheck(curr_piece.getTeamColor());
                            if(!inCheck){
                                return false;
                            }
                            board.addPiece(move.getStartPosition(),p2);
                            board.addPiece(move.getEndPosition(),p1);
                        }
                    }
                }
            }

        }
        return true;
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
                ChessPosition current_pos = new ChessPosition(i,j);
                var curr_piece = board.getPiece(current_pos);
                if(curr_piece==null){
                    continue;
                }
                if((curr_piece.getTeamColor() == teamColor)){
                    if(!validMoves(current_pos).isEmpty()){
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
