/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: PacmanObject
 */

package project.game;

import project.PacmanEndingScreen;
import project.Saver;
import project.common.CommonField;
import project.common.CommonMazeObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * trida PacmanObject reprezentuje Pacmana v bludisti
 */
public class PacmanObject implements CommonMazeObject{
    private CommonField [][]mazeArray;
    private int row;
    private int col;
    static public int lives = 3;
    private boolean key = false;
    private Saver saver;


    //constructor
    public PacmanObject(){

    }

    /*======Methods======*/
    //from interface
    /**
     * metoda, ktera zaznamenava kroky Pacmana
     */
    public void do_step(){
        mazeArray[row][col].stepCount();
    }
    /**
     * metoda, ktera zaznamenava zivoty pacmana
     */
    public void lives_minus(){
        lives = lives - 1;
        this.livesCount();
    }
    public void livesCount(){
        mazeArray[row][col].livesCount();
    }

    public int getLives(){
        return lives;
    }
    public boolean Dead(){
        return lives  <= 0;
    }
    public void setMaze(CommonField [][]mazeArray, int row, int col, Saver saver){
        this.mazeArray = mazeArray;
        this.row = row;
        this.col = col;
        this.saver = saver;
    }

    /**
     * metoda, ktera vraci true pokud se Pacman muze pohnout v danem smeru
     * @param var1
     * @return
     */
    public boolean canMove(CommonField.Direction var1){
        if(var1 == CommonField.Direction.L){
            if(mazeArray[row][col].nextField(CommonField.Direction.L) != null){
                return true;
            }else{
                return false;
            }
        }
        if(var1 == CommonField.Direction.U){
            if(mazeArray[row][col].nextField(CommonField.Direction.U) != null){
                return true;
            }else{
                return false;
            }
        }
        if(var1 == CommonField.Direction.R){
            if(mazeArray[row][col].nextField(CommonField.Direction.R) != null){
                return true;
            }else{
                return false;
            }
        }
        if(var1 == CommonField.Direction.D){
            if(mazeArray[row][col].nextField(CommonField.Direction.D) != null){
                return true;
            }else{
                return false;
            }
        }
        System.out.println("ERROR:canMove");
        return false;
    }

    /**
     * metoda zajistujici pohyb Pacmana po bludisti
     * @param var1
     * @return
     */
    public boolean move(CommonField.Direction var1){
        if(var1 == CommonField.Direction.L){
            if(this.canMove(CommonField.Direction.L)){
                do_step();
                if(mazeArray[row][col].nextField(CommonField.Direction.L).isHereGhost()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();

                       // new PacmanEndingScreen(lives);
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.L).moveObject(shift);
                    this.col = this.col - 1;
                    this.isKeyField();
                    this.isEndingField();
                    saver.saveState(row, col + 1, row, col);
                    return true;
                }
            }
        }
        if(var1 == CommonField.Direction.U){
            if(this.canMove(CommonField.Direction.U)){
                do_step();
                if(mazeArray[row][col].nextField(CommonField.Direction.U).isHereGhost()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                      //  new PacmanEndingScreen(lives);
                        System.exit(0);
                    }

                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.U).moveObject(shift);
                    this.row = this.row -1;
                    this.isKeyField();
                    this.isEndingField();
                    saver.saveState(row + 1, col, row, col);
                    return true;
                }
            }
        }
        if(var1 == CommonField.Direction.R){
            if(this.canMove(CommonField.Direction.R)){
                do_step();
                if(mazeArray[row][col].nextField(CommonField.Direction.R).isHereGhost()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                     //   new PacmanEndingScreen(lives);
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.R).moveObject(shift);
                    this.col = this.col + 1;
                    this.isKeyField();
                    this.isEndingField();
                    saver.saveState(row, col - 1, row, col);
                    return true;
                }
            }
        }
        if(var1 == CommonField.Direction.D){
            if(this.canMove(CommonField.Direction.D)){
                do_step();
                if(mazeArray[row][col].nextField(CommonField.Direction.D).isHereGhost()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                      //  new PacmanEndingScreen(lives);
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.D).moveObject(shift);
                    this.row = this.row + 1;
                    this.isKeyField();
                    this.isEndingField();
                    saver.saveState(row - 1, col, row, col);

                    return true;
                }
            }
        }
        //System.out.println("ERROR:move");
        return false;
    }

    /**
     * metoda, ktera vraci true pokud je policko s klicem
     */
    public void isKeyField(){
        if(mazeArray[row][col].IsHereKey()){
            this.key = true;
            mazeArray[row][col].PacmanTookKey();
        }
    }

    /**
     * metoda, ktera vraci true pokud je policko, kde muze pacman ukoncit hru. Jen pokud uz nasel klic
     */
    public void isEndingField(){
        if(mazeArray[row][col].IsHereEnding()){
            if(key){

                System.out.println("WIN");
                saver.closeSaver();
                new PacmanEndingScreen(lives);
                System.exit(0);
            }
        }
    }

    public boolean isPacman(){
        return true;
    }

    public CommonField getField(){
        return mazeArray[row][col];
    }

    //other methods

}
