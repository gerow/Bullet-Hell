package gerow.cs200.hw2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

/**
 * A class of static methods to provide a central location from which assets can
 * be loaded.
 * 
 * @author mike
 * 
 */
public class Assets {
    /**
     * A hashtable which is used to get references to images based off their
     * filename representation
     */
    private static Hashtable<String, BufferedImage> images = new Hashtable<String, BufferedImage>();

    /**
     * Method to load all the assets we need. Each of them are listed
     * individually here but we could just make it load all of a specific type
     * of file instead.
     */
    public static void loadAssets() {
	String imageNames[] = new String[] { "player.png", "player_hit.png",
		"cave_bg.png", "orb_medium0.png", "orb_medium1.png",
		"orb_small0.png", "orb_small1.png", "star_bg.png",
		"fog_bg.png", "enemy_squid.png", "enemy_squid_killed.png",
		"small_hit_effect_0.png", "small_hit_effect_1.png",
		"small_hit_effect_2.png", "small_hit_effect_3.png",
		"small_hit_effect_4.png", "small_hit_effect_5.png",
		"enemy_straight.png", "enemy_straight_bullet.png",
		"5multiplier.png" };
	for (int i = 0; i < imageNames.length; ++i) {
	    try {
		Assets.images.put(imageNames[i], ImageIO.read(new File(
			"assets/" + imageNames[i])));
	    } catch (IOException e) {
		System.out.println("Failed to find " + imageNames[i]);
	    }
	}
    }

    /**
     * Get the reference to an image by file name
     * 
     * @param name
     *            The file name of the image.
     * @return A reference to the ImageBuffer
     */
    public static BufferedImage getImage(String name) {
	return images.get(name);
    }
}
