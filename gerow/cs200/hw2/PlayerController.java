package gerow.cs200.hw2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * KeyListener for player.  Uses key-pad u d l r for movement, shift to slow down, and x to fire.
 * @author mike
 *
 */
public class PlayerController implements KeyListener, Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2115264660338352079L;
    Player p;
    
    public PlayerController(Player p) {
	this.p = p;
    }
    
    public void keyPressed(KeyEvent e) {
	switch (e.getKeyCode()) {
	case KeyEvent.VK_UP: this.p.startGoingUp(); break;
	case KeyEvent.VK_DOWN: this.p.startGoingDown(); break;
	case KeyEvent.VK_RIGHT: this.p.startGoingRight(); break;
	case KeyEvent.VK_LEFT: this.p.startGoingLeft(); break;
	case KeyEvent.VK_SHIFT: this.p.startSlow(); break;
	case KeyEvent.VK_Z: this.p.startFiring(); break;
	case KeyEvent.VK_ESCAPE: GameInformation.pause(); break;
	}
	
    }

    public void keyReleased(KeyEvent e) {
	switch (e.getKeyCode()) {
	case KeyEvent.VK_UP: this.p.stopGoingUp(); break;
	case KeyEvent.VK_DOWN: this.p.stopGoingDown(); break;
	case KeyEvent.VK_RIGHT: this.p.stopGoingRight(); break;
	case KeyEvent.VK_LEFT: this.p.stopGoingLeft(); break;
	case KeyEvent.VK_SHIFT: this.p.stopSlow(); break;
	case KeyEvent.VK_Z: this.p.stopFiring(); break;
	}
    }

    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
    }
    
}
