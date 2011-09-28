package gerow.cs200.hw2;

import java.awt.image.BufferedImage;

/**
 * A kind of bullet that the player shoots
 * 
 * @author mike
 * 
 */
public class LargeOrbBullet extends Bullet {

    /**
     * 
     */
    private static final long serialVersionUID = -1185374069122304908L;

    /**
     * Constructor that sets us up with an initial location, xVelocity,
     * yVelocity, and the object's origin object (Like a Player)
     * 
     * @param location
     * @param xVelocity
     * @param yVelocity
     * @param origin
     */
    public LargeOrbBullet(Point location, int xVelocity, int yVelocity,
	    MapObject origin) {
	super(origin);
	this.setLocation(location);
	this.xVelocity = xVelocity;
	this.yVelocity = yVelocity;
	this.setKillWhenOutsideMap(true);
	this.hitBox.makeSquareHitbox(this.getXSize());
    }

    public BufferedImage getImage() {
	return Assets.getImage("orb_medium0.png");
    }

    public void step() {
	super.step();
	this.velocityMove();
    }

    public void informCollision(MapObject other, Point p) {
	// TODO Auto-generated method stub

    }

}
