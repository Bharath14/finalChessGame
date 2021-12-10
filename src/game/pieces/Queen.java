package game.pieces;

import java.util.*;
import game.Cell;
import game.Color;
import game.Position;

public class Queen extends Piece{

    public Queen(final Color color, final int life, Position position) {
        super(PieceType.QUEEN, color, life, position, 90);
    }
    public Queen(Queen piece){
        this(piece.getColor(), piece.getLife(), piece.getPosition());
    }
    @Override
    public ArrayList<Cell> legalMoves(Cell cells[][])
    {
        ArrayList<Cell> legalMoves = new ArrayList<Cell>();
        Position startPosition = this.getPosition();
        int x = startPosition.getXCoordinate();
        int y = startPosition.getYCoordinate();

        //Checking possible moves in vertical direction
        int tempx=x-1;
        while(tempx >= 0)
        {
            if(cells[tempx][y].getPiece()==null)
                legalMoves.add(cells[tempx][y]);
            else if(cells[tempx][y].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[tempx][y]);
                break;
            }
            tempx--;
        }

        tempx=x+1;
        while(tempx < 8)
        {
            if(cells[tempx][y].getPiece()==null)
                legalMoves.add(cells[tempx][y]);
            else if(cells[tempx][y].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[tempx][y]);
                break;
            }
            tempx++;
        }


        //Checking possible moves in horizontal Direction
        int tempy=y-1;
        while(tempy >= 0)
        {
            if(cells[x][tempy].getPiece()==null)
                legalMoves.add(cells[x][tempy]);
            else if(cells[x][tempy].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[x][tempy]);
                break;
            }
            tempy--;
        }
        tempy=y+1;
        while(tempy < 8)
        {
            if(cells[x][tempy].getPiece()==null)
                legalMoves.add(cells[x][tempy]);
            else if(cells[x][tempy].getPiece().getColor()==this.getColor())
                break;
            else
            {
                legalMoves.add(cells[x][tempy]);
                break;
            }
            tempy++;
        }

        //Checking for possible moves in diagonal direction
        tempx=x+1;
        tempy=y-1;
        while(tempx<8 && tempy>=0)
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
            return "Q";
        }
        else{
            return "q";
        }
    }
}
