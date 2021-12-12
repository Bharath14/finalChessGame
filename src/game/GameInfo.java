package game;

import javafx.util.Pair;

import java.util.List;

public class GameInfo {

    public static void printState(Game game) {
        printInfo(game);

        List<Pair<Cell, Cell>> allmoves = GameUtils.allLegalMoves(game);
        /*for(Pair<Cell, Cell> move: allmoves)
        {
            System.out.println("PieceType:" + move.getKey().toCharacter() + ", "+
                    "SourcePosition :" + move.getKey().getPosition().toCharacter() + ", " + "DestPosition : "+ move.getValue().getPosition().toCharacter() +
                    "DestPieceType: "+ move.getValue().toCharacter());
        }*/


        //printHistory(game);
        //System.out.println();

        printBoard(game);
    }



    public static void printInfo(Game game) {
        System.out.println("Turn " + game.getCurrentTurn().toCharacter() + ":");
        //System.out.println("Fifty-move Rule: " + game.getPeace());
        //System.out.println("Currently " + GameHelper.turnToString(game.getWhiteTurn()) + "'s turn.");
        //System.out.println("FEN: " + toFEN(game));
        //System.out.println("Repetition: " + GameHelper.repetition(game));
        //System.out.println("White Time: " + game.getClock().getWhiteTime());
        //System.out.println("Black Time: " + game.getClock().getBlackTime());
    }



    public static void printBoard(Game game) {
        game.getBoard().toCharacter();
    }
}

