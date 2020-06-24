import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener {
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
	int sequenceIndex = 0;
	
	Timer betweenButtons = new Timer(500, this);
	
	int buttonclicked;
	
	ArrayList<Integer> sequence = new ArrayList<Integer>();
	ArrayList<Boolean> numCorrect = new ArrayList<Boolean>();
	
	Segment segment = new Segment(sequence);
	
	JLabel pink = new JLabel();
	JLabel blue = new JLabel();
	JLabel yellow = new JLabel();
	JLabel green = new JLabel();
	JLabel control = new JLabel("Ready to play?");
	
	Random rand = new Random();
	
	GamePanel(){
		titleFont = new Font("Courier", Font.PLAIN, 48);
		pressEnter = new Font("Courier", Font.PLAIN, 20);
		instructions = new Font("Verdana", Font.PLAIN, 14);
		
		control.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (needImage) {
		    loadImage ("soothingbackground.jpg");
		}
		
		setLayout(null);
		
		Dimension dim = new Dimension(Simon.WIDTH/3, Simon.HEIGHT/4);
		
		pink.setBackground(Color.PINK);
		pink.setPreferredSize(dim);
		pink.setVisible(false);
		pink.setBounds(100, 20, dim.width, dim.height);
		pink.setOpaque(true);
		add(pink);
		
		blue.setBackground(Color.BLUE);
		blue.setPreferredSize(dim);
		blue.setVisible(false);
		blue.setBounds(400, 20, dim.width, dim.height);
		blue.setOpaque(true);
		add(blue);
		
		yellow.setBackground(Color.YELLOW);
		yellow.setPreferredSize(dim);
		yellow.setVisible(false);
		yellow.setBounds(100, 220, dim.width, dim.height);
		yellow.setOpaque(true);
		add(yellow);
		
		green.setBackground(Color.GREEN);
		green.setPreferredSize(dim);
		green.setVisible(false);
		green.setBounds(400, 220, dim.width, dim.height);
		green.setOpaque(true);
		add(green);
		
		control.setBackground(Color.WHITE);
		control.setPreferredSize(dim);
		control.setVisible(false);
		control.setBounds(250, 400, dim.width, dim.height);
		control.setOpaque(true);
		add(control);
		
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
		if (gotImage) {
			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Simon.WIDTH, Simon.HEIGHT);
		}
		
		pink.addMouseListener(this);
		blue.addMouseListener(this);
		yellow.addMouseListener(this);
		green.addMouseListener(this);
		control.addMouseListener(this);

	}
	
	void drawEndGamePage(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Simon.WIDTH, Simon.HEIGHT);
		}
	}
	
	void makeButtonsInvisible() {
		pink.setVisible(false);
		blue.setVisible(false);
		yellow.setVisible(false);
		green.setVisible(false);
	}
	
	void makeButtonsVisible() {
		pink.setVisible(true);
		blue.setVisible(true);
		yellow.setVisible(true);
		green.setVisible(true);
	}
	
	void createSequence() {
		sequence.add(rand.nextInt(4));
	}
	
	void displaySquare(int i) {
		makeButtonsInvisible();
		if(sequence.get(i) == 0) {
			pink.setVisible(true);
		} else if (sequence.get(i) == 1) {
			blue.setVisible(true);
		} else if (sequence.get(i) == 2) {
			yellow.setVisible(true);
		} else if (sequence.get(i) == 3) {
			green.setVisible(true);
		}
		
		sequenceIndex++; 
		repaint();
		
	}
	
	boolean checkIfCorrect() {
		return true;
	}
	
	boolean pause = true;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("it's working");
		
		if (sequenceIndex == sequence.size()) {
			betweenButtons.stop();
			sequenceIndex = 0;
			makeButtonsVisible();
			control.setText("Your turn!");
			for (int i = 0; i < sequence.size(); i++) {
				System.out.println(sequence.get(i));
			}
		} else {
			if (pause) {
				makeButtonsInvisible();
				pause = false;
			} else {
				displaySquare(sequenceIndex);
				pause = true;
			}
		}
		
		repaint();
		
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
				control.setVisible(true);
				
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == control){
			if (control.getText().equals("Ready to play?")) {
				control.setText("...playing");
				createSequence();
				betweenButtons.start();
			} 
		} 
		
		// for some reason it still allows me to click buttons, but doesn't appear to add anything to the list
		for (int i = 0; i < sequence.size(); i++) {
			boolean isCorrect = true;
			if (e.getSource() == pink) {
				isCorrect = segment.userInput(0);
				System.out.println("pink");
			} else if (e.getSource() == blue) {
				isCorrect = segment.userInput(1);
				System.out.println("blue");
			} else if (e.getSource() == yellow) {
				isCorrect = segment.userInput(2);
				System.out.println("yellow");
			} else if (e.getSource() == green) {
				isCorrect = segment.userInput(3);
				System.out.println("green");
			}
			
			if(!isCorrect) {
				control.setText("You f*cked up");
				sequence.clear();
				break;
			} else {
				numCorrect.add(isCorrect);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
