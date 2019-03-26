import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.Random;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Draw extends Canvas implements Runnable{

	public BufferedImage backgroundImage;
	public static Player player;
	private Camera cam;
	private StartMenu startmenu;
	private MouseInput mouse;


	public URL resource = getClass().getResource("stage1.jpg");
	public boolean isRunning = false;
	private boolean initial = false;
	protected boolean start = false;
	private Thread gameThread;

	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private Random rand = new Random();

	public Draw(){

		mouse = new MouseInput();
     	
     	addMouseMotionListener(mouse);
     	addMouseListener(mouse);

     	player = new Player(50, 300);
     	cam = new Camera(0,0);
     	startmenu = new StartMenu(mouse, this);



		try{
			backgroundImage = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		init();
	}

	private void init(){

		for(int i = 1; i <= 100; i++){
				int temp = rand.nextInt(5444);
			if(temp >= 450){
				pipes.add(new Pipe(temp));
			}
		}
		initial = true;
	}

	private void drawPipes(Graphics g){

		for(int i = 1; i <= pipes.size()-1; i++){
			for(int yy = 1; yy <= pipes.size() - 1;yy++){
				if(pipes.get(i).getBounds().intersects(pipes.get(yy).getBounds())){
					pipes.remove(yy);

				}


			}

			}

			initial = false;

	}

	public synchronized void start(){


		if(isRunning)
			return;

		isRunning = true;
		gameThread = new Thread(this);
		gameThread.run();

	}

		
	public void run(){

		long lastTime = System.nanoTime();
		double aOT = 60.0;
		double nano = 1000000000/aOT;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / nano;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frames = 0;
				updates = 0;
			}
		}
	}

	public void render(){

		BufferStrategy bs = this.getBufferStrategy();

		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 600, 600);

		if(start == true){
		g.translate(cam.getX(), cam.getY());
		/////////////////////////////////////////////

		g.drawImage(backgroundImage, 0, 0, null);

		if(initial == true){
			drawPipes(g);
		}

		for(int i = 0; i < pipes.size()-1;i++){
			pipes.get(i).render(g);
		}
		

		
		player.jumpAnimation();



		/////////////////////////////////////////////
		g.translate(-cam.getX(), -cam.getY());
		g.drawImage(player.image, player.x, player.y, null);
		player.render(g);

		}else{

			startmenu.render(g);
		}
		g.dispose();
		bs.show();

	}

	public void collision(){


		for(int i = 0; i < pipes.size(); i++){
			Pipe temp = pipes.get(i);
			System.out.println(i);

			if(player.getBounds().intersects(temp.getBounds())){

				System.out.println(temp.width+ "  " + temp.height);
			}
		}
	}

	public void tick(){

		if(start == true){
			player.gravity(player);
			cam.tick(player);
			collision();
		}else{
			startmenu.tick();
		}
	}


	public static void main(String args[]){
	 	new MyFrame(new Draw(), player); 
	}
}