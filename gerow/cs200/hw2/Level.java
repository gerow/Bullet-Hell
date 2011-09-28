package gerow.cs200.hw2;

import java.io.Serializable;

/**
 * An unused object to allow levels to be defined.
 * @author mike
 *
 */
public class Level implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int length;

    public void setLength(int length) {
	this.length = length;
    }

    public int getLength() {
	return length;
    }
}
