package game.pieces;
import java.util.*;

import game.Cell;
import game.Color;
import game.Position;

public class Bishop extends Piece{
    private static final int[] X_DIRECTIONS={-1,-1,1,1};
    private static final int[] Y_DIRECTIONS={-1,1,-1,1};
    public Bishop(final Color color, final int life, Position position) {
        super(PieceType.BISHOP, color, life,position, 30);
    }

    public Bishop(Bishop piece){
        this(piece.getColor(), piece.getLife(), piece.getPosition());
    }
    @Override
    public ArrayList<Cell> legalMoves(Cell cells[][])
    {
        final ArrayList<Cell> highlightCells = new ArrayList<>();
        final int x = this.position.getXCoordinate();
        final int y = this.position.getYCoordinate();

        // check all directions
        for(int i=0;i<4;i++)
        {
            int destinationXCoordinate=x,destinationYCoordinate=y;
            destinationXCoordinate+= X_DIRECTIONS[i];
            destinationYCoordinate+= Y_DIRECTIONS[i];
            while(destinationXCoordinate>=0 && destinationXCoordinate<8 && destinationYCoordinate>=0 && destinationYCoordinate<8)
            {
                final Cell destinationCell = cells[destinationXCoordinate][destinationYCoordinate];
                final Piece destinationPiece = destinationCell.getPiece();
                if(destinationPiece==null){
                    //System.out.println("its null");
                    highlightCells.add(destinationCell);
                }
                else{
                    //System.out.println(destinationPiece.toCharacter());
                    if(destinationPiece.getColor()!= this.color){
                        highlightCells.add(destinationCell);
                    }
                    break;
                }
                destinationXCoordinate+= X_DIRECTIONS[i];
                destinationYCoordinate+= Y_DIRECTIONS[i];
            }
        }
        return highlightCells;
    }
    @Override
    public String toCharacter(){
        if(this.color==Color.WHITE) {
            return "B";
        }
        else{
            return "b";
        }
    }
}