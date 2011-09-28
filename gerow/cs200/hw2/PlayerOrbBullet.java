package gerow.cs200.hw2;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The player's bullet.
 * @author mike
 *
 */
public class PlayerOrbBullet extends Bullet {
    /**
     * 
     */
    private static final long serialVersionUID = 1491067214378675879L;
    private int animationFrame = 0;
    Random r = new Random();
    
    public PlayerOrbBullet(Point location, int xVelocity, int yVelocity, MapObject origin) {
	super(origin);
	this.setLocation(location);
	this.xVelocity = xVelocity;
	this.yVelocity = yVelocity;
	this.setKillWhenOutsideMap(true);
	this.hitBox.makeRectangularHitbox(this.getXSize(), this.getYSize());
	this.setCollisionListener(true);
    }

    public BufferedImage getImage() {
	// TODO Auto-generated method stub
	if (r.nextBoolean())
	    return Assets.getImage("orb_small0.png");
	return Assets.getImage("orb_small1.png");
    }

    public void step() {
	super.step();
	++animationFrame;
	this.velocityMove();
    }

    /**
     * If this object hits anything it dies.
     */
    public void informCollision(MapObject other, Point p) {
	//System.out.println("Collision detected");
	if (other instanceof Enemy) {
	    SmallHitEffect e = new SmallHitEffect();
	    e.setLocation(p);
	    MapInterface.addEffect(e);
	    this.setAlive(false);
	    if (this.getOrigin()instanceof Player) {
		((Player) this.getOrigin()).addScore(10);
	    }
	}
    }

}
