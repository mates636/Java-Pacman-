/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: CommonMaze
 */

package project.common;

import java.util.List;

public interface CommonMaze extends Observable{
    CommonField getField(int var1, int var2);
    int numRows();
    int numCols();
    CommonField[][] getMazeArray();
    List<CommonMazeObject> ghostsInMaze();
   int getSteps();
   int getLives();
    void setSteps(int steps);
    void setLives(int lives);
}
