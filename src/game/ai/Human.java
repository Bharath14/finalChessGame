package game.ai;

import game.Cell;
import game.Color;
import game.Game;
import game.GameUtils;
import game.pieces.King;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Human extends Player {

    public Human(Color color) {
        super(color);
    }
    public Human(Human player){
        this(player.getColor());
    }


    @Override
    public boolean move(Game game) {
        Cell source = this.getSource();
        Cell dest = this.getDest();
        if (!game.validSourceSelection(source)) {
            System.out.println("notvalidss");
            return false;
        }
        ArrayList<Cell> moves = source.getPiece().legalMoves(game.getBoard().getCells());
        List<Pair<Cell, Cell>> allmoves = new ArrayList<Pair<Cell, Cell>>();
        for (Cell cell : moves) {
            Pair<Cell, Cell> move = new Pair<Cell, Cell>(source, cell);
            allmoves.add(move);
        }
        System.out.println("legal move:" + allmoves.size());
        List<Pair<Cell, Cell>> filteredmoves = GameUtils.allFilteredMoves(game, allmoves);
        System.out.println(filteredmoves.size());

        for (Pair<Cell, Cell> move : filteredmoves) {
            //System.out.println(dest.toCharacter());
            //System.out.println(move.getValue().toCharacter());
            if (move.getValue() == dest) {
                game.playTurn(source, dest);
                System.out.println("Validss");
                return true;
            }
        }
        //System.out.println("at the end");
        return false;
    }
}