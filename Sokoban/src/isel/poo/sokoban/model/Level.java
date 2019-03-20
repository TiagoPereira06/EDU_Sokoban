package isel.poo.sokoban.model;


public class Level {
    private int levelNumber,height,width,moves,boxesCount,currentManCellLine,currentManCellCol,targetLine,targetCol;
    private boolean gameOver,clear;
    private Game currentGame;
    private Cell[][] board,initialBoard;
    private Observer updater;

    public Level(int levelNumber, int height, int width) {
        this.levelNumber=levelNumber;
        this.height=height;
        this.width=width;
        moves=0;
        boxesCount=0;
        gameOver=false;
        board = new Cell[height][width];
        initialBoard = new Cell[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean manIsDead() {
        return gameOver;
    }

    public boolean isFinished() {
        return manIsDead();
    }

    public int getMoves() {
        return moves;
    }

    public int getRemainingBoxes() {
        return boxesCount;
    }

    public void moveMan(DIR dir) { //TODO:MOVIMENTOS PLAYER

        if(dir== DIR.UP){
            targetLine=currentManCellLine-1;
            targetCol=currentManCellCol;
        }else if(dir== DIR.DOWN){
            targetLine=currentManCellLine+1;
            targetCol=currentManCellCol;
        }else if (dir== DIR.LEFT){
            targetLine=currentManCellLine;
            targetCol=currentManCellCol-1;
        }else{
            targetLine=currentManCellLine;
            targetCol=currentManCellCol+1;
        }
        move(targetLine,targetCol,dir);
        }



    public void move(int trtLine,int trtCol,DIR dir){
        if((trtLine<0&&trtLine>width)&&trtCol<0&&trtCol>height){
            return;
        }
        Cell man = new ManCell(trtLine, trtCol);
        Cell empty = new EmptyCell();
        Cell obj   = new TargetCell();

        if (targetIsEmptyOrObjective(trtLine, trtCol)) {
            Cell target = (clear) ? empty : obj;
            board[currentManCellLine][currentManCellCol] = target;
            updater.cellReplaced(currentManCellLine, currentManCellCol, target);
            board[trtLine][trtCol] = man;
            updateManCellCord(trtLine, trtCol);
            updater.cellReplaced(currentManCellLine, currentManCellCol, man);
            ++moves;
        }else if(getCell(trtLine,trtCol)instanceof HoleCell) {
            board[currentManCellLine][currentManCellCol] = empty;
            updater.cellReplaced(currentManCellLine, currentManCellCol, new EmptyCell());
            board[trtLine][trtCol] = new DeadManCell();
            updater.cellReplaced(targetLine, targetCol, new DeadManCell());
            gameOver = true;
        }else if(getCell(trtLine,trtCol)instanceof BoxCell){
            boxMotion(trtLine,trtCol,dir);

        }

        clear=false;
    }

    private void boxMotion(int trtLine, int trtCol, DIR dir) {
        if(dir == DIR.UP){
            if(targetIsEmptyOrObjective(trtLine-1,trtCol)){
                BoxCell box= (BoxCell) getCell(trtLine,trtCol);
                if ((clear)) {//CLEAR = FALSE(BOX CHEGOU AO OBJETIVO)
                    box.setComplete(false);
                } else {
                    box.setComplete(true);
                }
                {
                    board[trtLine-1][trtCol] = box;
                    updater.cellReplaced(trtLine-1,trtCol,box);
                    Cell man = new ManCell(trtLine,trtCol);
                    board[trtLine][trtCol]=man;
                    updater.cellReplaced(trtLine,trtCol,man);
                    Cell empty = new EmptyCell();
                    board[currentManCellLine][currentManCellCol]=empty;
                    updater.cellReplaced(currentManCellLine,currentManCellCol,empty);
                    updateManCellCord(trtLine,trtCol);
                }


                }else if(getCell(trtLine-1,trtCol)instanceof HoleCell){
                EmptyCell empty = new EmptyCell();
                board[trtLine-1][trtCol]=empty;
                ManCell man = new ManCell(trtLine,trtCol);
                board[trtLine][trtCol]=man;
                updater.cellReplaced(currentManCellLine,currentManCellCol,new EmptyCell());
                board[currentManCellLine][currentManCellCol]=new EmptyCell();
                updater.cellReplaced(trtLine-1,trtCol,empty);
                updater.cellReplaced(trtLine,trtCol,man);
                updateManCellCord(trtLine,trtCol);
                boxesCount--;
            }
            }
        }


    private boolean targetIsEmptyOrObjective(int line, int col) {
        if (getCell(line, col) instanceof EmptyCell) {
            clear = true;
            return true;
        } else if (getCell(line, col) instanceof TargetCell)
        return true;

        return false;
    }

    private void updateManCellCord(int line, int col) {
        currentManCellLine=line;
        currentManCellCol=col;
    }

    public Cell getCell(int l, int c) {
        return board[l][c];
    }

    public int getNumber() {
        return levelNumber;
    }

    public void setObserver(Observer updater) {
        this.updater=updater;

    }

    public void init(Game game) {
/*        currentGame = game;
        currentGame.setScore(score);
        currentGame.setLevelNumber(levelNumber);*/
    }

    public void reset() {//TODO:RESET GAME
        moves=0;
        board=initialBoard;
        boxesCount=0;
    }

    public void put(int l, int c, char type) {
        switch (type){
            case 'X':
                board[l][c]=new ObstacleCell();
                initialBoard[l][c]=new ObstacleCell();
                break;
            case '*':
                board[l][c]=new TargetCell();
                initialBoard[l][c]=new TargetCell();
                break;
            case 'H':
                board[l][c]=new HoleCell();
                initialBoard[l][c]=new HoleCell();
                break;
            case 'B':
                board[l][c]=new BoxCell(l,c,false);
                initialBoard[l][c]=new BoxCell(l,c,false);
                boxesCount++;
                break;
            case '@':
                board[l][c]=new ManCell(l,c);
                initialBoard[l][c]=new ManCell(l,c);
                currentManCellCol=c;
                currentManCellLine=l;
                break;
            case '.':
                board[l][c]=new BlackCell();
                initialBoard[l][c]=new BlackCell();
                break;

            default: board[l][c]=new EmptyCell();
            initialBoard[l][c]=new EmptyCell();
        }
    }

    public interface Observer {

        void cellUpdated(int l, int c, Cell cell);

        void cellReplaced(int l, int c, Cell cell);
    }
}
