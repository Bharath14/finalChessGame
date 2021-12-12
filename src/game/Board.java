package game;

import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Knight;
import game.pieces.Pawn;
import game.pieces.Piece;
import game.pieces.Queen;
import game.pieces.Rook;

import java.util.Arrays;

import static game.Color.*;

public class Board{

    private final Cell [][]cells = new Cell[8][8];
    //0-7 PAWN
    //8,9 ROOK
    //10,11 BISHOP
    //12,13 KNIGHT
    //14 QUEEN
    //15 KING
    private Piece []whitePieces=new Piece[16];
    private Piece []blackPieces=new Piece[16];

    public Board()
    {
        //create white pieces
        this.whitePieces[0]=new Pawn(WHITE, 1,0,new Position(0,1));
        this.whitePieces[1]=new Pawn(WHITE, 1,0,new Position(1,1));
        this.whitePieces[2]=new Pawn(WHITE, 1,0,new Position(2,1));
        this.whitePieces[3]=new Pawn(WHITE, 1,0,new Position(3,1));
        this.whitePieces[4]=new Pawn(WHITE, 1,0,new Position(4,1));
        this.whitePieces[5]=new Pawn(WHITE, 1,0,new Position(5,1));
        this.whitePieces[6]=new Pawn(WHITE, 1,0,new Position(6,1));
        this.whitePieces[7]=new Pawn(WHITE, 1,0,new Position(7,1));
        this.whitePieces[8]=new Rook(WHITE, 1,0,new Position(0,0));
        this.whitePieces[9]=new Rook(WHITE, 1,0,new Position(7,0));
        this.whitePieces[10]=new Knight(WHITE, 1,new Position(1,0));
        this.whitePieces[11]=new Knight(WHITE, 1,new Position(6,0));
        this.whitePieces[12]=new Bishop(WHITE, 1,new Position(2,0));
        this.whitePieces[13]=new Bishop(WHITE, 1,new Position(5,0));
        this.whitePieces[14]=new Queen(WHITE, 1,new Position(3,0));
        this.whitePieces[15]=new King(WHITE, 1,0,new Position(4,0));
        //create black pieces
        this.blackPieces[0]=new Pawn(BLACK, 1,0,new Position(0,6));
        this.blackPieces[1]=new Pawn(BLACK, 1,0,new Position(1,6));
        this.blackPieces[2]=new Pawn(BLACK, 1,0,new Position(2,6));
        this.blackPieces[3]=new Pawn(BLACK, 1,0,new Position(3,6));
        this.blackPieces[4]=new Pawn(BLACK, 1,0,new Position(4,6));
        this.blackPieces[5]=new Pawn(BLACK, 1,0,new Position(5,6));
        this.blackPieces[6]=new Pawn(BLACK, 1,0,new Position(6,6));
        this.blackPieces[7]=new Pawn(BLACK, 1,0,new Position(7,6));
        this.blackPieces[8]=new Rook(BLACK, 1,0,new Position(0,7));
        this.blackPieces[9]=new Rook(BLACK, 1,0,new Position(7,7));
        this.blackPieces[10]=new Knight(BLACK, 1,new Position(1,7));
        this.blackPieces[11]=new Knight(BLACK, 1,new Position(6,7));
        this.blackPieces[12]=new Bishop(BLACK, 1,new Position(2,7));
        this.blackPieces[13]=new Bishop(BLACK, 1,new Position(5,7));
        this.blackPieces[14]=new Queen(BLACK, 1,new Position(3,7));
        this.blackPieces[15]=new King(BLACK, 1,0,new Position(4,7));
        //create game.Cell objects
        for(int x = 0; 8 > x; x++){
            for(int y = 2; 5 >= y; y++){
                this.cells[x][y]=new Cell(new Position(x,y),null);
            }
        }
        for(int i = 0; 16 > i; i++){
            this.cells[this.whitePieces[i].getPosition().getXCoordinate()][this.whitePieces[i].getPosition().getYCoordinate()]=new Cell(this.whitePieces[i].getPosition(), this.whitePieces[i]);
        }
        for(int i = 0; 16 > i; i++){
            this.cells[this.blackPieces[i].getPosition().getXCoordinate()][this.blackPieces[i].getPosition().getYCoordinate()]=new Cell(this.blackPieces[i].getPosition(), this.blackPieces[i]);
        }
    }

    public Board(Board board){
        Cell [][]originalCells=board.getCells();
        for (int x=0;x<8;x++){
            for(int y=0;y<8;y++){
                this.cells[x][y]=new Cell(originalCells[x][y]);
            }
        }
        // below is not valid safe code(we should do deep copy)
        this.whitePieces= board.getWhitePieces();
        this.whitePieces= board.getBlackPieces();
    }

    public Piece[] getWhitePieces(){
        return this.whitePieces.clone();
    }

    public Piece[] getBlackPieces(){
        return this.blackPieces;
    }

    public Cell[][] getCells(){
        return this.cells;
    }

    @Override
    public String toString() {
        return "Board{" +
                "cells=" + Arrays.toString(this.cells) +
                ", whitePieces=" + Arrays.toString(this.whitePieces) +
                ", blackPieces=" + Arrays.toString(this.blackPieces) +
                '}';
    }

    public void toCharacter() {
        for (int y=8;y>=0;y--){
            for(int x=0;x<=8;x++){
                if(y!=0)
                {if(x==0){
                    System.out.print(y-1);
                    System.out.print(" ");
                }
                else
                {
                    System.out.print(this.cells[x-1][y-1].toCharacter());
                    System.out.print(" ");
                }
                }
                else{

                    if(x!=0){
                        System.out.print(x-1);
                        System.out.print(" ");

                    }
                    else{
                        System.out.print(" ");
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}