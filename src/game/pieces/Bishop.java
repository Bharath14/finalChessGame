package game.pieces;
import java.util.*;

import game.Cell;
import game.Color;
import game.Position;

public class Bishop extends Piece{

    public Bishop(final Color color, final int life, Position position) {
        super(PieceType.BISHOP, color, life,position, 30);
    }

    public Bishop(Bishop piece){
        this(piece.getColor(), piece.getLife(), piece.getPosition());
    }
    @Override
    public ArrayList<Cell> legalMoves(Cell cells[][])
    {
        //Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
        ArrayList<Cell> legalMoves = new ArrayList<Cell>();
        Position startPosition = this.getPosition();
        int x = startPosition.getXCoordinate();
        int y = startPosition.getYCoordinate();

        int tempx=x+1, tempy=y-1;
        while(tempx<8 && tempy>=0)
        {
            if(cells[tempx][tempy].getPiece()==null)
            {
                legalMoves.add(cells[tempx][tempy]);
            }
            else if(cells[tempx][tempy].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[tempx][tempy]);
                break;
            }
            tempx++;
            tempy--;
        }

        tempx=x-1;
        tempy=y+1;
        while(tempx>=0 && tempy<8)
        {
            if(cells[tempx][tempy].getPiece()==null)
                legalMoves.add(cells[tempx][tempy]);
            else if(cells[tempx][tempy].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[tempx][tempy]);
                break;
            }
            tempx--;
            tempy++;
        }

        tempx=x-1;
        tempy=y-1;
        while(tempx>=0 && tempy>=0)
        {
            if(cells[tempx][tempy].getPiece()==null)
                legalMoves.add(cells[tempx][tempy]);
            else if(cells[tempx][tempy].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[tempx][tempy]);
                break;
            }
            tempx--;
            tempy--;
        }

        tempx=x+1;
        tempy=y+1;
        while(tempx<8 && tempy<8)
        {
            if(cells[tempx][tempy].getPiece()==null)
                legalMoves.add(cells[tempx][tempy]);
            else if(cells[tempx][tempy].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[tempx][tempy]);
                break;
            }
            tempx++;
            tempy++;
        }
        return legalMoves;
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