package gerow.cs200.hw2;

import java.awt.image.BufferedImage;

/**
 * An enemy of the Player.  Shoots large orbs and swings left and right.
 * @author mike
 *
 */
public class SquidEnemy extends Enemy {
    /**
     * 
     */
    private static final long serialVersionUID = 1686807953039736181L;
    int actionNumber = 0;
    boolean left = false, right;
    int frameNumber = 0;
    private double sineWaveSpeed = 1;
    private int health = 20;

    /**
     * Default constructor.
     * @param velocity The velocity this object will move forward.
     */
    public SquidEnemy(int velocity) {
	this.hitBox.makeRectangularHitbox(this.getXSize(), this.getYSize());
	this.setCollisionListener(true);
	this.yVelocity = velocity;
	this.setKillWhenOutsideMap(true);
    }

    public BufferedImage getImage() {
	return Assets.getImage("enemy_squid.png");
    }

    /**
     * Continues operating as long as it still has health.
     */
    public void step() {
	this.sineWave();
	if (this.health <= 0) {
	    this.setAlive(false);
	}
    }

    /**
     * moves this object in a sine wave pattern.
     */
    public void sineWave() {
	/*
	 * if (actionNumber == 0) { if (!left) { this.xAcceleration = -0.5; }
	 * else { this.xAcceleration = 0.5; } } if (actionNumber < 29) {
	 * this.accelerationMove(); } else { this.xAcceleration = 0.5; if
	 * (this.accelerateToZero()) { this.left = !this.left; this.actionNumber
	 * = -1; } } if (actionNumber % 5 == 0) { this.shootLargeOrb(); }
	 * ++actionNumber;
	 */
	this.frameNumber++;
	double x = 300 * Math.sin(this.frameNumber / 10.0 * this.sineWaveSpeed) + 300;
	if (frameNumber % 2 == 0) {
	    this.shootLargeOrb();
	}
	this.setLocation(new Point((int) Math.floor(x), this.getLocation().y));
	this.velocityMove();
    }

    /**
     * Shootes the bullet.
     */
    public void shootLargeOrb() {
	Bullet b = new LargeOrbBullet(new Point(this.getLocation().x
		+ (this.getXSize() / 2), this.getLocation().y + 60), 0, 6, this);
	MapInterface.addEnemyBullet(b);
    }

    public void informCollision(MapObject other, Point p) {
	this.health--;
	if (this.health == 0 && other instanceof Bullet && ((Bullet) other).getOrigin() instanceof Player) {
	    ((Player)((Bullet) other).getOrigin()).addMultiplier(2);
	   ((Player)((Bullet) other).getOrigin()).addScore(500);
	}
	//System.out.println(this.health);
    }

    public void setSineWaveSpeed(double sineWaveSpeed) {
	this.sineWaveSpeed = sineWaveSpeed;
    }

    public double getSineWaveSpeed() {
	return sineWaveSpeed;
    }
}
