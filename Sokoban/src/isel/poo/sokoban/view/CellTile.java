package isel.poo.sokoban.view;

import isel.leic.pg.Console;
import isel.poo.console.tile.Tile;
import isel.poo.sokoban.model.*;

public class CellTile extends Tile {
    private int back;
    private int fore;
    private char character;
    public static final int SIDE = 2;

    public CellTile(int backgroundColor,int foregroundColor, char c) {
        this.back =backgroundColor;
        this.fore =foregroundColor;
        this.character=c;
    }

    @Override
    public void paint() {
        super.paint();
        Console.setBackground(this.back);
        Console.setForeground(this.fore);
        print(0,0,this.character);
        print(0,1,this.character);
        print(1,0,this.character);
        print(1,1,this.character);
    }

    public static Tile tileOf(Cell cell) {

        if(cell instanceof TargetCell)
            return new TargetCellTile();

        if(cell instanceof HoleCell)
            return new HoleCellTile();

        if(cell instanceof BoxCell) {
            Tile tile = (((BoxCell) cell).isComplete()) ? new completeBoxCellTile() : new BoxCellTile();
            return tile;
        }
        if(cell instanceof ManCell)
            return new ManCellTile();

        if(cell instanceof ObstacleCell)
            return new ObstacleCellTile();

        if(cell instanceof BlackCell)
            return new BlackCellTile();

        if(cell instanceof DeadManCell)
            return new DeadManTile();

        return new EmptyCellTile();
    }
}
