/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: GhostObject
 */


package project.game;

import project.Saver;
import project.common.CommonField;
import project.common.CommonMazeObject;
import java.util.Random;


/**
 * trida GhostObject reprezentujici ducha v bludisti
 */
public class GhostObject implements CommonMazeObject {
    private CommonField [][]mazeArray;
    private int row;
    private int col;
    private Saver saver;
    static public int lives = 3;

    //constructor
    public GhostObject(){

    }

    /*======Methods======*/
    //from interface

    public void setMaze(CommonField [][]mazeArray, int row, int col, Saver saver){
        this.mazeArray = mazeArray;
        this.saver = saver;
        this.row = row;
        this.col = col;
    }

    public void lives_minus(){
        lives = lives - 1;
        this.livesCount();
    }

    public void livesCount(){
        mazeArray[row][col].livesCount();
    }

    public synchronized void run() {
        CommonField.Direction moveDirection;
        CommonField.Direction oldDirection = null;
        while (true) {
            while(true) {
                int direction = new Random().nextInt(4);
                moveDirection = CommonField.Direction.values()[direction];
                if(canMove(moveDirection) && moveDirection != oldDirection){
                    break;
                }
                oldDirection = moveDirection;
            }
            move(moveDirection);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Kontroluje zda se duch muze pohnout
     * @param var1
     * @return
     */
    public boolean canMove(CommonField.Direction var1){
        if(var1 == CommonField.Direction.L){
            if(mazeArray[row][col].nextField(CommonField.Direction.L) != null && !mazeArray[row][col].nextField(CommonField.Direction.L).isHereGhost()){
                if(mazeArray[row][col].nextField(CommonField.Direction.L).isHereGhost()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
                else{
                    return true;
                }
            }else{
                return false;
            }
        }
        if(var1 == CommonField.Direction.U){
            if(mazeArray[row][col].nextField(CommonField.Direction.U) != null && !mazeArray[row][col].nextField(CommonField.Direction.U).isHereGhost()){
                if(mazeArray[row][col].nextField(CommonField.Direction.U).isHereGhost()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
                else{
                    return true;
                }
            }else{
                return false;
            }
        }
        if(var1 == CommonField.Direction.R){
            if(mazeArray[row][col].nextField(CommonField.Direction.R) != null){
                if(mazeArray[row][col].nextField(CommonField.Direction.R).isHereGhost()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
                else{
                    return true;
                }
            }else{
                return false;
            }
        }
        if(var1 == CommonField.Direction.D){
            if(mazeArray[row][col].nextField(CommonField.Direction.D) != null){
                if(mazeArray[row][col].nextField(CommonField.Direction.D).isHereGhost()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
                else{
                    return true;
                }
            }else{
                return false;
            }
        }
        System.out.println("ERROR:canMove");
        return false;
    }

    /**
     * Duch se pohne na dalsi pole
     * @param var1
     * @return
     */
    public boolean move(CommonField.Direction var1){
        if(var1 == CommonField.Direction.L){
            if(this.canMove(CommonField.Direction.L)){
                if(mazeArray[row][col].nextField(CommonField.Direction.L).isHerePacman()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.L).moveObject(shift);
                    this.col = this.col - 1;
                    saver.saveState(row, col + 1, row, col);
                    return true;
                }
            }
        }
        if(var1 == CommonField.Direction.U){
            if(this.canMove(CommonField.Direction.U)){
                if(mazeArray[row][col].nextField(CommonField.Direction.U).isHerePacman()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.U).moveObject(shift);
                    this.row = this.row -1;
                    saver.saveState(row + 1, col, row, col);
                    return true;
                }
            }
        }
        if(var1 == CommonField.Direction.R){
            if(this.canMove(CommonField.Direction.R)){
                if(mazeArray[row][col].nextField(CommonField.Direction.R).isHerePacman()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.R).moveObject(shift);
                    this.col = this.col + 1;
                    saver.saveState(row, col - 1, row, col);
                    return true;
                }
            }
        }
        if(var1 == CommonField.Direction.D){
            if(this.canMove(CommonField.Direction.D)){
                if(mazeArray[row][col].nextField(CommonField.Direction.D).isHerePacman()){
                    lives_minus();
                    if (lives == 0){
                        System.out.println("GAME OVER");
                        saver.closeSaver();
                        System.exit(0);
                    }
                }else{
                    CommonMazeObject shift = mazeArray[row][col].getObject();
                    mazeArray[row][col].nextField(CommonField.Direction.D).moveObject(shift);
                    this.row = this.row + 1;
                    saver.saveState(row - 1, col, row, col);
                    return true;
                }
            }
        }
        System.out.println("ERROR:move");
        return false;
    }

    /**
     * vrati true pokud je na policku Pacman
     * @return
     */
    public boolean isPacman(){
        return false;
    }

    /**
     * vraci pole
     * @return
     */
    public CommonField getField(){
        return mazeArray[row][col];
    }
}
