package isel.poo.sokoban.model;

public class BoxCell extends Cell {
    private int posX,posY;
    private boolean complete;


    public BoxCell(int posX, int posY,boolean complete){
        super();
        this.posX=posX;
        this.posY=posY;
        this.complete=false;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
