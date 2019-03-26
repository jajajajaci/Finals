import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class StartMenu{

	private Draw draw;
	private MouseInput mouseInput;
	private BufferedImage image;

	private String start = "Start";
	private String exit = "Exit";
	private String score = "HighScores";

	private Button startButton;
	private Button exitButton;
	private Button highScoresButton;

	private int xxx = 1, yyy = 1, zzz = 1;

	public StartMenu(MouseInput mouseInput, Draw draw){

		this.draw = draw;
		this.mouseInput = mouseInput;


		init();
	}

	private void init(){
		startButton = new Button(Draw.WIDTH/2 + 200, Draw.HEIGHT/2 + 100, 200, 100);
		exitButton = new Button(Draw.WIDTH/2 + 200, Draw.HEIGHT/2 + 300 , 200, 100);
		

	}

	public void tick(){


		
	}

	public void render(Graphics g){


		//Title 
		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(image, 0, 0 ,1000, 800, null);

		g.setColor(new Color(2, 27, 149));
		g.setFont(new Font("Arial", Font.ITALIC, 45));
		g.drawString("Space Blasters", Draw.WIDTH/2 - 165, Draw.HEIGHT/2 - 300);

		//Buttons
		g.setColor(new Color(75, 0, 115));
		g2d.fill(startButton.getButtonBounds());
		g2d.fill(exitButton.getButtonBounds());
		g.setColor(Color.BLUE);

		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(start, startButton.getX() + 45, startButton.getY() + 50);
		g.drawString(exit, exitButton.getX() + 45, exitButton.getY() + 50);

		if(startButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){

			g.setColor(new Color(137, 0, 208));
			g2d.fill(startButton.getButtonBounds());
			g.setColor(new Color(195, 0, 255));
			g.drawString(start, startButton.getX() + 45, startButton.getY() + 50);

			if(mouseInput.isClicked == true){
				draw.start = true;

			}
		}

		if(exitButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			
			g.setColor(new Color(137, 0, 208));
			g2d.fill(exitButton.getButtonBounds());
			g.setColor(new Color(195, 0, 255));
			g.drawString(exit, exitButton.getX() + 45, exitButton.getY() + 50);
			
			if(mouseInput.isClicked == true){
				System.exit(1);
			}
		}
	}
}