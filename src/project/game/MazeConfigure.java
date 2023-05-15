/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: MazeConfigure
 */

package project.game;

import project.Saver;
import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;

/**
 * trida MazeConfigure reprezentuje sestaveni bludiste
 */
public class MazeConfigure {
    private final char [][]gamePlanArray;
    private final int gameRows;
    private final int gameCols;
    private CommonField [][]mazeArray;
    private CommonMaze pacmanMaze;
    private Saver saver;
    public MazeConfigure(char [][]gamePlanArray, int rows, int cols){
        this.gamePlanArray = gamePlanArray;
        this.gameRows = rows;
        this.gameCols = cols;
        this.Configure();

    }
    public void Configure(){
        this.CreateFields();
        this.pacmanMaze = new Maze(mazeArray, gameRows + 2, gameCols + 2);
        this.saver = new Saver(mazeArray, gameRows + 2, gameCols + 2);
        this.SetFields();
    }

    public CommonMaze GetPacmanMaze(){
        return this.pacmanMaze;
    }

    /**
     * metoda, ktera vytvari pole podle planku
     */
    public void CreateFields(){
        //creating array + edges
        mazeArray = new CommonField[gameRows + 2][gameCols + 2];

        //Top edge - walls
        for(int i = 0; i <= gameCols + 1; i++){
            mazeArray[0][i] = new WallField();
        }

        //game rows + 2 side edges
        for(int i = 1; i <= gameRows; i++){
            mazeArray[i][0] = new WallField();
            for(int j = 1; j <= gameCols; j++){
                if(gamePlanArray[i - 1][j - 1] == 'X'){
                    mazeArray[i][j] = new WallField();
                }
                if(gamePlanArray[i - 1][j - 1] == '.'){
                    mazeArray[i][j] = new PathField();
                    //CommonMazeObject point = new PointObject();
                    //mazeArray[i][j].SetObject(point);
                }
                if(gamePlanArray[i - 1][j - 1] == 'T'){
                    mazeArray[i][j] = new PathField();
                    mazeArray[i][j].EndingField();
                }
                if(gamePlanArray[i - 1][j - 1] == 'K'){
                    mazeArray[i][j] = new PathField();
                    mazeArray[i][j].KeyField();
                }
                if(gamePlanArray[i - 1][j - 1] == 'S'){
                    mazeArray[i][j] = new PathField();
                    CommonMazeObject pacman = new PacmanObject();
                    mazeArray[i][j].SetObject(pacman);
                }
                if(gamePlanArray[i - 1][j - 1] == 'G'){
                    mazeArray[i][j] = new PathField();
                    CommonMazeObject ghost = new GhostObject();
                    mazeArray[i][j].SetObject(ghost);
                }
            }
            mazeArray[i][gameCols + 1] = new WallField();
        }

        //Bottom edge - walls
        for(int i = 0; i <= gameCols + 1; i++){
            mazeArray[gameRows + 1][i] = new WallField();
        }
    }

    //setting complete map for every field
    public void SetFields(){
        for(int i = 0; i < gameRows + 2; i++){
            for(int j = 0; j < gameCols + 2; j++){
                mazeArray[i][j].SetMaze(mazeArray, i, j, pacmanMaze, saver);
            }
        }
    }
}
