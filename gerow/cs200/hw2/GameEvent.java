package gerow.cs200.hw2;

import java.io.Serializable;

/**
 * An unused class to allow specific levels of events.
 * @author mike
 *
 */
public abstract class GameEvent implements Comparable<GameEvent>, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1615834148852369999L;
    Map parent;
    int frameNumber = 0;
    public GameEvent(Map parent, int frameNumber) {
	this.parent = parent;
	this.frameNumber = frameNumber;
    }
    public int getFrameNumber() {
	return this.frameNumber;
    }
    public int compareTo(GameEvent other) {
	if (this.frameNumber < other.getFrameNumber()) {
	    return -1;
	}
	else if (this.frameNumber > other.getFrameNumber()) {
	    return 1;
	}
	return 0;
    }
    public abstract void execute();
}
