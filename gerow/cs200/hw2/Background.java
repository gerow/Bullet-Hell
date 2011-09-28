package gerow.cs200.hw2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * A class designed to handle looping backgrounds.
 * 
 * @author mike
 * 
 */
public class Background implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4087110851245713263L;
    /**
     * The image that the background is comppsed of
     */
    private String imageName;
    /**
     * The speed that the background will go by
     */
    private int speed;
    /**
     * A transient value that represents the location of one of the two images.
     */
    private int location0;
    /**
     * Another transient value that represents the location of the second of the
     * two images.
     */
    private int location1;

    /**
     * Constructor to create the Background
     * 
     * @param image
     *            The image that will be displayed
     * @param speed
     *            The speed it will run at.
     */
    public Background(String imageName, int speed) {
	this.imageName = imageName;
	this.location0 = 0;
	this.location1 = -Assets.getImage(imageName).getHeight();
	this.speed = speed;
    }

    /**
     * Step forward once. This involves moving the images down by a speed and
     * moving anything that falls off the bottom back to the top.
     */
    public void step() {
	location0 += speed;
	location1 += speed;
	if (location0 > GameInformation.getHeight()) {
	    location0 -= 2 * Assets.getImage(imageName).getHeight();
	}
	if (location1 > GameInformation.getHeight()) {
	    location1 -= 2 * Assets.getImage(imageName).getHeight();
	}
    }

    /**
     * Render the current representation of the image to the provided graphics
     * object.
     * 
     * @param g
     *            The graphics object to render this to.
     */
    public void render(Graphics2D g) {
	g.drawImage(Assets.getImage(imageName), 0, location0, null);
	g.drawImage(Assets.getImage(imageName), 0, location1, null);
    }
}
