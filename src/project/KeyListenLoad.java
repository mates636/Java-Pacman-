/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: KeyListenLoad
 */
package project;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenLoad implements KeyListener{
    ActualizeLoad actualizeLoad;
    public KeyListenLoad(ActualizeLoad actualizeLoad){
        this.actualizeLoad = actualizeLoad;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_LEFT:

                break;
            case KeyEvent.VK_RIGHT:
                actualizeLoad.actualize();
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