import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

	public Graphics2D g2;
	public KL keyListener = new KL();
	public Rect playerOne, ai, ball;
	public PlayerController playerController;

	// constructor method for the game window
	public Window() {
		this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.setTitle(Constants.SCREEN_TITLE);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(keyListener);
		Constants.TOOLBAR_HEIGHT = this.getInsets().top;
		
		g2 = (Graphics2D) this.getGraphics();

		// define game graphics
		playerOne = new Rect(
				Constants.HZ_PADDING,
				40,
				Constants.PADDLE_WIDTH,
				Constants.PADDLE_HEIGHT,
				Constants.PADDLE_COLOR
				);
		playerController = new PlayerController(playerOne, keyListener);
		ai = new Rect(
				Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HZ_PADDING,
				40,
				Constants.PADDLE_WIDTH,
				Constants.PADDLE_HEIGHT,
				Color.WHITE
				);
		ball = new Rect(
				Constants.SCREEN_WIDTH / 2, 
				Constants.SCREEN_HEIGHT / 2,
				Constants.BALL_WIDTH,
				Constants.BALL_WIDTH, 
				Constants.PADDLE_COLOR
				);
	}

	public void update(double dt) {
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.draw(dbg);
		g2.drawImage(dbImage, 0, 0, this);

		// print current framerate to the console
//		System.out.println(1 / dt + "fps");

		
		playerController.update(dt);

	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		// game window settings
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		// draw game graphics
		playerOne.draw(g2);
		ai.draw(g2);
		ball.draw(g2);

	}

	// game loop
	public void run() {
		// calculate framerate
		double lastFrameTime = 0.0;
		while (true) {
			double time = Time.getTime();
			double deltaTime = time - lastFrameTime;
			lastFrameTime = time;

			update(deltaTime);

			// limit frame rate to 60 fps
//			try {
//				Thread.sleep(15);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

		}
	}

}
