/**
 * Authors: Martin Priessnitz(xpries01), Mikuláš Uřídil(xuridi01)
 * File: ActualizeLoad
 */

package project;

import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;
import project.game.LoadConfigure;
import project.game.PacmanObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static project.game.PacmanObject.lives;


/**
 * trida ActualizeLoad zajistuje loadovani hry
 */
public class ActualizeLoad {
    private CommonField[][] mazeArray;
    private CommonField[][] newStateMazeArray;
    private CommonMaze maze;
    private char[][] mazePlan;
    private int rows;
    private int cols;
    private int count;
    private CommonMazeObject object;
    private int stepsCount;
    private int livesCount;
    public ActualizeLoad(CommonMaze maze, CommonField[][] mazeArray, int rows, int cols){
        this.mazeArray = mazeArray;
        this.maze = maze;
        this.rows = rows;
        this.cols = cols;
        this.count = this.rows;
        this.mazePlan = new char[this.rows][this.cols];
    }

    //methods
    public void actualize(){
        createNewState();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(!mazeArray[i][j].isEmpty() && newStateMazeArray[i][j].isEmpty()){
                    mazeArray[i][j].getObjectLoad();
                }
                else if(mazeArray[i][j].isEmpty() && !newStateMazeArray[i][j].isEmpty()){
                    object = newStateMazeArray[i][j].getObjectLoad();
                    mazeArray[i][j].moveObjectLoad(object);
                    if(object.getClass() == PacmanObject.class){
                        stepsCount = stepsCount + 1;
                        maze.setSteps(stepsCount);
                    }
                }
                if(mazeArray[i][j].IsHereKey() && !mazeArray[i][j].isEmpty()){
                    if(mazeArray[i][j].get().getClass() == PacmanObject.class){
                        mazeArray[i][j].PacmanTookKey();
                    }
                }
                mazeArray[i][j].notifyObservers();
            }
        }
    }

    public void createNewState(){
        loadNextState();
        LoadConfigure nextLoad = new LoadConfigure(mazePlan, rows, cols);
        newStateMazeArray = nextLoad.GetPacmanMaze().getMazeArray();
    }
    public void loadNextState(){
        try {
            File file = new File(("data/save.txt"));

            Scanner scanner = new Scanner(file);

            for(int i = 1; i <= count; i++){
                if(!scanner.hasNextLine()){
                    System.out.print("END OF REPLAY");
                    new Menu();
                    //System.exit(0);
                }
                scanner.nextLine();
            }

            String line = scanner.nextLine();


            for(int i = 0; scanner.hasNextLine() && i < rows; i++) {
                line = scanner.nextLine();
                char []chars = line.toCharArray();
                for (int j = 0; j < cols; j++) {
                    mazePlan[i][j] = chars[j];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        this.count = this.count + this.rows;
    }
}
