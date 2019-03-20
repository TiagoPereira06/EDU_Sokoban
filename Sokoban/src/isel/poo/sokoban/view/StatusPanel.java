package isel.poo.sokoban.view;

import isel.leic.pg.Console;
import isel.poo.console.FieldView;
import isel.poo.console.View;

public class StatusPanel extends View {
    public static int HEIGHT = 10, WIDTH=8;

    public static int level,moves,boxes;

    private FieldView levelPanel = new FieldView("Level", 1, 0, "---");
    private FieldView movesPanel = new FieldView("Moves", 4, 0, "---");
    private FieldView boxesPanel = new FieldView("Boxes", 7, 0, "---");

    public StatusPanel(int winWidth) {
        super(0,winWidth,HEIGHT,WIDTH, Console.DARK_GRAY);
        WIDTH=winWidth;
        addView(levelPanel);
        addView(movesPanel);
        addView(boxesPanel);
    }

    public void setBoxes(int remainingBoxes) {
        this.boxesPanel.setValue(remainingBoxes);
        boxes=remainingBoxes;
    }

    public void setMoves(int movesUpdate) {
        this.movesPanel.setValue(movesUpdate);
        moves=movesUpdate;
    }

    public void repaint() {

    }

    public void setLevel(int number) {
        this.levelPanel.setValue(number);
        level=number;
    }
}
