import javax.swing.JFrame;

import java.awt.Dimension;

public class MyFrame extends JFrame{

	private static MyFrame gameFrame;
	private KeyHandler keyhandler;


	public MyFrame(Draw game, Player player){


		keyhandler = new KeyHandler(player);

		setPreferredSize(new Dimension(600, 600));
		setMaximumSize(new Dimension(600, 600));
		setMinimumSize(new Dimension(600, 600));
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		addKeyListener(keyhandler);


		add(game);

		game.start();
	}
} 