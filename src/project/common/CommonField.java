/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: CommonField
 */

package project.common;

import project.Saver;

public interface CommonField extends Observable{
    void stepCount();
    void livesCount();
    void SetMaze(CommonField [][]mazeArray, int row, int col, CommonMaze maze, Saver saver);
    void SetObject(CommonMazeObject object);
    void EndingField();
    boolean IsHereEnding();
    void KeyField();
    boolean IsHereKey();
    boolean KeyTaken();
    void PacmanTookKey();
    CommonField nextField(Direction dir);
    boolean isEmpty();
    CommonMazeObject getObject();
    void moveObject(CommonMazeObject object);
    CommonMazeObject getObjectLoad();
    void moveObjectLoad(CommonMazeObject object);
    boolean canMove();

    boolean isHereGhost();
    boolean isHerePacman();
    CommonMazeObject get();

    public static enum Direction {
        L,
        U,
        R,
        D;
    }
}
