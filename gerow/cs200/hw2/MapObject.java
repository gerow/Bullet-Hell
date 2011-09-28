package gerow.cs200.hw2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * The base object for anything that goes on the map.
 * 
 * @author mike
 * 
 */
public abstract class MapObject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8267953200429623526L;
    private boolean killWhenOutsideMap = false;
    protected HitBox hitBox = new HitBox();
    private Point location;
    private boolean alive = true;
    private boolean collisionListener = false;

    /**
     * Step forward one frame in execution, it also checks if an object is
     * outside the map and kills it if the object requests this.
     */
    public void step() {
	if (this.killWhenOutsideMap) {
	    if (this.location.x > GameInformation.getWidth()
		    || this.location.y > GameInformation.getHeight()
		    || this.location.x < -this.getXSize()
		    || this.location.y < -this.getYSize()) {
		this.setAlive(false);
	    }
	}
    }

    /**
     * Get this object's x size. By default it is the current image's width.
     * 
     * @return
     */
    public int getXSize() {
	return this.getImage().getWidth();
    }

    /**
     * Get this object's y size. By default this is the current image's height
     * 
     * @return
     */
    public int getYSize() {
	return this.getImage().getHeight();
    }

    /**
     * Set whether or not we want this object to be culled when outside the map.
     * 
     * @param killWhenOutsideMap
     */
    public void setKillWhenOutsideMap(boolean killWhenOutsideMap) {
	this.killWhenOutsideMap = killWhenOutsideMap;
    }

    public boolean isKillWhenOutsideMap() {
	return killWhenOutsideMap;
    }

    public abstract BufferedImage getImage();

    /**
     * Renders to the graphics2D. By default this is to simply draw getImage,
     * but objects can override this to do fancy things.
     * 
     * @param g
     */
    public void render(Graphics2D g) {
	g.drawImage(this.getImage(), this.location.x, this.location.y, null);
    }

    /**
     * Set this object's hitbox.
     * @param hitBox
     */
    public void setHitBox(HitBox hitBox) {
	this.hitBox = hitBox;
    }

    public HitBox getHitBox() {
	return hitBox;
    }

    /**
     * Called to check if one object collides with another.
     * @param other the object to be checked against.
     * @return The point where they collide, or null if they don't
     */
    public Point collidesWith(MapObject other) {
	return this.hitBox.collidesWith(this.getLocation(),
		other.getLocation(), other.getHitBox());
    }

    /**
     * Called to inform the class of a collision.
     * @param other
     * @param p
     */
    public abstract void informCollision(MapObject other, Point p);

    public void setLocation(Point location) {
	this.location = location;
    }

    public Point getLocation() {
	return location;
    }

    public void setAlive(boolean alive) {
	this.alive = alive;
    }

    public boolean isAlive() {
	return alive;
    }

    /**
     * Set whether or not this object should be listening for collisions with it.
     * @param collisiobListener
     */
    public void setCollisionListener(boolean collisiobListener) {
	this.collisionListener = collisiobListener;
    }

    public boolean isCollisionListener() {
	return collisionListener;
    }
}
