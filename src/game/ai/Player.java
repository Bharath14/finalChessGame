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

    public Player(Color color) {
        this.playercolor = color;
    }

    Player(Player player){
        this(player.getColor());
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
            return "White";
        }
        else
        {
            return "Black";
        }
    }


}
