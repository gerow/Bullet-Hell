package gerow.cs200.hw2;

/**
 * A purely abstract class to group bullets together. It really has no useful
 * functions other than to call DynamicObject's step and to group
 * DynamicObjects.
 * 
 * @author mike
 * 
 */
public abstract class Bullet extends DynamicObject {
    /**
     * 
     */
    private static final long serialVersionUID = -3937832638732337845L;
    private MapObject origin;

    public MapObject getOrigin() {
        return origin;
    }

    public Bullet(MapObject origin) {
	super();
	this.origin = origin;
    }

    /**
     * When this object steps forward in execution it simply call's
     * DynamicObject's step
     */
    public void step() {
	super.step();
    }

}
