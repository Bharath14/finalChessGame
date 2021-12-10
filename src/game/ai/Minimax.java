package game.ai;

import java.util.*;

import game.*;
import game.pieces.PieceType;
import javafx.util.Pair;
import game.pieces.Piece;


public class Minimax extends Player {

    public Minimax(Color color) {
        super(color);
    }

    public Minimax(Minimax player){
        this(player.getColor());
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
        Pair<Pair<Cell, Cell>, Integer> move = Minimax_execute(4, game);
        Cell source = move.getKey().getKey();
        Cell dest = move.getKey().getValue();
        this.setDest(dest);
        this.setSource(source);
        if(game.validSourceSelection(source)) {
            if (source != null && dest != null) {
                game.playTurn(source, dest);
                return true;
            }
        }
        return false;
    }

    public Pair<Pair<Cell, Cell>, Integer> Minimax_execute(int depth, Game game) {
        List<Pair<Cell, Cell>> allCells = GameUtils.allLegalMoves(game);
        List<Pair<Cell, Cell>> filteredCells = GameUtils.allFilteredMoves(game, allCells);
        List<Pair<Pair<Cell, Cell>, Integer>> moveValue = new ArrayList<Pair<Pair<Cell, Cell>, Integer>>();

        //System.out.println(filteredCells.size());
        /*for (Pair<Cell, Cell> move : filteredCells) {
            System.out.println(move.getKey().toCharacter());
        }*/
        if (depth < 1) {
            return null;
        }

        else if (depth == 1) {
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
        } else {
            for (Pair<Cell, Cell> move : filteredCells) {
                    Position sourcepos = move.getKey().getPosition();
                    Position destpos = move.getValue().getPosition();
                    Game tempgame = new Game(game);
                    Cell[][] tempcells = tempgame.getBoard().getCells();
                    //System.out.println(game.getCurrentTurn().toCharacter());
                    //System.out.println(tempgame.getCurrentTurn().toCharacter());
                    tempgame.playTurn(tempcells[sourcepos.getXCoordinate()][sourcepos.getYCoordinate()], tempcells[destpos.getXCoordinate()][destpos.getYCoordinate()]);
                    //tempgame.getBoard().toCharacter();
                    //System.out.println(tempgame.getCurrentTurn().toCharacter());
                    //System.out.println(game.getCurrentTurn().toCharacter());
                    Minimax tempAI = new Minimax(this.getColor());
                /*if(this.getColor() == Color.WHITE) {
                    tempAI = new Minimax(Color.BLACK);
                }*/

                    int value = tempAI.Minimax_execute(depth - 1, tempgame).getValue();
                    Pair<Pair<Cell, Cell>, Integer> temp = new Pair<Pair<Cell, Cell>, Integer>(move, value);
                    moveValue.add(temp);
                }
        }

        if (this.getColor() == Color.WHITE) {
            if(moveValue.size() !=0) {
                return max(moveValue);
            }
            else
            {
                return new Pair<>(new Pair<>(null, null), 0);
            }
        } else {
            if(moveValue.size() !=0) {
                return min(moveValue);
            }
            else
            {
                return new Pair<>(new Pair<>(null, null), 0);
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