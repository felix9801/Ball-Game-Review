import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

public class Ball {
	
	//Diameter of the ball
	public static int D = 50;
	
	//The balls start speed
	private int moveX = 1;
	private int moveY = 1;
	
	//Speed variable
	public int speed = 1;
	
	//Random object for color change and random start position
	public static Random r = new Random();
	
	//Gives ball a random color up to a certain color
	public static Color ballColor = new Color(r.nextInt(0x99ff99));
	
	//Random ball start position x
	public static int x = r.nextInt(Game.WIDTH - D);
	
	//Random ball start position y
	public static int y = r.nextInt(Game.HEIGHT / 2);
	
	//Game object
	Game game;
	
	
	public Ball(Game game){
	this.game = game;
	}
	
	//Called by move() in Game class
	public void moveBall(){
		//If ball x position is at the right edge of screen, change direction
		if(x + moveX > game.getWidth() - D){
			moveX = -1;
		}
		
		//If ball x position is at left edge of screen, change direction
		if(x + moveX <= 0){
			moveX = 1;
		}
		
		//If ball y position is at top of the screen, change direction
		if(y + moveY <= 0){
			moveY = 1;
		}
		
		//If ball y position is at bottom of screen, you've lost
		if(y + moveY > game.getHeight() - D){
			game.gameOver();
		}
		//When collision method returns true
		if(collision()){
			//Change direction of ball
			moveY = -1;
			//Will increase the speed of ball and paddle to a specific cap
			if(speed < 8){
				speed++;
			}
			//Increase game points
			game.points++;
		}
		//ball x position = 1 * (an increasing number) 
		x += moveX * speed;
		
		//ball y position = 1 * (an increasing number) 
		y += moveY * speed;
		
	}
	
	//Render ball
	public void render(Graphics2D g2d){
		
		//Make ball move smoother
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Color
		g2d.setColor(ballColor);
		
		//Oval with position x, y and its diameter
		g2d.fillOval(x, y, D, D);
	}
	
	//Returns the balls position in the current frame of reference
	private Rectangle getBounds(){
		
		//Same bounds as the 
		return new Rectangle(x, y, D, D);
	}
	
	private boolean collision(){
		
		//Does paddle position intersect with ball position ?
		//If it intersects, return true
		return game.racket.getBounds().intersects(getBounds());
	}
	
}
