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
        //this.empasantmove = empasantmove;
        //this.empasantCell = empasantCell;
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
        int[] X_Direction = {0,0,1,-1,-1,1};
        int[] Y_Direction = {1,2,1,1,0,0};
        int x = startPosition.getXCoordinate();
        int y = startPosition.getYCoordinate();
        for(int i =0;i<6;i++)
        {
            int destx = x;
            int desty = y;
            if(this.getColor() == Color.WHITE)
            {
                destx += X_Direction[i];
                desty += Y_Direction[i];
            }
            else
            {
                destx += X_Direction[i];
                desty -= Y_Direction[i];
            }

            if(i==1) {
                int j = 0;
                while (j < 2 && desty>=0 && desty<8) {
                    Cell destcell1 = cells[destx][desty];
                    Piece destpiece1 = destcell1.getPiece();
                    if (destpiece1 == null) {
                        legalmoves.add(destcell1);
                    }
                    j = j + 1;
                    if (this.getColor() == Color.WHITE) {
                        destx += X_Direction[i];
                        desty += Y_Direction[i];
                    } else {
                        destx += X_Direction[i];
                        desty -= Y_Direction[i];
                    }
                }
            }
                else if(i==2||i==3)
                {
                    if(destx>=0&&destx<8&&desty>=0&&desty<8) {
                        Cell destcell2 = cells[destx][desty];
                        Piece destpiece2 = destcell2.getPiece();
                        if (destpiece2 != null && destpiece2.getColor() != this.getColor()) {
                            legalmoves.add(destcell2);
                        }
                    }
                }
                else
            {
                if(destx>=0&&destx<8&&desty>=0&&desty<8) {
                    Cell destcell3 = cells[destx][desty];
                    Piece destpiece3 = destcell3.getPiece();
                    if (destpiece3 != null && destpiece3.getColor() != this.getColor() && destpiece3 instanceof Pawn) {
                        if(((Pawn)destpiece3).getRecentMoveWasJump() == true)
                        {
                            //System.out.println("Emphasant legal move");
                            if (this.getColor() == Color.WHITE) {
                                legalmoves.add(cells[destx][desty+1]);
                            } else {
                                legalmoves.add(cells[destx][desty-1]);
                            }

                            this.empasantmove = true;
                            this.empasantCell = destcell3;
                        }
                    }
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
