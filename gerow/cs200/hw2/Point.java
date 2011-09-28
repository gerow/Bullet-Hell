package gerow.cs200.hw2;

import java.io.Serializable;

public class Point implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7188314028619608323L;
    public int x, y;
    private boolean generatedHashcode = false;
    private int hashCode = 0;

    /**
     * Default constructor for point. Initializes with an x and y location.
     * Points are immutable by default.
     * 
     * @param x
     *            The x location
     * @param y
     *            The y location
     */
    public Point(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Copy constructor for point
     * 
     * @param p
     *            The point to initialize as
     */
    public Point(Point p) {
	this.x = p.x;
	this.y = p.y;
    }

    /**
     * Returns a new point equal to this point plus p. This does not modify the
     * point itself (as it is immutable).
     * 
     * @param p
     *            The point to be added.
     * @return this + p
     */
    public Point add(Point p) {
	return new Point(this.x + p.x, this.y + p.y);
    }

    /**
     * Subtracts point p from this point and returns the result as a new point.
     * 
     * @param p
     *            The point to be subtracted
     * @return A new point representing the subtraction
     */
    public Point subtract(Point p) {
	return new Point(this.x - p.x, this.y - p.y);
    }

    /**
     * Overridden equals method to check for equality.
     * 
     * @param other
     *            The object to check equality against.
     * @return True if this object's x and y equal the other object's x and y.
     */
    public boolean equals(Object other) {
	if (this == other) {
	    return true;
	}
	if (other instanceof Point) {
	    return (this.x == ((Point) other).x && this.y == ((Point) other).y);
	}
	return false;
    }

    /**
     * Overridden hashCode method to generate a new hashcode from this object's
     * x and y.
     * 
     * @return A unique hashcode.
     */
    public int hashCode() {
	if (this.generatedHashcode) {
	    return this.hashCode;
	}
	int multiplier = 13;
	this.hashCode = 13 * multiplier;
	this.hashCode = this.hashCode * multiplier + this.x;
	this.hashCode = this.hashCode * multiplier + this.y;
	this.generatedHashcode = true;
	return this.hashCode;
    }
}
