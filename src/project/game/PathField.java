/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: PathField
 */

package project.game;

import project.Saver;
import project.common.AbstractField;
import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;

/**
 * trida PathField reprezentuje cestu v bludisti
 */
public class PathField extends AbstractField {
    private boolean endingField = false;
    private boolean keyField = false;
    private boolean pacmanTookKey = false;
    private CommonMaze maze;
    private CommonField [][] mazeArray;
    private int row;
    private int col;
    private CommonMazeObject object = null;
    public static int Steps = 0;
    public static int Lives = 3;
    private Saver saver;

    //constructor
    public PathField(){
    }

    /**
     * metoda, ktera zjisti pocet kroku pacmana
     */
    public void stepCount(){
        Steps = Steps + 1;
        maze.setSteps(Steps);
    }

    /**
     * metoda, ktera zjisti pocet zivotu pacmana
     */
    public void livesCount(){
        Lives = Lives - 1;
        maze.setLives(Lives);
    }
    public void SetMaze(CommonField [][]mazeArray, int row, int col, CommonMaze maze, Saver saver){
        this.mazeArray = mazeArray;
        this.row = row;
        this.col = col;
        this.saver = saver;
        if(this.object != null) {
            this.object.setMaze(this.mazeArray, this.row, this.col, this.saver);
        }
        this.maze = maze;
    }
    public void SetObject(CommonMazeObject object){
        this.object = object;
    }
    public void EndingField(){
        this.endingField = true;
    }
    public boolean IsHereEnding(){return endingField;}
    public void KeyField(){this.keyField = true;}
    public boolean IsHereKey(){return keyField;}
    public boolean KeyTaken(){
        if(this.pacmanTookKey){
            return true;
        }
        return false;
    }
    public void PacmanTookKey(){
        this.pacmanTookKey = true;
        notifyObservers();
    }

    public CommonField nextField(Direction dir){
        if(dir == Direction.L){
            if(mazeArray[row][col - 1].getClass() == PathField.class){
                return mazeArray[row][col - 1];
            }
        }
        if(dir == Direction.U){
            if(mazeArray[row - 1][col].getClass() == PathField.class){
                return mazeArray[row - 1][col];
            }
        }
        if(dir == Direction.R){
            if(mazeArray[row][col + 1].getClass() == PathField.class){
                return mazeArray[row][col + 1];
            }
        }
        if(dir == Direction.D){
            if(mazeArray[row + 1][col].getClass() == PathField.class){
                return mazeArray[row + 1][col];
            }
        }
        return null;
    }

    public boolean isEmpty(){
        if(object != null){
            return false;
        }
        return true;
    }

    /**
     * metoda nam vrati objekt v bludisti
     * @return
     */
    public CommonMazeObject getObject(){
        CommonMazeObject obj = this.object;
        this.object = null;
        notifyObservers();
        return obj;
    }

    /**
     * metoda, ktera pohybuje s objekty v bludisti
     * @param object
     */
    public void moveObject(CommonMazeObject object){
        this.object = object;
        notifyObservers();
    }
    public CommonMazeObject getObjectLoad(){
        CommonMazeObject obj = this.object;
        this.object = null;
        return obj;
    }
    public void moveObjectLoad(CommonMazeObject object){
        this.object = object;
    }

    public boolean canMove(){

        return true;
    }

    /**
     * metoda, ktera vraci true pokud je na policku duch
     * @return
     */
    public boolean isHereGhost(){
        if(object != null) {
            if (object.getClass() == GhostObject.class) {
                return true;
            }
        }
        return false;
    }

    /**
     * metoda, ktera vrati true pokud je na policku Pacman
     * @return
     */
    public boolean isHerePacman(){
        if(object != null) {
            if (object.getClass() == PacmanObject.class) {
                return true;
            }
        }
        return false;
    }

    public CommonMazeObject get(){
        return this.object;
    }
    //other methods

}
