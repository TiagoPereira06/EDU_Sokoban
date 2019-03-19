package isel.poo.sokoban.model;


import isel.poo.sokoban.ctrl.Sokoban;

public class Level {
    private int levelNumber,height,width;
    private Observer updater;

    public Level(int levelNumber, int height, int width) {
    this.levelNumber=levelNumber;
    this.height=height;
    this.width=width;
    }

    public int getHeight() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public boolean manIsDead() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }

    public int getMoves() {
        return 0;
    }

    public int getRemainingBoxes() {
        return 0;
    }

    public void moveMan(Dir dir) {

    }

    public Cell getCell(int l, int c) {
        return null;
    }

    public int getNumber() {
        return 0;
    }

    public void setObserver(Observer updater) {
        this.updater=updater;

    }

    public void init(Game game) {

    }

    public void reset() {
        
    }

    public void put(int l, int c, char type) {
    }

    public interface Observer {

        void cellUpdated(int l, int c, Cell cell);

        void cellRepalced(int l, int c, Cell cell);
    }
}
