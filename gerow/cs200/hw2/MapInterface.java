package gerow.cs200.hw2;

import java.io.Serializable;

/**
 * A static class to allow other objects to talk with the map on a global level
 * @author mike
 *
 */
public class MapInterface implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8895529312809275399L;
    private static Map m;

    public static void setMap(Map m) {
	MapInterface.m = m;
    }

    public static void addPlayerBullet(Bullet b) {
	m.addPlayerBullet(b);
    }

    public static void addEnemyBullet(Bullet b) {
	m.addEnemyBullet(b);
    }
    
    public static int getFrameNumber() {
	return m.getFrameNumber();
    }
    
    public static void addEffect(Effect e) {
	m.addEffect(e);
    }
}