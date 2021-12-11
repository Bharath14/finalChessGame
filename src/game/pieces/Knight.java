package game.pieces;

import java.util.ArrayList;
import game.Color;
import game.Cell;
import game.Position;

public final class Knight extends Piece {
    private final static int[] X_DIRECTIONS={-1,-2,-1,-2,1,2,1,2};
    private final static int[] Y_DIRECTIONS={2,1,-2,-1,2,1,-2,-1};
    public Knight(final Color color, final int life, Position position) {
        super(PieceType.KNIGHT, color, life,position, 30);
    }
    public Knight(Knight piece){
        this(piece.getColor(), piece.getLife(), piece.getPosition());
    }
    @Override
    public ArrayList<Cell> legalMoves(Cell cells[][]) {
        final ArrayList<Cell> highlightCells = new ArrayList<>();
        final int x = this.position.getXCoordinate();
        final int y = this.position.getYCoordinate();

        for(int i=0;i<8;i++){
            int destinationXCoordinate=x,destinationYCoordinate=y;
            destinationXCoordinate+=X_DIRECTIONS[i];
            destinationYCoordinate+=Y_DIRECTIONS[i];

            if(destinationXCoordinate>=0 && destinationXCoordinate<8 && destinationYCoordinate>=0 && destinationYCoordinate<8){
                final Cell destinationCell = cells[destinationXCoordinate][destinationYCoordinate];
                final Piece destinationPiece = destinationCell.getPiece();

                if(destinationPiece==null){
                    highlightCells.add(destinationCell);
                }
                else{
                    if(destinationPiece.getColor()!=this.color){
                        highlightCells.add(destinationCell);
                    }
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
