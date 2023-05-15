/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: CommonMazeObject
 */
package project.common;

import project.Saver;

public interface CommonMazeObject {
    void setMaze(CommonField [][]mazeArray, int row, int col, Saver saver);
    boolean canMove(CommonField.Direction var1);

    boolean move(CommonField.Direction var1);

    boolean isPacman();

    default boolean isHerePoint(){
        return false;
    }

    CommonField getField();
}
