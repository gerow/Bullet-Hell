package gerow.cs200.hw2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The user's main object.
 * @author mike
 *
 */
public class Player extends DynamicObject {
    /**
     * 
     */
    private static final long serialVersionUID = -7028040123926446228L;
    private final double NORMAL_SPEED = 10;
    private final double SLOW_SPEED = 4;
    private double speed = NORMAL_SPEED;
    private double diagonalSpeed;
    private boolean up, down, left, right;
    private boolean slow = false;
    private boolean fire = false;
    private boolean firstShot = true;
    private boolean dying = false;
    private int deathCounter = 0;
    private int score = 0;
    private int multiplier = 1;
    private int lives = 8;
    private Rectangle2D.Double deathRectangle = new Rectangle2D.Double();

    /**
     * Default constructor just sets up information for controlling the user.
     */
    public Player() {
	up = down = left = right = false;
	diagonalSpeed = speed / Math.sqrt(2);
	this.setStopAtEdgeOfScreen(true);
	// this.hitBox.makeOffsetHitbox(this.getXSize() / 4, this.getYSize() /
	// 4,
	// this.getXSize() * 3 / 4, this.getYSize() * 3 / 4);
	this.hitBox
		.addPoint(new Point(this.getXSize() / 2, this.getYSize() / 2));
	this.setCollisionListener(true);
	this.deathRectangle.width = GameInformation.getWidth();
	this.deathRectangle.height = GameInformation.getHeight();
    }

    /**
     * Step forward in execution.  Mainly concerned with moving.
     */
    public void step() {
	super.step();
	if (up && left) {
	    this.xVelocity = -diagonalSpeed;
	    this.yVelocity = -diagonalSpeed;
	} else if (up && right) {
	    this.xVelocity = diagonalSpeed;
	    this.yVelocity = -diagonalSpeed;
	} else if (right && down) {
	    this.xVelocity = diagonalSpeed;
	    this.yVelocity = diagonalSpeed;
	} else if (down && left) {
	    this.xVelocity = -diagonalSpeed;
	    this.yVelocity = diagonalSpeed;
	} else if (up) {
	    this.xVelocity = 0;
	    this.yVelocity = -speed;
	} else if (right) {
	    this.xVelocity = speed;
	    this.yVelocity = 0;
	} else if (down) {
	    this.xVelocity = 0;
	    this.yVelocity = speed;
	} else if (left) {
	    this.xVelocity = -speed;
	    this.yVelocity = 0;
	} else {
	    this.xVelocity = 0;
	    this.yVelocity = 0;
	}
	if (this.fire) {
	    this.fire();
	}
	if (this.dying) {
	    ++this.deathCounter;
	    if (this.deathCounter > 60)
		this.dying = false;
	}
	super.velocityMove();
    }

    /**
     * Do fancy stuff with the graphics besides the default.
     */
    public void render(Graphics2D g) {
	super.render(g);
	if (this.dying && this.deathCounter % 10 < 5) {
	    g.setColor(new Color(200, 0, 0, 128));
	    g.fill(this.deathRectangle);
	}
	this.printScore(g);
    }

    public BufferedImage getImage() {
	if (slow) {
	    return Assets.getImage("player_hit.png");
	}
	return Assets.getImage("player.png");
    }

    public void startGoingRight() {
	right = true;
    }

    public void stopGoingRight() {
	right = false;
    }

    public void startGoingLeft() {
	left = true;
    }

    public void stopGoingLeft() {
	left = false;
    }

    public void startGoingUp() {
	up = true;
    }

    public void stopGoingUp() {
	up = false;
    }

    public void startGoingDown() {
	down = true;
    }

    public void stopGoingDown() {
	down = false;
    }

    public void startSlow() {
	slow = true;
	this.speed = this.SLOW_SPEED;
	this.diagonalSpeed = this.SLOW_SPEED / Math.sqrt(2);
    }

    public void stopSlow() {
	slow = false;
	this.speed = this.NORMAL_SPEED;
	this.diagonalSpeed = this.NORMAL_SPEED / Math.sqrt(2);
    }

    public void startFiring() {
	fire = true;
    }

    public void stopFiring() {
	fire = false;
	firstShot = true;
    }

    /**
     * Fire bullets.  THis generates bullets in front of the object.
     */
    public void fire() {
	if (MapInterface.getFrameNumber() % 4 == 0 || firstShot) {
	    PlayerOrbBullet b0 = new PlayerOrbBullet(new Point(this
		    .getLocation().x
		    + this.getXSize() / 4, this.getLocation().y - 3), 0, -15,
		    this);
	    PlayerOrbBullet b1 = new PlayerOrbBullet(new Point(this
		    .getLocation().x
		    + this.getXSize() * 3 / 4, this.getLocation().y - 3), 0,
		    -15, this);
	    MapInterface.addPlayerBullet(b0);
	    MapInterface.addPlayerBullet(b1);
	    this.firstShot = false;
	}
    }

    /**
     * Add a number to the score.  This will be affected by the multiplier.
     * @param n
     */
    public void addScore(int n) {
	this.score += this.multiplier * n;
    }

    /**
     * Add to the multiplier
     * @param n
     */
    public void addMultiplier(int n) {
	this.multiplier += n;
    }

    /**
     * Called if the player is hit.
     */
    public void die() {
	--this.lives;
	if (this.lives <= 0) {
	    GameInformation.gameOver(this.score);
	}
	this.dying = true;
	this.multiplier = 1;
	this.deathCounter = 0;
	this.setLocation(new Point(375, 500));
    }

    /**
     * Print the player's score to the screen, in addition to other useful information.
     * @param g
     */
    public void printScore(Graphics2D g) {
	String livesString = "Lives: " + this.lives;
	String scoreString = "Score: " + this.score;
	String multiplierString = "Multiplier: x" + this.multiplier;
	int x = 20;
	int startLocation = 40;
	int spaceBetween = 14;
	g.setColor(Color.white);
	g.drawString(livesString, x, startLocation);
	g.drawString(scoreString, x, startLocation + spaceBetween);
	g.drawString(multiplierString, x, startLocation + 2 * spaceBetween);
    }

    /**
     * Inform the Player of a collision
     */
    public void informCollision(MapObject other, Point p) {
	// System.out.println("Collision detected");
	if (other instanceof Bullet) {
	    if (!this.dying) {
		this.die();
	    }
	}
	else if (other instanceof Multiplier5) {
	    this.multiplier += 5;
	}
    }
}
