package project;

import project.common.CommonMaze;
import project.game.MazeConfigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacmanEndingScreen extends JFrame {
    private int score;

    public PacmanEndingScreen(int score) {
        this.score = score;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pacman - End of Game");

        JPanel panel = new JPanel(new BorderLayout());
        JLabel scoreLabel = new JLabel("Lives remaining: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(scoreLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Back to menu");
        JButton exitButton = new JButton("Exit");

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}