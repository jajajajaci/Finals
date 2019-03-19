import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	private Player player;

	public KeyHandler(Player player){
		this.player = player;
	}


public void keyPressed(KeyEvent e){

		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			player.isJumping = true;
			player.isFalling = false;
			player.jump();

			System.out.println("pos: " + player.x + "," + player.y);
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			player.isJumping = false;
			player.isFalling = true;

			System.out.println("pos: " + player.x + "," + player.y);
		}
	}

	public void keyTyped(KeyEvent e){
		
	}

}