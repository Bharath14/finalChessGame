package game.ai;

import java.util.*;

import game.*;
import game.pieces.PieceType;
import javafx.util.Pair;
import game.pieces.Piece;


public class Minimax extends Ai {

    public Minimax(Color color) {
        super(PlayerType.Minimax,color);
    }

    public Minimax(Minimax player){
        this(player.getColor());
    }

    @Override
    public List<Pair<Pair<Cell, Cell>, Integer>> depth_1( List<Pair<Cell, Cell>> filteredCells, Game game,int alpha, int beta)
    {
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();
        for (Pair<Cell, Cell> move : filteredCells) {
            Position sourcepos = move.getKey().getPosition();
            Position destpos = move.getValue().getPosition();
            //System.out.println("in depth == 1");
            //System.out.println(move.getKey().toCharacter());
            Game tempgame = new Game(game);
            Cell[][] tempcells = tempgame.getBoard().getCells();
            tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
            int value = this.evaluate(tempgame);
            Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
            moveValue.add(temp);
        }
        return moveValue;
    }

    @Override
    public List<Pair<Pair<Cell, Cell>, Integer>> depthGreaterthanZero( List<Pair<Cell, Cell>> filteredCells, Game game, int depth, int alpha, int beta)
    {
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();
        for (Pair<Cell, Cell> move : filteredCells) {
            Position sourcepos = move.getKey().getPosition();
            Position destpos = move.getValue().getPosition();
            Game tempgame = new Game(game);
            Cell[][] tempcells = tempgame.getBoard().getCells();
            tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
            Minimax tempAI = new Minimax(this.getColor());
            int value = tempAI.execute(depth - 1,0,0, tempgame).getValue();
            Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
            moveValue.add(temp);
        }
        return moveValue;
    }


}