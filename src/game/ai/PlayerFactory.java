package game.ai;

import game.Color;
import game.Position;
import game.pieces.*;

public class PlayerFactory {
    public static Player createPlayer(Player original) {
        Player copy;
        if (original != null) {
            copy = switch (original.getType()) {
                case Minimax -> new Minimax((Minimax) original);
                case AlphaBeta -> new AlphaBeta((AlphaBeta) original);
                case Human -> new Human((Human) original);
                default -> null;
            };
        } else {
            copy = null;
        }
        return copy;
    }

    public static Player createPlayerfromstring (String type, Color color) {
        Player newPlayer;
        newPlayer = switch (type) {
            case "HUMAN" -> new Human(color);
            case "ALPHABETA" -> new AlphaBeta(color);
            case "MINIMAX" -> new Minimax(color);
            default -> null;
        };

        return newPlayer;
    }
}
