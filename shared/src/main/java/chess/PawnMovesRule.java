package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor type = board.getPiece(myPosition).getTeamColor();
        if(type== ChessGame.TeamColor.WHITE){
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() );
            ChessPiece piece = board.getPiece(newPosition);
            if(myPosition.getRow() < 7) {
                if (piece == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));}
            }
            if(myPosition.getRow() == 7){
                promotion(myPosition, moves, newPosition, piece);
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    captureRight(board, myPosition, moves, captureLeft, captureLeftPiece);
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    captureRight(board, myPosition, moves, captureRight, captureRightPiece);
                }
            }
            if(myPosition.getRow() == 2){
                ChessPosition firstMovePosition = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() );
                ChessPiece firstMovePiece = board.getPiece(firstMovePosition);
                if(firstMovePiece==null && piece==null){
                    moves.add(new ChessMove(myPosition,firstMovePosition,null));
                }
            }
            if(myPosition.getRow() < 7) {
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    if(captureLeftPiece!=null && (captureLeftPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor())) {
                        moves.add(new ChessMove(myPosition, captureLeft, null));
                    }
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    if(captureRightPiece!=null && (captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor())) {
                        moves.add(new ChessMove(myPosition, captureRight, null));
                    }
                }
            }
        }else if(type== ChessGame.TeamColor.BLACK){
            ChessPosition newPosition = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() );
            ChessPiece piece = board.getPiece(newPosition);
            if(myPosition.getRow() > 2) {
                if (piece == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
            }
            if(myPosition.getRow() == 2){
                promotion(myPosition, moves, newPosition, piece);
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    captureRight(board, myPosition, moves, captureLeft, captureLeftPiece);
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    captureRight(board, myPosition, moves, captureRight, captureRightPiece);
                }
            }
            if(myPosition.getRow() == 7){
                ChessPosition firstMovePosition = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() );
                ChessPiece firstMovePiece = board.getPiece(firstMovePosition);
                if(firstMovePiece==null && piece==null){
                    moves.add(new ChessMove(myPosition,firstMovePosition,null));
                }
            }
            if(myPosition.getRow() > 2) {
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    if(captureLeftPiece!=null && (captureLeftPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor())) {
                        moves.add(new ChessMove(myPosition, captureLeft, null));
                    }
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    if(captureRightPiece!=null&&(captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor())) {
                        moves.add(new ChessMove(myPosition, captureRight, null));
                    }
                }
            }
        }return moves;
    }

    private void captureRight(ChessBoard board,ChessPosition myPosition,Collection<ChessMove>
            moves,ChessPosition captureRight,ChessPiece captureRightPiece){
        if(captureRightPiece!=null) {
            if (captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.QUEEN));
                moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.BISHOP));
                moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.KNIGHT));
                moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.ROOK));
            }
        }
    }

    private void promotion(ChessPosition myPosition, Collection<ChessMove> moves, ChessPosition newPosition, ChessPiece piece) {
        if(piece==null){
            moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.QUEEN));
            moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.BISHOP));
            moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.KNIGHT));
            moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.ROOK));
        }
    }
}
