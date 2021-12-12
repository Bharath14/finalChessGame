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
            System.out.println("Do you want to resign ? (Y/N)");
            String wresign = sc.next();
            if(wresign.equals("Y"))
            {
                game.setGameStatus(BLACKWINS);
                break;
            }
            else {
                while (flagwhite == 0) {
                    //System.out.println("White turn");
                    System.out.println("Enter Source");
                    int x1 = sc.nextInt();
                    int y1 = sc.nextInt();
                    boolean result = false;
                    if (x1 >= 0 && x1 < 8 && y1 >= 0 && y1 < 8) {
                        Cell whitesource = cells[x1][y1];
                        if (white.setSource(whitesource, game)) {
                            System.out.println("Enter Destination");
                            int x2 = sc.nextInt();
                            int y2 = sc.nextInt();
                            if (x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
                                Cell whitedest = cells[x2][y2];
                                white.setDest(whitedest);
                                result = white.move(game);
                            } else {
                                System.out.println("Invalid board coordinates! Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid board coordinates! Please try again.");
                    }
                    if (result == true) {
                        flagwhite = 1;
                    } else {
                        flagwhite = 0;
                    }
                    //System.out.println("Original Color:"+game.getCurrentTurn().toCharacter());
                }
                GameInfo.printState(game);
            }
            if(game.getGameStatus() == GameStatus.CONTINUE) {
                System.out.println("Do you want to resign ? (Y/N)");
                String bresign = sc.next();
                if (bresign.equals("Y")) {
                    game.setGameStatus(WHITEWINS);
                    break;
                } else {
                    int flagblack = 0;
                    while (flagblack == 0) {
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
                        if (black.move(game) == true) {
                            flagblack = 1;
                        } else {
                            flagblack = 0;
                        }
                    }
                    GameInfo.printState(game);
                    //System.out.println("Original color:"+game.getCurrentTurn().toCharacter());
                }
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
