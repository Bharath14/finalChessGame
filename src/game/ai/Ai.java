package game.ai;
import game.*;
import game.pieces.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Ai extends Player{
    public Ai(Color color)
    {
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
        Pair<Pair<Cell, Cell>, Integer> move = this.execute(4,Integer.MIN_VALUE, Integer.MAX_VALUE, game);
        Cell source = move.getKey().getKey();
        Cell dest = move.getKey().getValue();
        this.setDest(dest);
        boolean res = this.setSource(source, game);
        if(res) {
            if (source != null && dest != null) {
                        game.playTurn(source, dest);
                        return true;
            }
        }
        return false;
    }

    public Pair<Pair<Cell, Cell>, Integer> execute(int depth,int alpha, int beta, Game game) {
        List<Pair<Cell, Cell>> allCells = GameUtils.allLegalMoves(game);
        List<Pair<Cell, Cell>> filteredCells = GameUtils.allFilteredMoves(game, allCells);
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();

        if (depth < 1) {
            return null;
        }
        else if (depth == 1) {
            moveValue = depth_1(filteredCells, game, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        else {
            moveValue = depthGreaterthanZero(filteredCells, game, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        if (game.getCurrentTurn().getColor() == Color.WHITE) {
            if(moveValue.size() !=0) {
                return max(moveValue);
            }
            else
            {
                return new Pair<>(new Pair<>(null, null), Integer.MAX_VALUE);
            }
        } else {
            if(moveValue.size() !=0) {
                return min(moveValue);
            }
            else
            {
                return new Pair<>(new Pair<>(null, null), Integer.MIN_VALUE);
            }
        }
    }

    public abstract List<Pair<Pair<Cell, Cell>, Integer>> depth_1( List<Pair<Cell, Cell>> filteredCells, Game game, int alpha, int beta);
    public abstract List<Pair<Pair<Cell, Cell>, Integer>> depthGreaterthanZero( List<Pair<Cell, Cell>> filteredCells, Game game, int depth, int alpha, int beta);

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
