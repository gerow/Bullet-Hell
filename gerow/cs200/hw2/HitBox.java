package gerow.cs200.hw2;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A class to allow objects to check if they collide with eachother.
 * 
 * @author mike
 * 
 */
public class HitBox implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -371039814412609802L;
    /**
     * A hashtable so that we can check for collisions in O(1) time
     */
    Set<Point> points = new HashSet<Point>();

    /**
     * This sets the hitbox to be a square of the size indicated.
     * 
     * @param size
     */
    public void makeSquareHitbox(int size) {
	for (int i = 0; i < size; ++i) {
	    for (int j = 0; j < size; ++j) {
		points.add(new Point(i, j));
	    }
	}
    }

    /**
     * This sets the hitbox to be a rectangle of the size indicated
     * 
     * @param width
     * @param height
     */
    public void makeRectangularHitbox(int width, int height) {
	for (int i = 0; i < width; ++i) {
	    for (int j = 0; j < height; ++j) {
		points.add(new Point(i, j));
	    }
	}
    }

    /**
     * Makes an offset hitbox so that an object can have a hitbox larger or
     * smaller than it still centered on it.
     * 
     * @param startx top x
     * @param starty left y
     * @param endx bottom x
     * @param endy right y
     */
    public void makeOffsetHitbox(int startx, int starty, int endx, int endy) {
	for (int x = startx; x < endx; ++x) {
	    for (int y = starty; y < endy; ++y) {
		points.add(new Point(x, y));
	    }
	}
    }

    /**
     * Adds a point manually to the hitbox
     * @param p
     */
    public void addPoint(Point p) {
	points.add(p);
    }

    /**
     * Removes a point from the list
     * @param p
     */
    public void removePoint(Point p) {
	points.remove(p);
    }

    /**
     * Clears the hitbox to start afresh.
     */
    public void clear() {
	points.clear();
    }

    /**
     * Gets the number of points in the hitbox.
     * @return
     */
    public int getSize() {
	return points.size();
    }

    /**
     * Checks whether a point collides with another point.
     * @param pointToTest
     * @param objectLocation
     * @return True if the objects collide
     */
    public boolean pointCollides(Point pointToTest, Point objectLocation) {
	Point localPoint = pointToTest.subtract(objectLocation);
	return this.points.contains(localPoint);
    }

    /**
     * Main method used to check if two hitboxes collide
     * @param thisLocation The location of this object
     * @param thatLocation The location of the other object
     * @param thatHitbox The other object's hitbox
     * @return null if the boxes don't collide, the point they first collide at otherwise.
     */
    public Point collidesWith(Point thisLocation, Point thatLocation,
	    HitBox thatHitbox) {
	Iterator<Point> itr = this.points.iterator();
	// System.out.println(this.points);
	while (itr.hasNext()) {
	    Point p = itr.next();
	    p = p.add(thisLocation);
	    // System.out.println("X: " + p.x + " Y: " + p.y);
	    if (thatHitbox.pointCollides(p, thatLocation)) {
		return p;
	    }
	}
	return null;
    }
}
