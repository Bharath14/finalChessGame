package game.pieces;

import game.Cell;
import game.Color;
import game.Position;

import java.util.ArrayList;

public abstract class Piece {
    protected PieceType type;
    protected Color color;
    protected Position position;
    protected int life;
    protected int value;

    Piece(PieceType type, Color color, int life,Position position, int value)
    {
        this.type = type;
        this.color = color;
        this.life = life;
        this.position = position;
        this.value = value;
    }

    Piece(Piece piece){
        this(piece.getType(),piece.getColor(), piece.getLife(), piece.getPosition(), piece.getValue());
    }

    public PieceType getType()
    {
        return this.type;
    }

    public void setType(PieceType type)
    {
        this.type=type;
    }

    public Color getColor()
    {
        return this.color;
    }

    public Position getPosition()
    {
        return this.position;
    }

    public void setPosition(Position newPosition)
    {
        this.position = newPosition;
    }

    public int getLife()
    {
        return this.life;
    }

    public void setLife(int newLife)
    {
        this.life = newLife;
    }

    public boolean checkPawnPromotion(Cell source)
    {
        if(source.getPiece().getType() != PieceType.PAWN)
        {
            return false;
        }
        else
        {
            if((source.getPiece().getColor() == Color.WHITE && source.getPosition().getYCoordinate() == 6)||
                    (source.getPiece().getColor() == Color.BLACK && source.getPosition().getYCoordinate() == 1))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

    }

    public int getValue(){ return this.value ;}

    public abstract ArrayList<Cell> legalMoves(Cell cells[][]);

    public abstract String toCharacter();
}