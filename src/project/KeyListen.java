/**
 * Authors: Martin Priessnitz(xpries01), Mikuláš Uřídil(xuridi01)
 * File: KeyListen
 */

package project;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import project.common.CommonField;
import project.common.CommonMazeObject;
import project.game.PacmanObject;

/**
 * trida KeyListen zajistuje pohyb po stisknuti klavesy na klavesnici
 */
public class KeyListen implements KeyListener{
    private CommonMazeObject pacman;

    public KeyListen(CommonMazeObject pacmanObject){
        this.pacman = pacmanObject;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                pacman.move(CommonField.Direction.U);
                break;
            case KeyEvent.VK_DOWN:
                pacman.move(CommonField.Direction.D);
                break;
            case KeyEvent.VK_LEFT:
                pacman.move(CommonField.Direction.L);
                break;
            case KeyEvent.VK_RIGHT:
                pacman.move(CommonField.Direction.R);
                break;
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException l) {
            l.printStackTrace();
        }
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
