import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	//Width and Height of window
	public static final int WIDTH = 600, HEIGHT = WIDTH / 12 * 9;
	
	//Create instances of the Ball and Racket class
	public Ball ball = new Ball(this);
	public Racket racket = new Racket(this);
	
	//We start out with 0 points
	public int points = 0;
	
	//The ball won't spawn in collision with the paddle
	public boolean collided = false;
	
	public Game(){
		
		
		/*
		 * Keys work in the sense that each key represents an integer number
		 * D has the id number 68 and A has the id number 65 
		 * (you can see this if you print the key code using System.out.println(e.getKeyCode()))
		 * 
		 * the implemented methods from the abstract class KeyListener, will use these numbers and compare them 
		 * to certain key codes "VK_D" means the D key. 
		 * 
		 * */
		
		//This will add a KeyListener to the JPanel
		addKeyListener(new KeyListener(){
			
			//When a key is pressed
			public void keyPressed(KeyEvent e) {
				//Check if the key is D
				if(e.getKeyCode() == e.VK_D){
					//If it is move the paddle to the right
					//Explains in the Racket class
					racket.moveX = 2 * ball.speed;
					if(collided){
						
					}
				}
				//If the key is A
				if(e.getKeyCode() == e.VK_A){
					//Move paddle left
					racket.moveX = -2 * ball.speed;
					if(collided){
						
					}
				}
			}
			
			//When a key is released
			@Override
			public void keyReleased(KeyEvent e) {
				//Stop the paddle from moving
				racket.moveX = 0;
			}
			
			//Unimportant implemented method
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
		//Will ensure that we can use the A & D keys straight away
		setFocusable(true);
	}
	
	//Will keep ball and paddle in motion (paddle will just have its speed set at 0)
	private void move(){
		ball.moveBall();
		racket.moveRacket();
	}
	
	//Method from the extended class JPanel
	public void paint(Graphics g) {
		
		//Will update and clear the screen
		super.paint(g);
		
		//Creates an Graphics2D object and casts the Graphics object g to and Graphics2D object, now called g2d
		Graphics2D g2d = (Graphics2D) g;
		
		//Call the individual render methods 
		//Check each class
		ball.render(g2d);
		racket.renderRacket(g2d);
		
		//Creates the score text 
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g2d.drawString("Score: " + points, 5, 30);
		
		//Creates instruction text
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g2d.drawString("Use A & D keys to move", 330, 30);
	}
	
	//When lost 
	public void gameOver(){
		//Dialog box displaying points and new game option
		JOptionPane.showMessageDialog(this, "Game Over! You got " + points + " points! Do you want to Start a New Game ?", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void main(String args[]){
		//New JFrame object
		JFrame frame = new JFrame();
		
		//New Game object
		Game game = new Game();
		
		//Initialize window components
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setTitle("One Man Pong");
		frame.add(game);
		frame.setResizable(false);
		frame.setVisible(true);
		
		//Creates an endless loop that will keep the game updating itself 
    	while(true){
    	//Keep ball moving and paddle movable
		game.move();
		//Repaint the graphics
		game.repaint();
		try {
				//Pauses the main thread for x milliseconds so the game won't be over in an instance
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

		
}