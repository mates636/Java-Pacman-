/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: Maze
 */

package project.game;

import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;
import project.common.Observable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * trida reprezxentujici planek hry
 */
public class Maze implements CommonMaze, Observable {
    private int rows;
    private int cols;
    private int counterRow;
    private int counterCol;
    private CommonField [][]mazeArray;
    private List<CommonMazeObject> ghosts;
    public static int Steps = 0;
    public static int Lives = 3;

    private final Set<Observer> observers = new HashSet<>();

    //constructor
    public Maze(CommonField [][]mazeArray, int rows, int cols){
        this.mazeArray = mazeArray;
        this.cols = cols;
        this.rows = rows;

        this.ghosts = new ArrayList<>();

        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                if(this.mazeArray[i][j].get() != null) {
                    if (this.mazeArray[i][j].get().getClass() == GhostObject.class) {
                        this.ghosts.add(this.mazeArray[i][j].get());
                        this.counterRow = i;
                        this.counterCol = j;
                    }
                }
            }
        }
    }

    //methods
    public CommonField getField(int var1, int var2){
        return mazeArray[var1][var2];
    }
    public int numRows(){return this.rows;}
    public int numCols(){return this.cols;}
    public CommonField[][] getMazeArray(){
        return mazeArray;
    }
    public List<CommonMazeObject> ghostsInMaze(){
        return ghosts;
    }

    public int getSteps(){
        return Steps;
    }
    public int getLives(){
        return Lives;
    }
    public void setSteps(int steps){
        this.Steps = steps;
        notifyObservers();
    }

    public void setLives(int lives){
        this.Lives = lives;
        notifyObservers();
    }

    //OBSERVABLE
    public void addObserver(Observable.Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observable.Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers() {
        this.observers.forEach((o) -> o.update(this));
    }
}
