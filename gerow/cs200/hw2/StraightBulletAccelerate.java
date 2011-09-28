package gerow.cs200.hw2;

/**
 * Same as a straight bullet except it accelerates over time.
 * @author mike
 *
 */
public class StraightBulletAccelerate extends StraightBullet {

    /**
     * 
     */
    private static final long serialVersionUID = 7273031150113192347L;
    /**
     * Constructs object to allow acceleration of bullets and an origin.
     * @param xAcceleration
     * @param yAcceleration
     * @param origin
     */
    public StraightBulletAccelerate(double xAcceleration, double yAcceleration, MapObject origin) {
	super(origin);
	this.yAcceleration = yAcceleration;
	this.xAcceleration = xAcceleration;
	this.hitBox.makeRectangularHitbox(this.getXSize(), this.getYSize());
    }
    /**
     * Move by acceleration
     */
    public void step() {
	super.step();
	this.accelerationMove();
    }
}
