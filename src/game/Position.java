package game;

public class Position{

    private int x;
    private int y;

    public Position(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getXCoordinate(){
        return this.x;
    }

    public int getYCoordinate(){
        return this.y;
    }

    public void setXCoordinate(int x){
        this.x=x;
    }

    public void setYCoordinate(int y){
        this.y=y;
    }

    public String toCharacter(){
        return "("+this.x + ", "+ this.y + ")";
    }
//Other coordinates like (A,1).,(B,6)....Pending

}