package pong;
import java.awt.*;
public class Ball {
	
	int x;
	int y;
	int size = 30;
	
	double xvel;
	double yvel;
	
	public Rectangle bounds;
	boolean drawBounds;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		xvel = 0;
		yvel = 0;
		drawBounds = false;
		bounds = new Rectangle(x, y, 30, 30);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillOval(x, y, size, size);
		g.setColor(Color.green);
		g.fillOval(x+2, y+2, size-4, size-4);
		if(drawBounds) {
			g.setColor(Color.red);
			g.drawRect(bounds.x, bounds.y, (int)bounds.getWidth(), (int)bounds.getHeight());
		}
	}
	
	public void move() {
		x += xvel;
		y += yvel;
		update();
	}
	
	public void update() {
		bounds = new Rectangle(x, y, 30, 30);
	}
	
	public void setX(int x) {
		this.x = x;
		update();
	}
	
	public void setY(int y) {
		this.y = y;
		update();
	}
	
	public void setXVel(double xvel) {
		this.xvel = xvel;
	}
	
	public void setYVel(double yvel) {
		this.yvel = yvel;
	}
	
}
