package pong;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
public class Pong extends JFrame implements MouseMotionListener, MouseListener {
	final private static int WIDTH = 900;
	final private static int HEIGHT = 600;
	
	Paddle player;
	Paddle computer;
	
	Ball ball;
	Random rand = new Random();
	
	boolean touched;
	boolean start;
	int score;
	
	BufferedImage backbuffer;
	Graphics2D g;
	
	int playerscore;
	int computerscore;
	
	public void initGui() {
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
		addMouseListener(this);
		setLayout(new BorderLayout());
		setVisible(true);
		new Engine().start();
	}
	public Pong() {
		super("Pong");
		
		playerscore = 0;
		computerscore = 0;
		backbuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = backbuffer.createGraphics();
		player = new Paddle(WIDTH/2-150/2, HEIGHT-35);
		computer = new Paddle(WIDTH/2-150/2, 35);
		
		ball = new Ball(WIDTH/2, HEIGHT/2);
		
		touched = false;
		start = false;
		score = 0;
		
		initGui();		
	}
	
	public void reset(int who) {
		score = 0;
		switch(who) {
		case 0:
			//player
			score = 1;
			playerscore++;
			break;
		case 1:
			//computer
			score = 2;
			computerscore++;
			break;
		}
		repaint();
		try {
		Thread.sleep(750);
		} catch(Exception e) { }
		score = 0;
		repaint();
		start = false;
		ball.setXVel(0);
		ball.setYVel(0);
		ball.setX(WIDTH/2);
		ball.setY(HEIGHT/2);
		player.setX(WIDTH/2-150/2);
		computer.setX(WIDTH/2-150/2);
		
	}
	
	@Override
	public void paint(Graphics g2) {
		//super.paint(g2);
		g2.drawImage(backbuffer, 0, 0, this);
	}
	
	public static void main(String args[]) {
		new Pong();
	}
	
	public void update() {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 24));
		
			g.setColor(Color.black);
			g.drawString("Player - " + playerscore, WIDTH-250, HEIGHT/2);
			g.drawString("Computer - " + computerscore, WIDTH-250, HEIGHT/2+20);
			if(score == 1) {
				g.drawString("Player score!", WIDTH/2-60, HEIGHT/2-20);
			} else if(score == 2) {
				g.drawString("Computer score!", WIDTH/2-60, HEIGHT/2-20);
			}
		if(!start && score == 0) {
			g.drawString("Click to start", WIDTH/2-60, HEIGHT/2-20);
		}
		
		player.draw(g);
		ball.draw(g);
		computer.draw(g);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) { }
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(start) {
			int x = e.getPoint().x;
			if(x > player.bounds.width/2 && x < WIDTH-player.bounds.width/2) {
				player.setX(x-player.bounds.width/2);
			}
		}
	}
	
	class Engine extends Thread {
		@Override
		public void run() {
			while(!isInterrupted()) {
				try {
					Thread.sleep(10);
					if(start) {

						ball.move();
						
						if(ball.bounds.intersects(player.bounds) && !touched) {
							ball.setYVel(ball.yvel*-1);
							ball.setXVel((rand.nextInt(2) == 1 ? 1 : -1)*rand.nextInt(6));
							touched = true;
						}
						
						if(ball.bounds.intersects(computer.bounds) && touched) {
							ball.setYVel(ball.yvel*-1);
							ball.setXVel((rand.nextInt(2) == 1 ? 1 : -1)*rand.nextInt(6));
							ball.setYVel(ball.yvel*1.05);
							ball.setXVel(ball.xvel*1.05);
							touched = false;
						}
						
						if(ball.y < 0) {
							//player scored
							reset(0);
						}
						
						if(ball.y > HEIGHT) {
							//computer scored
							reset(1);
						}
						
						if(ball.x <= 0 || ball.x >= WIDTH-ball.size/2) {
							ball.setXVel(ball.xvel*-1);
						}
						
						if(computer.x+150/2 < ball.x) {
							computer.setX(computer.x+3);
						} 
						
						if(computer.x+150/2 > ball.x) {
							computer.setX(computer.x-3);
						}
				}
					update();
					repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(!start) {
			score = 0;
			start = true;
			ball.setYVel(2);
			touched = false;
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}
}
