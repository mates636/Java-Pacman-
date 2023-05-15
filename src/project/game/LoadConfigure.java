/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: LoadConfigure
 */

package project.game;

import project.Saver;
import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;

public class LoadConfigure {
    private final char [][]gamePlanArray;
    private final int gameRows;
    private final int gameCols;
    private CommonField [][]mazeArray;
    private CommonMaze pacmanMaze;
    private Saver saver = null;
    public LoadConfigure(char [][]gamePlanArray, int rows, int cols){
        this.gamePlanArray = gamePlanArray;
        this.gameRows = rows;
        this.gameCols = cols;
        this.Configure();

    }
    public void Configure(){
        this.CreateFields();
        this.pacmanMaze = new Maze(mazeArray, gameRows, gameCols);
        this.SetFields();
    }

    public CommonMaze GetPacmanMaze(){
        return this.pacmanMaze;
    }

    //creating fields according to the map
    public void CreateFields(){
        //creating array + edges
        mazeArray = new CommonField[gameRows][gameCols];


        //game rows
        for(int i = 0; i < gameRows; i++){
            for(int j = 0; j < gameCols; j++) {
                if (gamePlanArray[i][j] == 'X') {
                    mazeArray[i][j] = new WallField();
                }
                if (gamePlanArray[i][j] == '.') {
                    mazeArray[i][j] = new PathField();
                }
                if (gamePlanArray[i][j] == 'T') {
                    mazeArray[i][j] = new PathField();
                    mazeArray[i][j].EndingField();
                }
                if (gamePlanArray[i][j] == 'K') {
                    mazeArray[i][j] = new PathField();
                    mazeArray[i][j].KeyField();
                }
                if (gamePlanArray[i][j] == 'S') {
                    mazeArray[i][j] = new PathField();
                    CommonMazeObject pacman = new PacmanObject();
                    mazeArray[i][j].SetObject(pacman);
                }
                if (gamePlanArray[i][j] == 'G') {
                    mazeArray[i][j] = new PathField();
                    CommonMazeObject ghost = new GhostObject();
                    mazeArray[i][j].SetObject(ghost);
                }
            }
        }
    }

    //setting complete map for every field
    public void SetFields(){
        for(int i = 0; i < gameRows; i++){
            for(int j = 0; j < gameCols; j++){
                mazeArray[i][j].SetMaze(mazeArray, i, j, pacmanMaze, saver);
            }
        }
    }
}
