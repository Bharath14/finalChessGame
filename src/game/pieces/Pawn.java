package game.pieces;

import game.Cell;
import game.Color;
import game.Position;

import java.util.ArrayList;


public class Pawn extends Piece
{
    private boolean RecentMoveWasJump ;
    private int isMoved;
    private Cell empasantCell;
    private boolean empasantmove = false;
    public Pawn(Color color, int life, int isMoved, Position position)
    {
        super(PieceType.PAWN,color, life, position, 10);
        this.isMoved = isMoved;
    }
    public Pawn(Pawn piece){
        this(piece.getColor(), piece.getLife(),piece.getIsMoved(), piece.getPosition());
    }
    public void setRecentMoveWasJump(boolean newValue)
    {
        this.RecentMoveWasJump = newValue;
    }

    public boolean getRecentMoveWasJump()
    {
        return this.RecentMoveWasJump;
    }
    @Override
    public ArrayList<Cell> legalMoves(Cell cells[][]) {
        ArrayList<Cell> legalmoves = new ArrayList<Cell>();
        Position startPosition = this.getPosition();
        int x = startPosition.getXCoordinate();
        int y = startPosition.getYCoordinate();
        if(this.getColor() == Color.WHITE)
        {
            if(y<=5) // Changethis
            {
                Cell destCell1 = cells[x][y+1];
                Piece destPiece1 = destCell1.getPiece();
                if(x-1>=0) {
                    Cell destCell2 = cells[x - 1][y + 1];
                    Piece destPiece2 = destCell2.getPiece();
                    if(destPiece2 != null && destPiece2.getColor() != this.getColor())
                    {
                        legalmoves.add(destCell2);
                    }
                    Cell destCell6 = cells[x-1][y];
                    Piece destPiece6 = destCell6.getPiece();


                    if((destPiece6 != null && destPiece6 instanceof Pawn && destPiece6.getColor() != this.getColor()))
                    {
                        //System.out.println("Empasant move");
                        if(((Pawn)destPiece6).getRecentMoveWasJump() == true)
                        {
                            //System.out.println("Emphasant legal move");
                            legalmoves.add(cells[x-1][y+1]);
                            this.empasantmove = true;
                            this.empasantCell = destCell6;
                        }
                    }
                }
                if(x+1<=7) {
                    Cell destCell3 = cells[x + 1][y + 1];
                    Piece destPiece3 = destCell3.getPiece();
                    if(destPiece3 != null && destPiece3.getColor() != this.getColor())
                    {
                        legalmoves.add(destCell3);
                    }
                    Cell destCell5 = cells[x+1][y];
                    Piece destPiece5 = destCell5.getPiece();
                    if((destPiece5 != null && destPiece5 instanceof Pawn && destPiece5.getColor() != this.getColor()))
                    {
                        if(((Pawn)destPiece5).getRecentMoveWasJump() == true)
                        {
                            legalmoves.add(cells[x+1][y+1]);
                            this.empasantmove = true;
                            this.empasantCell = destCell5;
                        }
                    }
                }

                if(destPiece1 == null)
                {
                    legalmoves.add(destCell1);
                }

                if(y==1)
                {
                    Cell destCell4 = cells[x][y+2];
                    Piece destPiece4 = destCell4.getPiece();
                    if(destPiece4 == null)
                    {
                        legalmoves.add(destCell4);
                    }
                }



            }
            else if(y==6)
            {
                //pawnPromotion
                Cell destCell1 = cells[x][7];
                Piece destPiece1 = destCell1.getPiece();
                Cell destCell2 = cells[x-1][7];
                Piece destPiece2 = destCell2.getPiece();
                Cell destCell3 = cells[x+1][7];
                Piece destPiece3 = destCell3.getPiece();

                if(destPiece1 == null || (destPiece2 != null && destPiece2.getColor() != this.getColor()) ||
                        (destPiece3 != null && destPiece3.getColor() != this.getColor()))
                {
                    /*destCell1.setPiece(Queen);
                    legalmoves.add(destCell1);
                    destCell1.setPiece(Rook);
                    legalmoves.add(destCell1);
                    destCell1.setPiece(Knight);
                    legalmoves.add(destCell1);
                    destCell1.setPiece(Bishop);
                    legalmoves.add(destCell1);*/
                }
            }
        }
        else
        {
            if(y>=1)
            {
                Cell destCell1 = cells[x][y-1];
                Piece destPiece1 = destCell1.getPiece();
                if(x+1<=7) {
                    Cell destCell2 = cells[x + 1][y - 1];
                    Piece destPiece2 = destCell2.getPiece();
                    if(destPiece2 != null && destPiece2.getColor() != this.getColor())
                    {
                        legalmoves.add(destCell2);
                    }
                    Cell destCell5 = cells[x+1][y];
                    Piece destPiece5 = destCell5.getPiece();
                    if((destPiece5 != null && destPiece5 instanceof Pawn && destPiece5.getColor() != this.getColor()))
                    {
                        if(((Pawn)destPiece5).getRecentMoveWasJump() == true)
                        {
                            legalmoves.add(cells[x+1][y-1]);
                            this.empasantmove = true;
                            this.empasantCell = destCell5;
                        }
                    }
                }
                if(x-1>=0) {
                    Cell destCell3 = cells[x - 1][y - 1];
                    Piece destPiece3 = destCell3.getPiece();
                    if(destPiece3 != null && destPiece3.getColor() != this.getColor())
                    {
                        legalmoves.add(destCell3);
                    }
                    Cell destCell6 = cells[x-1][y];
                    Piece destPiece6 = destCell6.getPiece();


                    if((destPiece6 != null && destPiece6 instanceof Pawn && destPiece6.getColor() != this.getColor()))
                    {
                        if(((Pawn)destPiece6).getRecentMoveWasJump() == true)
                        {
                            legalmoves.add(cells[x-1][y-1]);
                            this.empasantmove = true;
                            this.empasantCell = destCell6;
                        }
                    }
                }
                if(destPiece1 == null) {
                    legalmoves.add(destCell1);
                }

                if(y==6)
                {
                    Cell destCell4 = cells[x][y-2];
                    Piece destPiece4 = destCell4.getPiece();
                    if(destPiece4 == null)
                    {
                        legalmoves.add(destCell4);
                    }
                }



            }
            else if(y==1)
            {
                //pawnPromotion
                Cell destCell1 = cells[x][0];
                Piece destPiece1 = destCell1.getPiece();
                Cell destCell2 = cells[x-1][0];
                Piece destPiece2 = destCell2.getPiece();
                Cell destCell3 = cells[x+1][0];
                Piece destPiece3 = destCell3.getPiece();

                if(destPiece1 == null || (destPiece2 != null && destPiece2.getColor() != this.getColor()) ||
                        (destPiece3 != null && destPiece3.getColor() != this.getColor()))
                {
                    /*destCell1.setPiece(Queen);
                    legalmoves.add(destCell1);
                    destCell1.setPiece(Rook);
                    legalmoves.add(destCell1);
                    destCell1.setPiece(Knight);
                    legalmoves.add(destCell1);
                    destCell1.setPiece(Bishop);
                    legalmoves.add(destCell1);*/
                }
            }
        }
        return legalmoves;
    }
    public int getIsMoved()
    {
        return this.isMoved;
    }

    public void setIsMoved(int newValue)
    {
        this.isMoved = newValue;
    }

    public boolean getEmpasantmove()
    {
        return this.empasantmove;
    }

    public Cell getEmpasantcell()
    {
        return this.empasantCell;
    }

    public void setEmpasantmove(boolean newvalue)
    {
        this.empasantmove = newvalue;
    }
    @Override
    public String toCharacter(){
        if(this.color==Color.WHITE) {
            return "P";
        }
        else{
            return "p";
        }
    }
}
