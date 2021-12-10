package game.pieces;

import game.Cell;
import game.Color;
import game.pieces.Piece;
import game.Position;

import java.util.ArrayList;

public final class King extends Piece {
    private final static int[] X_DIRECTIONS={-1,-1,-1,0,0,1,1,1};
    private final static int[] Y_DIRECTIONS={-1,0,1,-1,1,-1,0,1};
    private int isMoved;
    public King(final Color color, final int life, final int isMoved, final Position piecePosition)
    {
        super(PieceType.KING,color,life,piecePosition, 100);
        this.isMoved=isMoved;
    }

    public King(King piece){
        this(piece.getColor(), piece.getLife(),piece.getIsMoved(), piece.getPosition());
    }

    @Override
    public ArrayList<Cell> legalMoves(Cell[][] cells)
    {
        final ArrayList<Cell> highlightCells = new ArrayList<>();
        final int x = this.position.getXCoordinate();
        final int y = this.position.getYCoordinate();

        //check all 8 directions
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
        highlightCells.addAll(this.calculateKingCastles(cells));
        return highlightCells;
    }
    public ArrayList<Cell> calculateKingCastles(Cell[][] cells){
        ArrayList<Cell> kingCastles = new ArrayList<>();
        final int x = this.position.getXCoordinate();
        final int y = this.position.getYCoordinate();
        Cell kingCell =cells[x][y];
        if(this.isMoved==0 && !kingCell.isInDanger(cells,this.color)){
            //kingside castle
            Cell rightCell1=cells[x+1][y];
            Cell rightCell2=cells[x+2][y];
            Cell rightRookCell=cells[x+3][y];
            if(rightCell1.getPiece()==null && rightCell2.getPiece()==null && !rightCell1.isInDanger(cells,this.color) && !rightCell2.isInDanger(cells,this.color)){
                if(rightRookCell.getPiece()!=null && rightRookCell.getPiece().getType()== PieceType.ROOK && ((Rook)rightRookCell.getPiece()).getIsMoved()==0) {
                    kingCastles.add(rightCell2);
                }
            }
            //queenside castle
            Cell leftCell1=cells[x-1][y];
            Cell leftCell2=cells[x-2][y];
            Cell leftCell3=cells[x-2][y];
            Cell leftRookCell=cells[x-4][y];
            if(leftCell1.getPiece()==null && leftCell2.getPiece()==null && leftCell3.getPiece()==null && !leftCell1.isInDanger(cells,this.color) && !leftCell2.isInDanger(cells,this.color)){
                if(leftRookCell.getPiece()!=null && leftRookCell.getPiece().getType()== PieceType.ROOK && ((Rook)leftRookCell.getPiece()).getIsMoved()==0) {
                    kingCastles.add(leftCell2);
                }
            }
        }
        return kingCastles;
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
            return "K";
        }
        else{
            return "k";
        }
    }
}