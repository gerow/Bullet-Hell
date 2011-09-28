package gerow.cs200.hw2;

import java.awt.image.BufferedImage;

/**
 * A specific kind of bullet that is thin and goes straight.
 * @author mike
 *
 */
public class StraightBullet extends Bullet {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4244042377744342265L;

    public StraightBullet(MapObject origin) {
	super(origin);
	this.setKillWhenOutsideMap(true);
	//this.setCollisionListener(true);
    }
    
    public void step() {
	super.step();
	
    }

    public BufferedImage getImage() {
	// TODO Auto-generated method stub
	return Assets.getImage("enemy_straight_bullet.png");
    }

    public void informCollision(MapObject other, Point p) {
	// TODO Auto-generated method stub
	
    }

}
