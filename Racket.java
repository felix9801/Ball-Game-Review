import java.awt.*;

public class Racket {
	
	//Game object needed for window properties
	private Game game;
	
	//Paddle width and height
	int width = 160;
	int height = 15;
	
	//Start position of paddle
	private int x = 220;
	private int y = 403;
	
	//The start speed of the paddle
	public int moveX = 0;
	
	public Racket(Game game){
		//Sets the private Game game object to the new game object created in this parameter
		this.game = game;
		
	}
	
	//Sets the paddle color to black and renders its position and size
	public void renderRacket(Graphics2D g2d){
		//Will make the rendered object a bit smoother will moving
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Color
		g2d.setColor(Color.BLACK);
		
		//Render
		g2d.fillRect(x, y, width, height);
	}
	//This method is constantly begin called, but moveX will only change after we press a key
	public void moveRacket(){
		
		//As long as the paddle position is greater than 0 or less than the screen width it's allowed to move
		if(x + moveX > 0 && x + moveX < game.getWidth() - width){
			x += moveX;
		}
	}
	
	//Returns paddles position in the current frame of reference
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
}