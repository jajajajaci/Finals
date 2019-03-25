import javax.swing.JComponent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Pipe{
		
	public BufferedImage pipeImage;
	public URL resource = getClass().getResource("obstacleup.png");

	public BufferedImage pipeImage2;
	public URL resource1 = getClass().getResource("obstacledown.png");

	public int x, y;
	public int width, height;

	public int x1, y1;
	public int width1, height1;

	public Random rand = new Random();


	public Pipe(int x){

		this.x = x;

		int temp = rand.nextInt(100);
		temp = -temp;
		this.y = temp;

		try{
			pipeImage = ImageIO.read(resource);
			pipeImage2 = ImageIO.read(resource1);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		width = pipeImage.getWidth();
		height = pipeImage.getHeight();
		
		width1 = pipeImage2.getWidth();
		height1 = pipeImage2.getHeight();


		x1 = x ;
		y1 = y + 450;
	}


	public Rectangle getBounds(){
		return(new Rectangle(x, y, width, height));
	}

	public Rectangle bottomBounds(){
		return(new Rectangle(x1, y1, width1, height1));
	}


	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;



		g.drawImage(pipeImage, x, y, null);

		g.setColor(Color.RED);
		g2d.draw(getBounds());


		g.drawImage(pipeImage2, x1, y1, null);

		g.setColor(Color.RED);
		g2d.draw(bottomBounds());
	}
}