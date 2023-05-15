/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: LivesView
 */

package project.view;

import project.common.CommonField;
import project.common.CommonMaze;
import project.common.CommonMazeObject;
import project.common.Observable;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class LivesView extends JPanel implements Observable.Observer {
    private CommonMaze maze;
    private int lives;

    public LivesView(CommonMaze maze) {
        this.setBackground(Color.white);
        this.maze = maze;
        this.maze.addObserver(this);
        this.lives = this.maze.getLives();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString("Lives: ", 0, 20);
        Graphics2D g2d2 = (Graphics2D) g;
        g2d2.setColor(Color.black);
        g2d2.setFont(new Font("Arial", Font.BOLD, 12));
        g2d2.drawString("" + lives, 0, 40);
    }

    public final void update(Observable field) {
        this.lives = this.maze.getLives();
        repaint();
    }
}