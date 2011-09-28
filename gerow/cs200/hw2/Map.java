package gerow.cs200.hw2;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * A class that holds all the relevant objects necessary to play the game. It
 * also controls game flow and checks collisions between objects.
 * 
 * @author mike
 * 
 */
public class Map implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2081346445507090579L;
    private int frameNumber = 0;
    private Vector<Player> players = new Vector<Player>();
    private Vector<Background> backgrounds = new Vector<Background>();
    private Vector<Bullet> playerBullets = new Vector<Bullet>();
    private Vector<Bullet> enemyBullets = new Vector<Bullet>();
    private Vector<Pickup> pickups = new Vector<Pickup>();
    private Vector<Enemy> enemies = new Vector<Enemy>();
    private Vector<Effect> effects = new Vector<Effect>();
    private PriorityQueue<GameEvent> events = new PriorityQueue<GameEvent>();

    public Map() {
	// this.backgrounds.add(new Background(Assets.getImage("fog_bg.png"),
	// 20));
	// Bullet b = new PlayerOrbBullet();
	// b.setLocation(new Point(50, 50));
	// this.bullets.add(b);
    }

    public void loadLevel(Level l) {

    }

    public void addPlayer(Player p) {
	this.players.add(p);
    }

    public void addBackground(Background b) {
	this.backgrounds.add(b);
    }

    public void addPlayerBullet(Bullet b) {
	this.playerBullets.add(b);
    }

    public void addEnemyBullet(Bullet b) {
	this.enemyBullets.add(b);
    }

    public void addPickup(Pickup p) {
	this.pickups.add(p);
    }

    public void addEnemy(Enemy e) {
	this.enemies.add(e);
    }

    public void addEffect(Effect e) {
	this.effects.add(e);
    }

    /**
     * Step forward one frame. We step in a specific order according to the
     * game. All the methods this calls are self explanatory.
     */
    public void step() {
	// System.out.println(this.bullets.size());
	this.stepBackgrounds();
	this.stepPlayers();
	this.stepPlayerBullets();
	this.stepEnemyBullets();
	this.stepEnemies();
	this.stepPickups();
	this.stepEffects();

	this.checkForCollisions();
	// this.handleEvents();
	++this.frameNumber;
    }

    /**
     * Allow each object to render to the screen as it sees fit.
     * @param g
     */
    public void render(Graphics2D g) {
	this.renderBackgrounds(g);
	this.renderEnemies(g);
	this.renderPlayers(g);
	this.renderPickups(g);
	this.renderPlayerBullets(g);
	this.renderEffects(g);
	this.renderEnemyBullets(g);
    }

    /**
     * Check for sets of collisions and inform objects if they occur.
     */
    public void checkForCollisions() {
	this.checkPlayerAndEnemyBulletCollisions();
	this.checkPlayerBulletAndEnemyCollisions();
	this.checkPlayerAndPickupCollisions();
    }

    public void checkPlayerAndPickupCollisions() {
	Point p;
	for (int i = 0; i < this.players.size(); ++i) {
	    for (int j = 0; j < this.pickups.size(); ++j) {
		Player player = this.players.get(i);
		Pickup pickup = this.pickups.get(j);
		if ((player.isCollisionListener() || pickup
			.isCollisionListener())
			&& ((p = player.collidesWith(pickup)) != null)) {
		    if (player.isCollisionListener())
			player.informCollision(pickup, p);
		    if (pickup.isCollisionListener())
			pickup.informCollision(player, p);
		}
	    }
	}
    }

    public void checkPlayerAndEnemyBulletCollisions() {
	Point p;
	for (int i = 0; i < this.players.size(); ++i) {
	    for (int j = 0; j < this.enemyBullets.size(); ++j) {
		Player player = this.players.get(i);
		Bullet enemyBullet = this.enemyBullets.get(j);
		if ((player.isCollisionListener() || enemyBullet
			.isCollisionListener())
			&& ((p = player.collidesWith(enemyBullet)) != null)) {
		    if (player.isCollisionListener())
			player.informCollision(enemyBullet, p);
		    if (enemyBullet.isCollisionListener())
			enemyBullet.informCollision(player, p);
		}
	    }
	}
    }

    public void checkPlayerBulletAndEnemyCollisions() {
	Point p;
	for (int i = 0; i < this.playerBullets.size(); ++i) {
	    for (int j = 0; j < this.enemies.size(); ++j) {
		Bullet playerBullet = this.playerBullets.get(i);
		Enemy enemy = this.enemies.get(j);
		if ((playerBullet.isCollisionListener() || enemy
			.isCollisionListener())
			&& ((p = playerBullet.collidesWith(enemy)) != null)) {
		    if (playerBullet.isCollisionListener())
			playerBullet.informCollision(enemy, p);
		    if (enemy.isCollisionListener())
			enemy.informCollision(playerBullet, p);
		}
	    }
	}
    }

    public void stepBackgrounds() {
	for (int i = 0; i < this.backgrounds.size(); ++i) {
	    this.backgrounds.get(i).step();
	}
    }

    public void stepPlayerBullets() {
	for (int i = 0; i < this.playerBullets.size(); ++i) {
	    if (!playerBullets.get(i).isAlive()) {
		this.playerBullets.remove(i);
		--i;
	    } else
		this.playerBullets.get(i).step();
	}
    }

    public void stepEnemyBullets() {
	for (int i = 0; i < this.enemyBullets.size(); ++i) {
	    if (!enemyBullets.get(i).isAlive()) {
		this.enemyBullets.remove(i);
		--i;
	    } else
		this.enemyBullets.get(i).step();
	}
    }

    public void stepPlayers() {
	for (int i = 0; i < this.players.size(); ++i) {
	    if (!players.get(i).isAlive()) {
		this.players.remove(i);
		--i;
	    } else
		this.players.get(i).step();
	}
    }

    public void stepEnemies() {
	for (int i = 0; i < this.enemies.size(); ++i) {
	    if (!enemies.get(i).isAlive()) {
		this.enemies.remove(i);
		--i;
	    } else
		this.enemies.get(i).step();
	}
    }

    public void stepPickups() {
	for (int i = 0; i < this.pickups.size(); ++i) {
	    if (!pickups.get(i).isAlive()) {
		this.pickups.remove(i);
		--i;
	    } else
		this.pickups.get(i).step();
	}
    }

    public void stepEffects() {
	for (int i = 0; i < this.effects.size(); ++i) {
	    if (!effects.get(i).isAlive()) {
		this.effects.remove(i);
		--i;
	    } else
		this.effects.get(i).step();
	}
    }

    public void renderBackgrounds(Graphics2D g) {
	for (int i = 0; i < this.backgrounds.size(); ++i) {
	    this.backgrounds.get(i).render(g);
	}
    }

    public void renderPlayers(Graphics2D g) {
	for (int i = 0; i < this.players.size(); ++i) {
	    players.get(i).render(g);
	}
    }

    public void renderEnemies(Graphics2D g) {
	for (int i = 0; i < this.enemies.size(); ++i) {
	    enemies.get(i).render(g);
	}
    }

    public void renderPlayerBullets(Graphics2D g) {
	for (int i = 0; i < this.playerBullets.size(); ++i) {
	    playerBullets.get(i).render(g);
	}
    }

    public void renderEnemyBullets(Graphics2D g) {
	for (int i = 0; i < this.enemyBullets.size(); ++i) {
	    enemyBullets.get(i).render(g);
	}
    }

    public void renderPickups(Graphics2D g) {
	for (int i = 0; i < this.pickups.size(); ++i) {
	    pickups.get(i).render(g);
	}
    }

    public void renderEffects(Graphics2D g) {
	for (int i = 0; i < this.effects.size(); ++i) {
	    effects.get(i).render(g);
	}
    }

    public void handleEvents() {
	while (this.events.peek().getFrameNumber() == this.frameNumber) {
	    this.events.poll().execute();
	}
    }

    public int getFrameNumber() {
	return this.frameNumber;
    }
}
