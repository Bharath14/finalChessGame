package game.pieces;

import java.util.ArrayList;
import game.Color;
import game.Cell;
import game.Position;

public final class Knight extends Piece {
    public Knight(final Color color, final int life, Position position) {
        super(PieceType.KNIGHT, color, life,position, 30);
    }
    public Knight(Knight piece){
        this(piece.getColor(), piece.getLife(), piece.getPosition());
    }
    @Override
    public ArrayList<Cell> legalMoves(Cell cells[][]) {
        ArrayList<Cell> highlightCells = new ArrayList<>();
        Position startPosition = this.getPosition();
        int x = startPosition.getXCoordinate();
        int y = startPosition.getYCoordinate();

        int posx[] = {x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
        int posy[] = {y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};
        int i;
        for (i = 0; i < 8; i++) {
            // Boundary check i.e checking if it is within bounds of the board.
            if (posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8) {
                // If the resulting cell or the final cell has no piece or if it has a piece of the opposite color then, add it to the possible legalMoves.
                final Cell destination = cells[posx[i]][posy[i]];

                final Piece destinationPiece = destination.getPiece();

                //final Color destinationPieceColor = destinationPiece.getColor();

                if (destinationPiece == null || destinationPiece.getColor() != this.getColor()) {
                    // You might have to change this
                    //System.out.println("its knight class");
                    highlightCells.add(cells[posx[i]][posy[i]]);
                }
            }
        }
        return highlightCells;
    }
    @Override
    public String toCharacter(){
        if(this.color==Color.WHITE) {
            return "N";
        }
        else{
            return "n";
        }
    }
}
