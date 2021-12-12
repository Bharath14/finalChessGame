package game;

import game.ai.*;

import  java.util.*;

import static game.GameStatus.*;

public class ChessConsole {
    public static void movehuman(Game game, Cell[][] cells) {
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        Player player = game.getCurrentTurn();
        while (flag == 0) {
            System.out.println("Enter Source");
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            boolean result = false;
            if (x1 >= 0 && x1 < 8 && y1 >= 0 && y1 < 8) {
                Cell source = cells[x1][y1];
                if (player.setSource(source, game)) {
                    System.out.println("Enter Destination");
                    int x2 = sc.nextInt();
                    int y2 = sc.nextInt();
                    if (x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
                        Cell dest = cells[x2][y2];
                        player.setDest(dest);
                        result = player.move(game);
                    } else {
                        System.out.println("Invalid board coordinates! Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid board coordinates! Please try again.");
            }
            if (result == true) {
                flag = 1;
            } else {
                flag = 0;
            }
        }
    }
    public static void moveai(Game game)
    {
        Player player = game.getCurrentTurn();
        boolean result = player.move(game);
    }
    public static void resign(Game game)
    {
        if(game.getCurrentTurn().getColor() == Color.WHITE)
        {
            game.setGameStatus(BLACKWINS);
            System.out.println("Black Wins !!");
            System.exit(0);
        }
        else
        {
            game.setGameStatus(WHITEWINS);
            System.out.println("White Wins !!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
       //Player white = new Human(Color.WHITE) ;
        //Player black = new Human(Color.BLACK);
        Scanner sc= new Scanner(System.in);
        System.out.println("1. Human");
        System.out.println("2. Minimax");
        System.out.println("3. AlphaBeta");
        System.out.println("Select White player from above list (Enter player in all CAPS) !!");
        String player = sc.next();
        Player white = PlayerFactory.createPlayerfromstring(player, Color.WHITE);
        System.out.println("Select Black player from above list (Enter player in all CAPS) !!");
        player = sc.next();
        Player black = PlayerFactory.createPlayerfromstring(player, Color.BLACK);

        Game game = new Game(white, black);
        System.out.println(game.getWhitePlayer().toCharacter());
        System.out.println(game.getBlackPlayer().toCharacter());
        GameInfo.printState(game);
        Cell[][] cells = game.getBoard().getCells();

        while(game.getGameStatus() == GameStatus.CONTINUE)
        {
            if(white instanceof Human)
            {
                movehuman(game, cells);
            }
            else if( white instanceof Ai)
            {
                moveai(game);
            }
            GameInfo.printState(game);
            if(game.getGameStatus() == GameStatus.CONTINUE) {

                if(black instanceof Human)
                {
                    movehuman(game, cells);
                }
                else if( black instanceof Ai)
                {
                    moveai(game);
                }
                GameInfo.printState(game);

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
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                try {
                    Thread.sleep(200);
                    System.out.println("shutting down");
                }
                catch (InterruptedException e)
                {
                    System.out.println("Exiting");
                    Thread.currentThread().interrupt();
                }
            }
        });

    }


}
