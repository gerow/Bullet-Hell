package gerow.cs200.hw2;

import java.io.Serializable;

/**
 * Static object to allow other objects to get information about the game.
 * @author mike
 *
 */
public class GameInformation implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8850958962533833878L;
    private static Game g;
    public static void setGame(Game g) {
	GameInformation.g = g;
    }
    public static int getWidth() {
	return g.getWidth();
    }
    public static int getHeight() {
	return g.getHeight();
    }
    public static void pause() {
	g.pause();
    }
    public static void resume() {
	g.resume();
    }
    public static void gameOver(int score) {
	g.gameOver(score);
    }
}
