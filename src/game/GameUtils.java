package game;

import game.pieces.PieceType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public final class GameUtils{
    // It returns list of all legal moves without filtering...(there won't be checking of king's safety)
    public static List<Pair<Cell, Cell>> allLegalMoves(Game game){
        Cell[][] cells =game.getBoard().getCells();
        List<Pair<Cell, Cell>> moves = new ArrayList<Pair<Cell, Cell>>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if(cells[i][j].getPiece()!=null && cells[i][j].getPiece().getColor()==game.getCurrentTurn().getColor()){
                    List<Cell>legalmoves=cells[i][j].getPiece().legalMoves(cells);
                    if(!legalmoves.isEmpty()){
                        for(Cell x: legalmoves){
                            //System.out.println("PieceType: "+cells[i][j].getPiece().toCharacter());
                            moves.add(new Pair<>(cells[i][j], x));
                        }
                    }
                }
            }
        }
        //System.out.println("legalmoves Size :"+ moves.size());
        return moves;
    }

    public static List<Pair<Cell, Cell>> allFilteredMoves(Game game, List<Pair<Cell, Cell>> moves){
        List<Pair<Cell, Cell>> finalMoves = new ArrayList<Pair<Cell, Cell>>();
        for(Pair<Cell,Cell> move: moves){
            if(!game.willKingBeInDanger(move.getKey(), move.getValue())){
                //System.out.println("in filtered moves");
                finalMoves.add(move);
            }
        }
        return finalMoves;
    }

    /*public static boolean threefoldRepetition(Game game){
        return repetition(game) >= 3;
    }
    public static int repetition(Game game){
        List<String> history = game.getHistory();
        String current = GameInfo.FENBoard(game);
        int repeat = 0;
        for(String states: history)
            if(states.equals(current))
                repeat++;
        return repeat;
    }*/

    // isInCheck() method checks whether currentTurn's king is in check or not
    public static boolean isInCheck(Cell[][] cells, Color color){
        //Cell[][] cells =game.getBoard().getCells();
        for (int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(cells[i][j].getPiece()!=null && cells[i][j].getPiece().getColor()==color && cells[i][j].getPiece().getType()== PieceType.KING){
                    //System.out.println("is in check :first false");
                    return cells[i][j].isInDanger(cells, cells[i][j].getPiece().getColor());
                }
            }
        }
        //System.out.println("is in check :second false");
        return false;
    }

    // If isInCheck() returns true and isInMate() returns true then it's checkmate.
// If isInCheck() returns false and isInMate() returns true then it's stalemate.
    public static boolean isInMate(Game game){
        //System.out.println("filtered moves:" +allFilteredMoves(game, allLegalMoves(game)).size() );
        return allFilteredMoves(game, allLegalMoves(game)).isEmpty();
    }

    public static boolean checkMate(Game game){
        //System.out.println("Is in checkgame "+isInCheck(game));
        //System.out.println("Is in mategame "+isInMate(game));
        return isInCheck(game.getBoard().getCells(), game.getCurrentTurn().getColor()) && isInMate(game);
    }

    public static boolean staleMate(Game game){
        //System.out.println("instalemate :" + isInMate(game) +isInCheck(game.getBoard().getCells(), game.getCurrentTurn().getColor())  );
        return !isInCheck(game.getBoard().getCells(), game.getCurrentTurn().getColor()) && isInMate(game);
    }

    // if fiftyMoveRule returns true then game draws else continues.
    public static boolean fiftyMoveRule(Game game){
        List<Pair<Cell,Cell>>moves = game.getMoveHistory();
        boolean flag=true;
        for(int i=moves.size()-1;i> moves.size()-50;i--){
            if(moves.get(i).getKey().getPiece().getType()==PieceType.PAWN || moves.get(i).getValue().getPiece()!=null){
                flag=false;
                break;
            }
        }
        return flag;
    }
    //PENDING!!
    public static boolean insufficientMating(Game game){
        return false;
    }

    public static void checkStatus(Game game){
        if(fiftyMoveRule(game) || staleMate(game) || insufficientMating(game) /*|| threefoldRepetition(game)*/){
            game.setGameStatus(GameStatus.DRAW);
        }
        else if(checkMate(game)){
            //System.out.println("checkstatus- checkmate");
            if(game.getCurrentTurn().getColor()==Color.BLACK) {
                game.setGameStatus(GameStatus.WHITEWINS);
            }
            else{
                game.setGameStatus(GameStatus.BLACKWINS);
            }
        }
        else{
            game.setGameStatus(GameStatus.CONTINUE);
        }
    }

}