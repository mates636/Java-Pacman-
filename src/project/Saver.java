/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: Saver
 */
package project;

import project.common.CommonField;
import project.game.GhostObject;
import project.game.PacmanObject;
import project.game.PathField;
import project.game.WallField;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * trida Saver zajistuje ukladani stavu do souboru
 */
public class Saver {
    private CommonField[][] mazeArray;
    private char[][] charMazePlan;
    private Lock myLock;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    int rows;
    int cols;

    //constructor
    public Saver(CommonField[][] mazeArray, int rows, int cols){
        this.mazeArray = mazeArray;
        this.rows = rows;
        this.cols = cols;
        this.myLock = new ReentrantLock();
        try {
            this.fileWriter = new FileWriter("data/save.txt");
            this.printWriter = new PrintWriter(this.fileWriter);
        } catch (IOException e) {
            System.out.println("ERROR: opening writers.");
            e.printStackTrace();
        }
        charMazePlan = new char[this.rows][this.cols];
        //first save
        this.saveFirstState();
    }

    //methods
    public void saveFirstState(){
        myLock.lock();
        try{
            printWriter.print(rows);
            printWriter.print(" ");
            printWriter.print(cols);
            printWriter.println();
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    if(mazeArray[i][j].getClass() == WallField.class){
                        printWriter.print("X");
                        charMazePlan[i][j] = 'X';
                    }
                    if(mazeArray[i][j].getClass() == PathField.class){
                        if(!mazeArray[i][j].isEmpty()) {
                            if (mazeArray[i][j].get().getClass() == PacmanObject.class) {
                                printWriter.print("S");
                                charMazePlan[i][j] = 'S';
                            }
                            if (mazeArray[i][j].get().getClass() == GhostObject.class) {
                                printWriter.print("G");
                                charMazePlan[i][j] = 'G';
                            }
                        }else if(mazeArray[i][j].IsHereKey()){
                            printWriter.print("K");
                            charMazePlan[i][j] = 'K';
                        }else if(mazeArray[i][j].IsHereEnding()){
                            printWriter.print("T");
                            charMazePlan[i][j] = 'T';
                        }else{
                            printWriter.print(".");
                            charMazePlan[i][j] = '.';
                        }
                    }
                }
                printWriter.println();
            }
        } finally {
           myLock.unlock();
        }
    }

    public void saveState(int rowBefore, int colBefore, int rowAfter, int colAfter){
        myLock.lock();
        try{
            char shift  = charMazePlan[rowBefore][colBefore];
            charMazePlan[rowBefore][colBefore] = '.';
            charMazePlan[rowAfter][colAfter] = shift;

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    if(charMazePlan[i][j] == 'X') {
                        printWriter.print("X");
                    }
                    if(charMazePlan[i][j] == '.') {
                        printWriter.print(".");
                    }
                    if(charMazePlan[i][j] == 'S') {
                        printWriter.print("S");
                    }
                    if(charMazePlan[i][j] == 'G') {
                        printWriter.print("G");
                    }
                    if(charMazePlan[i][j] == 'K') {
                        printWriter.print("K");
                    }
                    if(charMazePlan[i][j] == 'T') {
                        printWriter.print("T");
                    }
                }
                printWriter.println();
            }
        } finally {
            myLock.unlock();
        }
    }

    public void saveConflict(){
        printWriter.print("CONFLICT");
        printWriter.println();
    }

    public void closeSaver(){
        printWriter.close();
    }
}
