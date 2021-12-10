package game.pieces;

import game.Cell;
import game.Color;
import game.Position;

import java.util.ArrayList;
public final class Rook extends Piece {
    private static final int[] X_DIRECTIONS={-1,1,0,0};
    private static final int[] Y_DIRECTIONS={0,0,-1,1};
    private int isMoved;
    public Rook(final Color color, final int life, final int isMoved, final Position piecePosition)
    {
        super(PieceType.ROOK,color,life,piecePosition,50);
        this.isMoved=isMoved;
    }

    public Rook(Rook piece){
        this(piece.getColor(), piece.getLife(),piece.getIsMoved(), piece.getPosition());
    }

    @Override
    public ArrayList<Cell> legalMoves(final Cell[][] cells)
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

    public int getIsMoved()
    {
        return this.isMoved;
    }

    public void setIsMoved(int newValue)
    {
        this.isMoved = newValue;
    }

    @Override
    public String toCharacter(){
        if(this.color==Color.WHITE) {
            return "R";
        }
        else{
            return "r";
        }
    }

}