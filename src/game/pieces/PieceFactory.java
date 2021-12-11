package game.pieces;
import game.Color;
import game.Position;

import static game.Color.*;


public class PieceFactory {
    public static Piece createPiece (Piece original) {
        Piece copy;
        if(original != null) {
            copy = switch (original.getType()) {
                case KING -> new King((King) original);
                case QUEEN -> new Queen((Queen) original);
                case ROOK -> new Rook((Rook) original);
                case BISHOP -> new Bishop((Bishop) original);
                case KNIGHT -> new Knight((Knight) original);
                case PAWN -> new Pawn((Pawn) original);
                default -> null;
            };
        }
        else
        {
            copy = null;
        }
        return copy;
    }

    public static Piece createPromotionPiece (String type, Piece original) {
        Piece newPiece;
        Position originalPosition=original.getPosition();
        Color originalColor=original.getColor();
              newPiece = switch (type) {
                case "Queen" -> new Queen(originalColor,1,originalPosition);
                case "Rook" -> new Rook(originalColor,1,1,originalPosition);
                case "Bishop" -> new Bishop(originalColor,1,originalPosition);
                case "Knight" -> new Knight(originalColor,1,originalPosition);
                default -> null;
            };

        return newPiece;
    }

}