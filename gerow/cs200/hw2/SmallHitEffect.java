package gerow.cs200.hw2;

import java.awt.image.BufferedImage;

/**
 * An effect that is displayed to the screen when the player's bullets hit anything.
 * @author mike
 *
 */
public class SmallHitEffect extends Effect {
    /**
     * 
     */
    private static final long serialVersionUID = -4978727727030702987L;
    int frameCount = -1;
    public SmallHitEffect() {
	
    }
    
    public void step() {
	this.frameCount++;
	if (frameCount > 5) {
	    this.setAlive(false);
	}
    }
    
    /**
     * Goes through a number of animation sprites and then dies.
     */
    public BufferedImage getImage() {
	if (frameCount < 1) {
	    return Assets.getImage("small_hit_effect_0.png");
	}
	else if (frameCount < 2) {
	    return Assets.getImage("small_hit_effect_1.png");
	}
	else if (frameCount < 3) {
	    return Assets.getImage("small_hit_effect_2.png");
	}
	else if (frameCount < 4) {
	    return Assets.getImage("small_hit_effect_3.png");
	}
	else if (frameCount < 5) {
	    return Assets.getImage("small_hit_effect_4.png");
	}
	else {
	    return Assets.getImage("small_hit_effect_5.png");
	}
    }

    public void informCollision(MapObject other, Point p) {
	// TODO Auto-generated method stub

    }

}
