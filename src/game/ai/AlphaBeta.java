package game.ai;
import java.util.*;

import game.*;
import game.ai.Player;
import javafx.util.Pair;
import game.pieces.Piece;

public class AlphaBeta extends Player {

    public AlphaBeta(Color color) {
        super(color);
    }

    public int evaluate(Game game) {
        Cell cells[][] = game.getBoard().getCells();
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = cells[i][j].getPiece();
                if (piece != null) {
                    int value = piece.getValue();
                    if (piece.getColor() == Color.WHITE) {
                        sum = sum + value;
                    } else {
                        sum = sum - value;
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public boolean move(Game game) {
        Pair<Pair<Cell, Cell>, Integer> move = AlphaBeta_execute(4, Integer.MIN_VALUE, Integer.MAX_VALUE, game);
        Cell source = move.getKey().getKey();
        Cell dest = move.getKey().getValue();
        this.setDest(dest);
        this.setSource(source);
        if (source != null && dest != null) {
            game.playTurn(source, dest);
            return true;
        }
        return false;
    }

    public Pair<Pair<Cell, Cell>, Integer> AlphaBeta_execute(int depth, int alpha, int beta, Game game) {
        List<Pair<Cell, Cell>> allCells = GameUtils.allLegalMoves(game);
        List<Pair<Cell, Cell>> filteredCells = GameUtils.allFilteredMoves(game, allCells);
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();
        if (depth < 1) {
            return null;
        }

        else if (depth == 1) {
            if(game.getCurrentTurn().getColor() == Color.WHITE) {
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
                    depth_value = Math.max(depth_value, value);
                    alpha = Math.max(alpha, (int)depth_value);
                    if(alpha>=beta)
                    {
                        break;
                    }
                }
                if(moveValue.size() !=0) {
                    return max(moveValue);
                }
                else
                {
                    return new Pair<>(new Pair<>(null, null), 0);
                }
            }
            else
            {
                double depth_value = Integer.MAX_VALUE;
                //System.out.println(filteredCells.size());
                for (Pair<Cell, Cell> move : filteredCells) {
                    Game tempgame = new Game(game);
                    Position sourcepos = move.getKey().getPosition();
                    Position destpos = move.getValue().getPosition();
                    Cell[][] tempcells = tempgame.getBoard().getCells();
                    tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
                    int value = this.evaluate(tempgame);
                    Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
                    moveValue.add(temp);
                    depth_value = Math.min(depth_value, value);
                    alpha = Math.min(alpha, (int)depth_value);
                    if(alpha>=beta)
                    {
                        break;
                    }
                }
                if(moveValue.size() !=0) {
                    return min(moveValue);
                }
                else
                {
                    return new Pair<>(new Pair<>(null, null), 0);
                }
            }
        } else {
            if(game.getCurrentTurn().getColor() == Color.WHITE) {
                double depth_value = Integer.MIN_VALUE;
                for (Pair<Cell, Cell> move : filteredCells) {
                    Game tempgame = new Game(game);
                    Position sourcepos = move.getKey().getPosition();
                    Position destpos = move.getValue().getPosition();
                    Cell[][] tempcells = tempgame.getBoard().getCells();
                    tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
                    AlphaBeta tempAI = new AlphaBeta(this.getColor());
                    int value = tempAI.AlphaBeta_execute(depth - 1, alpha, beta, tempgame).getValue();
                    Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
                    moveValue.add(temp);
                    depth_value = Math.max(depth_value, value);
                    alpha = Math.max(alpha, (int)depth_value);
                    if(alpha>=beta)
                    {
                        break;
                    }
                }
                if(moveValue.size() !=0) {
                    return max(moveValue);
                }
                else
                {
                    return new Pair<>(new Pair<>(null, null), 0);
                }
            }
            else
            {
                double depth_value = Integer.MAX_VALUE;
                for (Pair<Cell, Cell> move : filteredCells) {
                    Game tempgame = new Game(game);
                    Position sourcepos = move.getKey().getPosition();
                    Position destpos = move.getValue().getPosition();
                    Cell[][] tempcells = tempgame.getBoard().getCells();
                    tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
                    AlphaBeta tempAI = new AlphaBeta(this.getColor());
                    int value = tempAI.AlphaBeta_execute(depth - 1, alpha, beta, tempgame).getValue();
                    Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
                    moveValue.add(temp);
                    depth_value = Math.min(depth_value, value);
                    alpha = Math.min(alpha, (int)depth_value);
                    if(alpha>=beta)
                    {
                        break;
                    }
                }
                if(moveValue.size() !=0) {
                    return min(moveValue);
                }
                else
                {
                    return new Pair<>(new Pair<>(null, null), 0);
                }
            }
        }
    }

    public Pair<Pair<Cell, Cell>, Integer> max(List<Pair<Pair<Cell, Cell>, Integer>> moveValue) {
        Pair<Pair<Cell, Cell>, Integer> max = moveValue.get(0);
        for (Pair<Pair<Cell, Cell>, Integer> move : moveValue) {
            if (move.getValue() > max.getValue()) {
                max = move;
            }
        }
        return max;
    }

    public Pair<Pair<Cell, Cell>, Integer> min(List<Pair<Pair<Cell, Cell>, Integer>> moveValue) {
        Pair<Pair<Cell, Cell>, Integer> min = moveValue.get(0);
        for (Pair<Pair<Cell, Cell>, Integer> move : moveValue) {
            if (move.getValue() < min.getValue()) {
                min = move;
            }
        }
        return min;
    }

}