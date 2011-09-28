package gerow.cs200.hw2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * KeyListener for the pause menu.
 * @author mike
 *
 */
public class PauseMenuController implements KeyListener, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3704198651022286101L;

    public void keyPressed(KeyEvent arg0) {
	//System.out.println("Key detected");
	switch (arg0.getKeyCode()) {
	case KeyEvent.VK_ESCAPE: GameInformation.resume();
	}
	
    }

    public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
    }

    public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
    }

}
