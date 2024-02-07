package Entities;

public class Jump {
    private int startPoint;
    private int endPoint;

    public Jump(int startPoint, int endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public int getStartPoint(){
        return startPoint;
    }

    public int getEndPoint(){
        return endPoint;
    }
}
