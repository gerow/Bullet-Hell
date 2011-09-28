package gerow.cs200.hw2;

import java.io.Serializable;

/**
 * Exception class thrown when an illegal move is made.
 * @author mike
 *
 */
public class InvalidMove extends Exception implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 557387403223431933L;
    String what;
    
    public InvalidMove(String what) {
	this.what = what;
    }
    
    public String toString() {
	return "InvalidMove exception: " + this.what;
    }
}
