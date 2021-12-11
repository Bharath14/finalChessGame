package game.ai;
import java.util.*;

import game.*;
import game.ai.Player;
import javafx.util.Pair;
import game.pieces.Piece;

public class AlphaBeta extends Ai {

    public AlphaBeta(Color color) {
        super(color);
    }
    public List<Pair<Pair<Cell, Cell>, Integer>> depth_1( List<Pair<Cell, Cell>> filteredCells, Game game, int alpha, int beta)
    {
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();
        double depth_value = Integer.MIN_VALUE;
        for (Pair<Cell, Cell> move : filteredCells) {
            Position sourcepos = move.getKey().getPosition();
            Position destpos = move.getValue().getPosition();
            Game tempgame = new Game(game);
            Cell[][] tempcells = tempgame.getBoard().getCells();
            tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
            int value = this.evaluate(tempgame);
            Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
            moveValue.add(temp);
            if(game.getCurrentTurn().getColor() == Color.WHITE)
            {
                depth_value = Math.max(depth_value, value);
                alpha = Math.max(alpha, (int)depth_value);
            }
            else
            {
                depth_value = Math.min(depth_value, value);
                alpha = Math.min(alpha, (int)depth_value);
            }
            if(alpha>=beta)
            {
                break;
            }
        }
        return moveValue;
    }
    public List<Pair<Pair<Cell, Cell>, Integer>> depthGreaterthanZero( List<Pair<Cell, Cell>> filteredCells, Game game, int depth, int alpha, int beta)
    {
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();
        double depth_value = Integer.MAX_VALUE;
        for (Pair<Cell, Cell> move : filteredCells) {
            Game tempgame = new Game(game);
            Position sourcepos = move.getKey().getPosition();
            Position destpos = move.getValue().getPosition();
            Cell[][] tempcells = tempgame.getBoard().getCells();
            tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
            AlphaBeta tempAI = new AlphaBeta(this.getColor());
            int value = tempAI.execute(depth - 1, alpha, beta, tempgame).getValue();
            Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
            moveValue.add(temp);
            if(game.getCurrentTurn().getColor() == Color.WHITE)
            {
                depth_value = Math.max(depth_value, value);
                alpha = Math.max(alpha, (int)depth_value);
            }
            else
            {
                depth_value = Math.min(depth_value, value);
                alpha = Math.min(alpha, (int)depth_value);
            }
            if(alpha>=beta)
            {
                break;
            }
        }
        return moveValue;
    }

}