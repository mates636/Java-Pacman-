/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: MazePresenter
 */
package project;

import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;
import project.game.GhostObject;
import project.game.PacmanObject;
import project.view.DistanceView;
import project.view.FieldView;
import project.view.LivesView;

import java.awt.*;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javax.swing.*;


public class MazePresenter {
    private final CommonMaze maze;
    private int rows;
    private int cols;
    private CommonMazeObject pac;
    private List<Thread> threadList = new ArrayList<>();
    private CommonField counterField;
    private int mode;
    private KeyListener keyListener;
    private JPanel content;

    /**
     * trida MazePresenter zajistuje zobrazeni okna bludiste
     * @param maze
     * @param mode
     */
    public MazePresenter(CommonMaze maze, int mode) {
        this.maze = maze;
        this.mode = mode;
    }
    public void open() {
        if(mode == 1) {
            try {
                SwingUtilities.invokeLater(this::initializeInterface);
            } catch (Exception e) {
                Logger.getLogger(MazePresenter.class.getName()).log(Level.SEVERE, null, e);
            }
        } else if(mode == 2){
            try {
                SwingUtilities.invokeLater(this::initializeInterfaceLoad);
            } catch (Exception e) {
                Logger.getLogger(MazePresenter.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }



    private void initializeInterface() {
        JFrame frame = new JFrame("Pacman Game");
        frame.setDefaultCloseOperation(3);
        frame.setSize(550, 600);
        frame.setPreferredSize(new Dimension(550, 600));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        this.rows = this.maze.numRows();
        this.cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows + 1, cols + 1);
        content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j), this);
                if(this.maze.getField(i,j).get() != null){
                    if(this.maze.getField(i,j).get().getClass() == PacmanObject.class){
                        pac = maze.getField(i,j).get();
                        counterField = maze.getField(i,j);
                    }
                }
                content.add(field);
            }
        }

        DistanceView distanceField = new DistanceView(maze);
        content.add(distanceField);

        LivesView livesField = new LivesView(maze);
        content.add(livesField);

        for (CommonMazeObject object : maze.ghostsInMaze()) {
            threadList.add( new Thread(() -> {
                ((GhostObject)object).run();
            }));
        }
        for (Thread object : threadList){
            object.start();
        }

        keyListener = new KeyListen(pac);

        frame.getContentPane().add(content, "Center");
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(keyListener);
    }

    private void initializeInterfaceLoad() {
        JFrame frame = new JFrame("Pacman Replay");
        frame.setDefaultCloseOperation(3);
        frame.setSize(550, 600);
        frame.setPreferredSize(new Dimension(550, 600));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        this.rows = this.maze.numRows();
        this.cols = this.maze.numCols();
        GridLayout layout = new GridLayout(rows + 1, cols + 1);
        content = new JPanel(layout);

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                FieldView field = new FieldView(this.maze.getField(i, j), this);
                if(this.maze.getField(i,j).get() != null){
                    if(this.maze.getField(i,j).get().getClass() == PacmanObject.class){
                        pac = maze.getField(i,j).get();
                        counterField = maze.getField(i,j);
                    }
                }
                content.add(field);
            }
        }

        DistanceView distanceField = new DistanceView(maze);
        content.add(distanceField);

        ActualizeLoad actualizeLoad = new ActualizeLoad(maze, maze.getMazeArray(), rows, cols);
        keyListener = new KeyListenLoad(actualizeLoad);

        frame.getContentPane().add(content, "Center");
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(keyListener);
    }
}
