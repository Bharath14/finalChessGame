package game.ai;

import game.*;
import game.pieces.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        ArrayList<Cell> moves = source.getPiece().legalMoves(game.getBoard().getCells());
        List<Pair<Cell, Cell>> allmoves = new ArrayList<Pair<Cell, Cell>>();
        for (Cell cell : moves) {
            Pair<Cell, Cell> move = new Pair<Cell, Cell>(source, cell);
            allmoves.add(move);
        }
        //System.out.println("legal move:" + allmoves.size());
        List<Pair<Cell, Cell>> filteredmoves = GameUtils.allFilteredMoves(game, allmoves);
        //System.out.println(filteredmoves.size());

        for (Pair<Cell, Cell> move : filteredmoves) {
            //System.out.println(dest.toCharacter());
            //System.out.println(move.getValue().toCharacter());
            if (move.getValue() == dest) {
                    game.playTurn(source, dest);
                    //System.out.println("Validss");
                    return true;
            }
        }

        for (Pair<Cell, Cell> move : allmoves){
            if (move.getValue() == dest) {
                if(GameUtils.isInCheck(game.getBoard().getCells(), game.getCurrentTurn().getColor())){
                    System.out.println("Your king is in check! Save him.");
                    return false;
                }
                System.out.println("The move made puts king in danger! Please try another move.");
                return false;
            }
        }

        System.out.println("Selected piece can't reach the destination! Please try again.");
        return false;
    }

}