package gerow.cs200.hw2;

import java.awt.image.BufferedImage;

/**
 * Class for the pickupable that multiplies score by 5.
 * @author mike
 *
 */
public class Multiplier5 extends Pickup {
    /**
     * 
     */
    private static final long serialVersionUID = 6587176492413981022L;

    public Multiplier5 () {
	this.hitBox.makeRectangularHitbox(this.getXSize(), this.getYSize());
	this.setCollisionListener(true);
    }

    public BufferedImage getImage() {
	// TODO Auto-generated method stub
	return Assets.getImage("5multiplier.png");
    }

    public void informCollision(MapObject other, Point p) {
	if (other instanceof Player) {
	    this.setAlive(false);
	}
    }
}
