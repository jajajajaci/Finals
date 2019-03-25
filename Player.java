import javax.swing.JComponent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;

public class Player{

	public BufferedImage image;
	public URL resource = getClass().getResource("dante.png");

	public int speed = 3;
	public int x = 30;
	public int y = 30;
	public int velX= 1;
	public int velY = 1;
    public int height = 0;
    public int width = 0;
    public int state = 0;
    public int gravity = 1;
    public int ctr = 1;
    public boolean isFalling = true;
    public boolean isJumping = false;
    public int MAX_SPEED = 2;

    Draw draw;


	public Player(){


		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public Player(int x, int y){

		this.x = x;
		this.y = y;


		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();
	}

	public void collision(){

	}

	public void jumpAnimation(){
	
		if(isJumping == true){
			if(ctr==4){
				resource = getClass().getResource("dante.png");
				y = y - 3;
			}else{
				ctr++;
			}
		}
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}	
	}

	public void gravity(Player object){
		
		y+=velY;

			if(isFalling || isJumping){
				velY +=gravity;

				if(velY > MAX_SPEED){ 
					velY = MAX_SPEED;
				}
			}
	}	

	public void setVelY(int velY){
		this.velY = velY;
	}

	public int getVelY(){
		return velY;
	}
	public void jump(){
		jumpAnimation();
	}
	public void sety(int y){
		this.y = y;
	}

	public int gety(){
		return y;
	}
}
	