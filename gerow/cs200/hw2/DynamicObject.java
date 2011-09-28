package gerow.cs200.hw2;

/**
 * An abstract class for MapObjects that are moving.
 * 
 * @author mike
 * 
 */
public abstract class DynamicObject extends MapObject {
    /**
     * 
     */
    private static final long serialVersionUID = 7591047867669955582L;
    /**
     * A double representation of the object's x position. This is used so that
     * an object can move less that one pixel per cycle.
     */
    protected double xDoublePosition = 0;
    /**
     * Like xDoublePosition, for y
     */
    protected double yDoublePosition = 0;
    /**
     * Used for the velocity of the object in the x direction
     */
    protected double xVelocity = 0;
    /**
     * Used for the velocity of the object in the y direction.
     */
    protected double yVelocity = 0;
    /**
     * Limits the x velocity for certain cases
     */
    protected double maxXVelocity = 0;
    /**
     * Limits the y velocity for certain cases
     */
    protected double maxYVelocity = 0;
    /**
     * The object's xAcceleration
     */
    protected double xAcceleration = 0;
    protected double yAcceleration = 0;
    Point wayPoint = null;

    private boolean stopAtEdgeOfScreen = false;

    /**
     * Step forward onece in execution
     */
    public void step() {
	super.step();
    }

    /**
     * Change this object's location dynamically
     */
    public void setLocation(Point p) {
	super.setLocation(p);
	this.xDoublePosition = p.x;
	this.yDoublePosition = p.y;
    }

    /**
     * This turns the double location to an integer location that can actually
     * be worked with. It also corrects anything that requires being kept on the
     * screen.
     */
    public void setLocationToDoubles() {
	Point newLocation = new Point((int) Math.floor(this.xDoublePosition),
		(int) Math.floor(this.yDoublePosition));
	if (this.stopAtEdgeOfScreen) {
	    if (newLocation.x < 0) {
		newLocation.x = 0;
	    } else if (newLocation.x > GameInformation.getWidth()
		    - this.getXSize()) {
		newLocation.x = GameInformation.getWidth() - this.getXSize();
	    }
	    if (newLocation.y < 0) {
		newLocation.y = 0;
	    } else if (newLocation.y > GameInformation.getHeight()
		    - this.getYSize()) {
		newLocation.y = GameInformation.getHeight() - this.getYSize();
	    }
	}
	this.setLocation(newLocation);
    }

    /**
     * Move according to velocity
     */
    public void velocityMove() {
	this.xDoublePosition += this.xVelocity;
	this.yDoublePosition += this.yVelocity;
	this.setLocationToDoubles();
    }

    /**
     * Move according to acceleration
     */
    public void accelerationMove() {
	this.xVelocity += this.xAcceleration;
	this.yVelocity += this.yAcceleration;
	this.velocityMove();
    }

    /**
     * Decelerate this object to zero
     * @return true when the object has finished decelerating
     */
    public boolean accelerateToZero() {
	if (this.xVelocity > 0) {
	    this.xVelocity -= this.xAcceleration;
	    if (this.xVelocity < 0)
		this.xVelocity = 0;
	} else if (this.xVelocity < 0) {
	    this.xVelocity += this.xAcceleration;
	    if (this.xVelocity > 0)
		this.xVelocity = 0;
	}
	if (this.yVelocity > 0) {
	    this.yVelocity -= this.yAcceleration;
	    if (this.yVelocity > 0)
		this.yVelocity = 0;
	} else if (this.yVelocity < 0) {
	    this.yVelocity += this.yAcceleration;
	    if (this.yVelocity < 0)
		this.yVelocity = 0;
	}
	if (this.xVelocity == 0 && this.yVelocity == 0)
	    return true;
	this.velocityMove();
	return false;

    }

    /**
     * Move by teleport.
     * @throws InvalidMove Thrown if we move to an invalid location.
     */
    public void teleportMove() throws InvalidMove {
	if (wayPoint == null) {
	    throw new InvalidMove("Tried to teleport to null location");
	}
	this.setLocation(new Point(wayPoint));
    }

    /**
     * Makes it to where the object will stop at the edge of the screen
     * 
     * @param stopAtEdgeOfScreen
     *            If true we will stop at the edge of the screen, otherwise we
     *            won't.
     */
    public void setStopAtEdgeOfScreen(boolean stopAtEdgeOfScreen) {
	this.stopAtEdgeOfScreen = stopAtEdgeOfScreen;
    }

    /**
     * Setter for stopAtEdgeOfScreen
     * 
     * @return The current value of stopAtEdgeOfScreen
     */
    public boolean isStopAtEdgeOfScreen() {
	return stopAtEdgeOfScreen;
    }
}
