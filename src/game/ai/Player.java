package game.ai;

import game.Cell;
import game.Color;
import game.Game;

public abstract class Player {
    private Color playercolor ;
    private Cell currentsource;
    private Cell currentdest;

    public Player(Color color) {
        this.playercolor = color;
    }

    public void setSource(Cell source)
    {
        this.currentsource = source;
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

    public boolean move(Game game) {
        return true;
    }

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
