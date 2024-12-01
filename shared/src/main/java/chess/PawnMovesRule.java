package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesRule extends PieceMoveRule {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor type = board.getPiece(myPosition).getTeamColor();

        //WHITE
        if(type== ChessGame.TeamColor.WHITE){
            //Forward
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() );
            ChessPiece piece = board.getPiece(newPosition);

            if(myPosition.getRow() < 7) {
                if (piece == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
            }
            //ALL PROMOTION CASES
            if(myPosition.getRow() == 7){
                if(piece==null){
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.ROOK));
                }
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    if(captureLeftPiece!=null) {
                        if (captureLeftPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.QUEEN));
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.BISHOP));
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.KNIGHT));
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.ROOK));
                        }
                    }
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    if(captureRightPiece!=null) {
                        if (captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.QUEEN));
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.BISHOP));
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.KNIGHT));
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.ROOK));
                        }
                    }
                }
            }

            if(myPosition.getRow() == 2){
                ChessPosition firstMovePosition = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn() );
                ChessPiece firstMovePiece = board.getPiece(firstMovePosition);
                if(firstMovePiece==null && piece==null){
                    moves.add(new ChessMove(myPosition,firstMovePosition,null));
                }
            }


            //capture
            if(myPosition.getRow() < 7) {
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    if(captureLeftPiece!=null) {
                        if (captureLeftPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureLeft, null));
                        }
                    }
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    if(captureRightPiece!=null) {
                        if (captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureRight, null));
                        }
                    }
                }
            }

        }


        //BLACK

        else if(type== ChessGame.TeamColor.BLACK){
            //Forward
            ChessPosition newPosition = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() );
            ChessPiece piece = board.getPiece(newPosition);

            if(myPosition.getRow() > 2) {
                if (piece == null) {
                    moves.add(new ChessMove(myPosition, newPosition, null));
                }
            }
            //PROMOTION CASES
            if(myPosition.getRow() == 2){
                if(piece==null){
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.QUEEN));
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.BISHOP));
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.KNIGHT));
                    moves.add(new ChessMove(myPosition, newPosition, ChessPiece.PieceType.ROOK));
                }
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    if(captureLeftPiece!=null) {
                        if (captureLeftPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.QUEEN));
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.BISHOP));
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.KNIGHT));
                            moves.add(new ChessMove(myPosition, captureLeft, ChessPiece.PieceType.ROOK));
                        }
                    }
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    if(captureRightPiece!=null) {
                        if (captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.QUEEN));
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.BISHOP));
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.KNIGHT));
                            moves.add(new ChessMove(myPosition, captureRight, ChessPiece.PieceType.ROOK));
                        }
                    }
                }
            }

            if(myPosition.getRow() == 7){
                ChessPosition firstMovePosition = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn() );
                ChessPiece firstMovePiece = board.getPiece(firstMovePosition);
                if(firstMovePiece==null && piece==null){
                    moves.add(new ChessMove(myPosition,firstMovePosition,null));
                }
            }


            //capture
            if(myPosition.getRow() > 2) {
                if (myPosition.getColumn() - 1 >= 1) {
                    ChessPosition captureLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()-1 );
                    ChessPiece captureLeftPiece = board.getPiece(captureLeft);
                    if(captureLeftPiece!=null) {
                        if (captureLeftPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureLeft, null));
                        }
                    }
                }
                if (myPosition.getColumn() + 1 <= 8) {
                    ChessPosition captureRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn()+1 );
                    ChessPiece captureRightPiece = board.getPiece(captureRight);
                    if(captureRightPiece!=null) {
                        if (captureRightPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                            moves.add(new ChessMove(myPosition, captureRight, null));
                        }
                    }
                }
            }

        }

        return moves;
    }
}
