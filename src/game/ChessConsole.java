package game;

import game.ai.AlphaBeta;
import game.ai.Human;
import game.ai.Minimax;
import game.ai.Player;

import  java.util.*;

import static game.GameStatus.*;

public class ChessConsole {
    public static void main(String[] args) {
        Player white = new Human(Color.WHITE) ;
        Player black = new Human(Color.BLACK);
        Game game = new Game(white, black);
        GameInfo.printState(game);
        Scanner sc= new Scanner(System.in);

        Cell[][] cells = game.getBoard().getCells();

        while(game.getGameStatus() == GameStatus.CONTINUE)
        {
            int flagwhite = 0;
            while(flagwhite == 0) {
                //System.out.println("White turn");
                System.out.println("Enter Source");
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                Cell whitesource = cells[x1][y1];
                white.setSource(whitesource);

                System.out.println("Enter Destination");
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                Cell whitedest = cells[x2][y2];
                white.setDest(whitedest);
                boolean result = white.move(game);
                if(result == true)
                {
                    flagwhite = 1;
                }
                else
                {
                    flagwhite = 0;
                }
                //System.out.println("Original Color:"+game.getCurrentTurn().toCharacter());
            }
            GameInfo.printState(game);
            if(game.getGameStatus() == GameStatus.CONTINUE)
            {
                int flagblack = 0;
                while(flagblack == 0) {
                    //System.out.println("Black turn");
                    System.out.println("Enter Source");
                    int x3 = sc.nextInt();
                    int y3 = sc.nextInt();
                    Cell blacksource = cells[x3][y3];
                    black.setSource(blacksource, game);

                    System.out.println("Enter Destination");
                    int x4 = sc.nextInt();
                    int y4 = sc.nextInt();
                    Cell blackdest = cells[x4][y4];
                    black.setDest(blackdest);
                    if(black.move(game)==true)
                    {
                        flagblack = 1;
                    }
                    else
                    {
                        flagblack = 0;
                    }
                }
                GameInfo.printState(game);
                //System.out.println("Original color:"+game.getCurrentTurn().toCharacter());
            }
        }

        switch (game.getGameStatus()){
            case WHITEWINS:
                System.out.println("White Wins.");
                break;
            case BLACKWINS:
                System.out.println("Black Wins.");
                break;
            case DRAW:
                System.out.println("Draw by Fifty-move Rule.");
                break;
            default:
                System.out.println("Error: Invalid End.");
                break;
        }

    }

}
