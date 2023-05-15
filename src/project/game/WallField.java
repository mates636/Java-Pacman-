/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: WallField
 */

package project.game;

import project.Saver;
import project.common.AbstractField;
import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;

/**
 * trida WallField reprezentuje zdi v bludisti
 */
public class WallField extends AbstractField {
    private CommonField [][] mazeArray;
    private int row;
    private int col;

    public void SetMaze(CommonField [][]mazeArray, int row, int col, CommonMaze maze, Saver saver){
        this.mazeArray = mazeArray;
        this.row = row;
        this.col = col;
    }
    public void stepCount(){}

    public void livesCount(){}
    public void SetObject(CommonMazeObject object){}
    public void EndingField(){}
    public boolean IsHereEnding(){return false;}
    public void KeyField(){}
    public boolean IsHereKey(){return false;}
    public boolean KeyTaken(){return false;}
    public void PacmanTookKey(){}
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
        return true;
    }

    public CommonMazeObject getObject(){
        return null;
    }
    public void moveObject(CommonMazeObject object){}

    public CommonMazeObject getObjectLoad(){return null;}
    public void moveObjectLoad(CommonMazeObject object){}

    public boolean canMove(){
        return false;
    }




    public boolean isHereGhost(){return false;}
    public boolean isHerePacman(){return false;}
    public CommonMazeObject get(){return null;}
}
