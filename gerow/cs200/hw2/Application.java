package gerow.cs200.hw2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

/**
 * Holds the main function which loads assets and sets off the game.
 * 
 * @author mike
 * 
 */
public class Application {

    /**
     * The main function only loads assets and then sets the game in motion. The
     * assets are loaded like this to provide a central location where images
     * and such can be accessed.
     * 
     * @param args
     *            This main takes no arguments
     * @throws IOException
     */
    public static void main(String[] args) {
	Assets.loadAssets();
	System.out.print("Please enter your name: ");
	BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
	String name = "default";
	try {
	    name = b.readLine();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.print("Would you like to load an existing game? (y if yes, anything else if no): ");
	String choice = "default";
	try {
	    choice = b.readLine();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Game g = null;
	if (choice.equals("y")) {
	    System.out.println("Loading game...");
	    boolean failed = false;
	    try {
		g = loadGame(name);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("No save file for user " + name);
		g = new Game(name);
		failed = true;
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		//System.out.println("No save file for use " + name);
		//g = new Game(name);
	    }
	    if (!failed)
		g.kickoff();
	}
	else {
	    System.out.println("Making new game");
	    g = new Game(name);
	}
	for (;;) {
	    System.out.println("Type save at any time to save the game");
	    try {
		choice = b.readLine();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    if (choice.equals("save")) {
		g.save();
	    }
	}
	//g.run();
    }
    
    /**
     * Loads the game with the username provided.
     * 
     * @param name The username to load against.
     * @return The loaded game
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Game loadGame(String name) throws IOException, ClassNotFoundException {
	FileInputStream in = new FileInputStream("saves/" + name + ".save");
	ObjectInputStream s = new ObjectInputStream(in);
	return (Game) s.readObject();
    }
}
