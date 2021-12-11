package game;

import javafx.util.Pair;

import java.util.List;

public class GameInfo {
    /**
     * function that prints the state of the board using ascii characters to the terminal for the player to reference
     *
     * @param game variable that stores the piece positions, accessed with game class getPiece()
     */
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

    /**
     * function that prints info about the game such as the turn, fifty-move rule, black or white turn, the FEN, threefold repetition and white and black time
     *
     * @param game variable that stores the piece positions, accessed with game class getPiece()
     */

    public static void printInfo(Game game) {
        System.out.println("Turn " + game.getCurrentTurn().toCharacter() + ":");
        //System.out.println("Fifty-move Rule: " + game.getPeace());
        //System.out.println("Currently " + GameHelper.turnToString(game.getWhiteTurn()) + "'s turn.");
        //System.out.println("FEN: " + toFEN(game));
        //System.out.println("Repetition: " + GameHelper.repetition(game));
        //System.out.println("White Time: " + game.getClock().getWhiteTime());
        //System.out.println("Black Time: " + game.getClock().getBlackTime());
    }

    /**
     * function that prints the current state of the board
     *
     * @param game variable that stores the piece positions, accessed with game class getPiece()
     */

    public static void printBoard(Game game) {
        game.getBoard().toCharacter();
    }
}

