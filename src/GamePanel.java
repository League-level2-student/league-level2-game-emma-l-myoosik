import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int START = 0;
	final int INSTRUCTIONS = 1;
	final int GAME = 2;
	final int END = 3;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Font titleFont;
	Font pressEnter;
	Font instructions;
	
	int currentPage = START;
	
	Timer frameDraw;
	
	Segment segment;
	int buttonclicked;
	
	JButton pink = new JButton("pink");
	JButton blue = new JButton("blue");
	JButton yellow = new JButton("yellow");
	JButton green = new JButton("green");
	
	GamePanel(){
		titleFont = new Font("Courier", Font.PLAIN, 48);
		pressEnter = new Font("Courier", Font.PLAIN, 20);
		instructions = new Font("Verdana", Font.PLAIN, 14);
		
//		frameDraw = new Timer(1000/60, this);
//		frameDraw.start();
		
		if (needImage) {
		    loadImage ("soothingbackground.jpg");
		}
		
		setLayout(new GridLayout(2,2));
		
		pink.setBackground(Color.PINK);
		pink.setPreferredSize(new Dimension(Simon.WIDTH/3, Simon.HEIGHT/4));
		pink.setVisible(false);
		add(pink);
		
		blue.setBackground(Color.BLUE);
		blue.setVisible(false);
		add(blue);
		
		yellow.setBackground(Color.YELLOW);
		yellow.setVisible(false);
		add(yellow);
		
		green.setBackground(Color.GREEN);
		green.setVisible(false);
		add(green);
		
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (currentPage == START) {
			drawStartPage(g);
		} else if (currentPage == INSTRUCTIONS) {
			drawInstructionPage(g);
		} else if (currentPage == GAME) {
			super.paintComponent(g);
			drawGamePage(g);
		} else if (currentPage == END) {
			drawEndGamePage(g);
		}
	}
	
	void updateStartPage() {
		
	}
	
	void updateInstructionPage() {
		
	}
	
	void updateGamePage() {
		
	}
	
	void updateEndGamePage() {
		
	}
	
	void drawStartPage(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Simon.WIDTH, Simon.HEIGHT);
		}
		
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Simon", Simon.WIDTH/3, Simon.HEIGHT/10);
		
		g.setFont(pressEnter);
		g.setColor(Color.WHITE);
		g.drawString("Click ENTER to BEGIN", Simon.WIDTH/2, Simon.HEIGHT/5);
		
		g.setFont(pressEnter);
		g.setColor(Color.WHITE);
		g.drawString("Click SPACE KEY to view INSTRUCTIONS", Simon.WIDTH/3, Simon.HEIGHT/4);
	}
	
	void drawInstructionPage(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Simon.WIDTH, Simon.HEIGHT);
		}	
		
	}
	
	void drawGamePage(Graphics g) {
//		if (gotImage) {
//			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
//		} else {
//			g.setColor(Color.BLUE);
//			g.fillRect(0, 0, Simon.WIDTH, Simon.HEIGHT);
//		}
		
		for (int i = 0; i < Segment.answerList.length; i++) {
			System.out.println(Segment.answerList[i]);
		}
		
		pink.addActionListener(this);
		blue.addActionListener(this);
		yellow.addActionListener(this);
		green.addActionListener(this);

	}
	
	void drawEndGamePage(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Simon.WIDTH, Simon.HEIGHT);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed == pink) {
			buttonclicked = Segment.PINK;
			System.out.println("pink");
		} else if (buttonPressed == blue) {
			buttonclicked = Segment.BLUE;
			System.out.println("blue");
		} else if (buttonPressed == yellow) {
			buttonclicked = Segment.YELLOW;
			System.out.println("yellow");
		} else if (buttonPressed == green) {
			buttonclicked = Segment.GREEN;
			System.out.println("green");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (currentPage == START) {
				currentPage = GAME;
				
				pink.setVisible(true);
				blue.setVisible(true);
				yellow.setVisible(true);
				green.setVisible(true);
				
				repaint();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentPage == START) {
				currentPage = INSTRUCTIONS;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
