import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

	Graphics2D g2;
	KL keyListener = new KL();

	public Window() {
		this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		this.setTitle(Constants.SCREEN_TITLE);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(keyListener);
		g2 = (Graphics2D)this.getGraphics();
	}
	
	public void update(double dt) {
		System.out.println(1 / dt + "fps");
		
		g2.setColor(Color.BLACK);
		g2.fillRect( 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT );
		
		// user input
		if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			System.out.println("user is pressing UP arrow");
		}
		
		if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
			System.out.println("user is pressing DOWN arrow");
		}
	}

	// game loop
	public void run() {
		double lastFrameTime = 0.0;
		while (true) {
			double time = Time.getTime();
			double deltaTime = time - lastFrameTime;
			lastFrameTime = time;
			
			update(deltaTime);
			
			// limits frame rate to 60 fps
			try {
				Thread.sleep(15);
			} catch(Exception e) {
				
			}
		}
	}

}
