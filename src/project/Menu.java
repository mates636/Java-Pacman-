/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: Menu
 */
package project;

import project.common.CommonMaze;
import project.game.LoadConfigure;
import project.game.MazeConfigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu extends JFrame {
    private JButton startButton;
    private JButton settingsButton;
    private JButton exitButton;
    private int rows ;
    private int cols;
    private char[][] myArray;

    public Menu() {
        setSize(300, 200);
        setTitle("Pacman Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readMap();
                MazeConfigure game = new MazeConfigure(myArray, rows, cols);
                CommonMaze pacmanMaze = game.GetPacmanMaze();
                MazePresenter maze = new MazePresenter(pacmanMaze, 1);
                maze.open();
                dispose();
                //panel.setVisible(false);
            }
        });

        settingsButton = new JButton("Replay game");
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadSave();
                LoadConfigure load = new LoadConfigure(myArray, rows, cols);
                CommonMaze pacmanMaze = load.GetPacmanMaze();
                MazePresenter maze = new MazePresenter(pacmanMaze, 2);
                maze.open();
                dispose();
                //load = new LoadConfigure(myArray, rows, cols);
                //pacmanMaze = load.GetPacmanMaze();
               // maze.actualize(pacmanMaze);
            }
        });

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(startButton);
        panel.add(settingsButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public void readMap(){
        try {

            File file = new File(("data/mapa02.txt"));

            Scanner scanner = new Scanner(file);

            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            this.rows = Integer.parseInt(numbers[0]);
            this.cols = Integer.parseInt(numbers[1]);
            myArray = new char[this.rows][this.cols];

            for(int i = 0; scanner.hasNextLine() && i <= rows; i++) {
                line = scanner.nextLine();
                char []chars = line.toCharArray();
                for (int j = 0; j < cols; j++) {
                    myArray[i][j] = chars[j];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void loadSave(){
        try {

            File file = new File(("data/save.txt"));

            Scanner scanner = new Scanner(file);

            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            this.rows = Integer.parseInt(numbers[0]);
            this.cols = Integer.parseInt(numbers[1]);
            myArray = new char[this.rows][this.cols];

            for(int i = 0; scanner.hasNextLine() && i < rows; i++) {
                line = scanner.nextLine();
                char []chars = line.toCharArray();
                for (int j = 0; j < cols; j++) {
                    myArray[i][j] = chars[j];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}