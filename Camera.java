

public class Camera{
	
	protected int x;
	protected int y;

	Player player;

	public Camera(int x, int y){

		this.x = x;
		this.y = y;
	}

	public void tick(Player player){
		this.player = player;

		x--;
		
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
}