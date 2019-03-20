package isel.poo.sokoban.view;

import isel.leic.pg.Console;

public class DeadManTile extends CellTile {
    public DeadManTile() {
        super(Console.GRAY, Console.BLACK, '@');
    }
}
