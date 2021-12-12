package game.ai;

import game.Cell;
import game.Color;
import game.Game;
import game.pieces.Piece;
import game.pieces.PieceType;

public abstract class Player {
    private Color playercolor ;
    private Cell currentsource;
    private Cell currentdest;
    private PlayerType type;

    public Player(PlayerType type,Color color) {
        this.playercolor = color;
        this.type = type;
    }

    Player(Player player){
        this(player.getType(),player.getColor());
    }

    public boolean setSource(Cell source, Game game)
    {
        if(!game.validSourceSelection(source))
        {
            return false;
        }
        this.currentsource = source;
        return true;
    }

    public PlayerType getType()
    {
        return this.type;
    }

    public void setType(PlayerType newType)
    {
        this.type = newType;
    }

    public void setDest(Cell dest)
    {
        this.currentdest = dest;
    }

    public Cell getSource()
    {
        return this.currentsource ;
    }

    public Cell getDest()
    {
        return this.currentdest;
    }
    public Color getColor() {
        return this.playercolor;
    }

    public abstract boolean move(Game game);

    public String toCharacter()
    {
        if(this.playercolor == Color.WHITE)
        {
            return "White:" +" " +this.type;
        }
        else
        {
            return "Black:" +" " +this.type;
        }
    }


}
