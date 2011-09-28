package gerow.cs200.hw2;

import java.awt.image.BufferedImage;

/**
 * Enemy that shoots straight bullets.
 * @author mike
 *
 */
public class StraightEnemy extends Enemy {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1753020903147270373L;

    public StraightEnemy(double xVelocity, double yVelocity) {
	super();
	this.xVelocity = xVelocity;
	this.yVelocity = yVelocity;
	this.setKillWhenOutsideMap(true);
	this.setCollisionListener(true);
	this.hitBox.makeRectangularHitbox(this.getXSize(), this.getYSize());
    }
    
    public void step() {
	super.step();
	this.velocityMove();
	if (MapInterface.getFrameNumber() % 8 == 0) {
	    this.fire();
	}
    }

    public BufferedImage getImage() {
	return Assets.getImage("enemy_straight.png");
    }

    /**
     * Inform this object it has been hit.  THis object dies from one hit. 
     */
    public void informCollision(MapObject other, Point p) {
	if (other instanceof Bullet) {
	    this.setAlive(false);
	    SmallHitEffect e = new SmallHitEffect();
	    e.setLocation(p);
	    MapInterface.addEffect(e);
	    Bullet b = (Bullet) other;
	    if (b.getOrigin() instanceof Player) {
		((Player) b.getOrigin()).addScore(250);
		((Player) b.getOrigin()).addMultiplier(1);
	    }
	}

    }
    
    /**
     * Shoot a straight bullet.
     */
    public void fire() {
	int x = this.getLocation().x + 10;
	int y = this.getLocation().y + 25;
	StraightBulletAccelerate s = new StraightBulletAccelerate(0, 0.4, this);
	s.xVelocity = this.xVelocity;
	s.yVelocity = this.yVelocity;
	s.setLocation(new Point(x, y));
	MapInterface.addEnemyBullet(s);
    }

}
