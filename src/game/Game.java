package game;
import game.pieces.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import game.ai.Player;

public class Game{

    private Board board;
    private Player currentTurn;
    //players[0]-> white player, players[1]-> black player
    private Player []players=new Player[2];
    private GameStatus gameStatus;
    private List<String> history;
    private List<Pair<Cell, Cell>> moveHistory;

    public Game(Player p1, Player p2)
    {
        this.players[0]=p1;
        this.players[1]=p2;
        this.currentTurn =p1;
        this.board =new Board();
        this.gameStatus =GameStatus.CONTINUE;//DEFAULT
        this.history = new ArrayList<>();
        this.moveHistory = new ArrayList<Pair<Cell, Cell>>();
    }
    //if u need a deep copy of a game then first convert it into fen & then simply do: game.Game deepcopy = new game.Game(fen,p1,p2);
    public Game(String fen,Player p1, Player p2){

    }

    public Board getBoard(){
        return this.board;
    }

    public void setBoard(Board board){
        this.board=board;
    }

    public Player getCurrentTurn(){
        return this.currentTurn;
    }

    public void setCurrentTurn(Player player){
        this.currentTurn =player;
    }

    public void changeCurrentTurn(){
        if(this.currentTurn.getColor()== Color.WHITE){
            this.currentTurn =this.players[1];
        }
        else{
            this.currentTurn =this.players[0];
        }
    }

    public List<String> getHistory(){
        return Collections.unmodifiableList(this.history);
    }

    /*public void storeToHistory(){
        this.history.add(FENUtils.getFen(this));
    }*/

    public GameStatus getGameStatus(){
        return this.gameStatus;
    }

    public void setGameStatus(GameStatus status){
        this.gameStatus =status;
    }

    // checks if source selected by player is valid or not (Valid :   game.Cell should have a piece & color of piece should be currentPlayer's color)
    public boolean validSourceSelection(Cell source){
        Piece sourcePiece=source.getPiece();
        return (sourcePiece != null && sourcePiece.getColor() == this.currentTurn.getColor());
    }
    // checks if currentPlayer's king will be in danger after the move.
    boolean willKingBeInDanger(Cell source, Cell destination){
        Cell[][] cells = this.board.getCells();
        Cell[][] newCells = new Cell[8][8];
        int sourceX = source.getPosition().getXCoordinate();
        int sourceY = source.getPosition().getYCoordinate();
        int destinationX = destination.getPosition().getXCoordinate();
        int destinationY = destination.getPosition().getYCoordinate();
        for (int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                newCells[i][j] = new Cell(cells[i][j]);
            }
        }
        this.makeMove(newCells[sourceX][sourceY],newCells[destinationX][destinationY]);

        return GameUtils.isInCheck(newCells, this.currentTurn.getColor());
    }
    // should be called only if the move is an actual valid move
    public void makeMove(Cell source,Cell destination){
        // changes done to this function parameters reflect in the board attribute also...check it

        Piece sourcePiece = source.getPiece();
        Piece destinationPiece=destination.getPiece();
        if(destinationPiece!=null)
        {
            destinationPiece.setLife(0);
            destination.removePiece();
        }
        destination.setPiece(sourcePiece);
        sourcePiece.setPosition(destination.getPosition());
        if(sourcePiece.getType() == PieceType.PAWN) {
            ((Pawn) sourcePiece).setIsMoved(1);
            Position startPosition = source.getPosition();
            int x = startPosition.getXCoordinate();
            int y = startPosition.getYCoordinate();
            if((destination.getPosition().getYCoordinate() == y+2 && sourcePiece.getColor() == Color.WHITE) ||
                    ((destination.getPosition().getYCoordinate() == y-2 && sourcePiece.getColor() == Color.BLACK)))
            {
                //System.out.println("jump");
                ((Pawn)sourcePiece).setRecentMoveWasJump(true);
            }
            else
            {
                ((Pawn)sourcePiece).setRecentMoveWasJump(false);
            }
            if(((Pawn)sourcePiece).getEmpasantmove() == true)
            {
                if((destination.getPosition().getXCoordinate() == x+1 || destination.getPosition().getXCoordinate() == x-1) && destinationPiece == null)
                {
                    Cell empasantcell = ((Pawn)sourcePiece).getEmpasantcell();
                    Piece empasantpiece = empasantcell.getPiece();
                    empasantpiece.setLife(0);
                    empasantcell.removePiece();
                    ((Pawn)sourcePiece).setEmpasantmove(false);
                }
                else
                {
                    ((Pawn)sourcePiece).setEmpasantmove(false);
                }
            }
        }
        else if(sourcePiece.getType() == PieceType.ROOK) {
            ((Rook) sourcePiece).setIsMoved(1);
        }
        else if(sourcePiece.getType() == PieceType.KING) {
            ((King) sourcePiece).setIsMoved(1);
        }
        source.removePiece();
        //movehistory, store history, change turn ,check/update status
    }

    public void playTurn(Cell source, Cell destination){
        //this.storeToHistory();
        this.makeMove(source,destination);
        //we need to send deep copies of cells so that we can check those move (i.e cells) later even after board changes
        this.addToMoveHistory(new Pair<>(new Cell(source),new Cell(destination)));
        this.changeCurrentTurn();
        GameUtils.checkStatus(this);

    }

    public List<Pair<Cell, Cell>> getMoveHistory() {
        return Collections.unmodifiableList(this.moveHistory);
    }

    public  void addToMoveHistory(Pair<Cell,Cell> move){this.moveHistory.add(move);}
    //storeToHistory -> makeMove -> checkStatus -> changeTurn -> REPEAT
}