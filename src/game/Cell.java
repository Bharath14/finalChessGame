package game;

import game.pieces.*;

import static game.pieces.PieceType.*;

/**
 * Represents each square in the chess board
 */
public class Cell{
    private Position cellPosition;
    private Piece piece;

    public Cell(Position position, Piece piece)
    {
        this.cellPosition = position;
        this.piece = piece;
    }

    public Cell(Cell cell) {
        Piece original = cell.getPiece();
        Piece copy;
        if(original != null) {
            switch (original.getType()) {
                case KING:
                    copy = new King((King) original);
                    break;
                case QUEEN:
                    copy = new Queen((Queen) original);
                    break;
                case ROOK:
                    copy = new Rook((Rook) original);
                    break;
                case BISHOP:
                    copy = new Bishop((Bishop) original);
                    break;
                case KNIGHT:
                    copy = new Knight((Knight) original);
                    break;
                case PAWN:
                    copy = new Pawn((Pawn) original);
                    break;
                default:
                    copy = null;
                    break;
            }
        }
        else
        {
            copy = null;
        }
        this.cellPosition = cell.getPosition();
        this.piece = copy;
    }

    public Position getPosition(){
        return cellPosition;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece= piece;
    }

    public void removePiece(){
        this.piece= null;
    }

    public boolean isInDanger(Cell cells[][], Color color){
        final int x = this.cellPosition.getXCoordinate();
        final int y = this.cellPosition.getYCoordinate();
        int attackerX,attackerY,flag;
        //checking possible attacks from top,bottom,left,right OR in short vertical,horizontal directions (rook,queen,king)
        int horizontal[]={1,-1,0,0};
        int vertical[]={0,0,1,-1};

        for(int i=0;i<4;i++) {
            attackerX=x;
            attackerY=y;
            attackerX+=horizontal[i];
            attackerY+=vertical[i];
            flag=1;
            while(attackerX>=0 && attackerX<8 && attackerY>=0 && attackerY<8){
                Cell attackerCell = cells[attackerX][attackerY];
                if(attackerCell.getPiece()!=null){
                    Piece attackerPiece = attackerCell.getPiece();
                    PieceType attackerPieceType = attackerPiece.getType();
                    if(attackerPiece.getColor()!=color){
                        if(flag==1){
                            if(attackerPieceType== ROOK || attackerPieceType== QUEEN || attackerPieceType== KING){
                                return true;
                            }
                        }
                        else{
                            if(attackerPieceType== ROOK || attackerPieceType== QUEEN){
                                return true;
                            }
                        }
                    }
                    break;
                }
                if(flag==1){flag=0;}
                attackerX+=horizontal[i];
                attackerY+=vertical[i];
            }
        }
        //checking possible attacks from all diagonal directions (bishop,queen,king,pawn)
        int diagonalX[]={1,1,-1,-1};
        int diagonalY[]={1,-1,1,-1};
        for(int i=0;i<4;i++) {
            attackerX=x;
            attackerY=y;
            attackerX+=diagonalX[i];
            attackerY+=diagonalY[i];
            flag=1;
            while(attackerX>=0 && attackerX<8 && attackerY>=0 && attackerY<8){
                Cell attackerCell = cells[attackerX][attackerY];
                if(attackerCell.getPiece()!=null){
                    Piece attackerPiece = attackerCell.getPiece();
                    PieceType attackerPieceType = attackerPiece.getType();
                    if(attackerPiece.getColor()!=color){
                        if(flag==1){
                            if(attackerPieceType== KING || attackerPieceType== PAWN || attackerPieceType== BISHOP || attackerPieceType== QUEEN){
                                return true;
                            }
                        }
                        else{
                            if(attackerPieceType== BISHOP || attackerPieceType== QUEEN){
                                return true;
                            }
                        }
                    }
                    break;
                }
                if(flag==1){flag=0;}
                attackerX+=diagonalX[i];
                attackerY+=diagonalY[i];
            }
        }

        //checking possible attacks from a knight
        int knightX[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
        int knightY[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
        for(int i=0;i<8;i++) {
            if ((knightX[i] >= 0 && knightX[i] < 8 && knightY[i] >= 0 && knightY[i] < 8)) {
                if (cells[knightX[i]][knightY[i]].getPiece() != null && cells[knightX[i]][knightY[i]].getPiece().getColor() != color && (cells[knightX[i]][knightY[i]].getPiece().getType() == KNIGHT)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toCharacter(){
        if(this.getPiece()==null){
            return "x";
        }
        else {
            return this.getPiece().toCharacter();
        }
    }
}