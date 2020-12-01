import java.awt.event.KeyEvent;

public class PlayerController {
	public Rect rect;
	public KL keyListener;

	public PlayerController(Rect rect, KL keyListener) {
		this.rect = rect;
		this.keyListener = keyListener;
	}
	
	public void update(double dt) {
		if (keyListener.isKeyPressed(KeyEvent.VK_J)) {
			if ((rect.y + Constants.PADDLE_SPEED * dt) + rect.height < Constants.SCREEN_HEIGHT)
				this.rect.y += (Constants.PADDLE_SPEED * dt);
		} else if (keyListener.isKeyPressed(KeyEvent.VK_K)) {
			if (rect.y - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT)
				this.rect.y -= (Constants.PADDLE_SPEED * dt);
		}
	}
}
