package pong;
import java.awt.*;
public class Paddle {
	int x;
	int y;
	public Rectangle bounds;
	public Paddle(int x, int y) {
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x, y, 150, 20);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 150, 20);
	}

	public void update() {
		bounds = new Rectangle(x, y, 150, 20);
	}
	
	public void setX(int x) {
		this.x = x;
		update();
	}
	
	
}
