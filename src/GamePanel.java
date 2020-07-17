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
import javax.swing.JOptionPane;
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
	int numRounds = 0;
	
	Timer betweenButtons = new Timer(500, this);
	
	static ArrayList<Integer> sequence = new ArrayList<Integer>();
	
	Segment segment = new Segment();
	
	JLabel pink = new JLabel();
	JLabel blue = new JLabel();
	JLabel yellow = new JLabel();
	JLabel green = new JLabel();
	JLabel control = new JLabel("Ready to play?");
	
	Random rand = new Random();
	
	boolean clickLock = false;
	
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
		
		addToSequence();
		
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
//			super.paintComponent(g);
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
		
		JOptionPane.showMessageDialog(null, "Instructions: \n" + "The premise of Simon is very simple; all it requires is your brain. "
				+ "You will be shown a pattern of different colored squares (either pink, blue, yellow, or green),\nand it is the player's job "
				+ "to enter the correct sequence by clicking the differnet colored-buttons in the order that they appeared." + " Every time "
				+ "you answer correctly, \nthe sequence will add one new square to the sequence; you'll keep going until you mess up--the game will "
				+ "end once that happens. To start each round, press \nthe white button that say 'Ready to play?' Good luck.");
		
		currentPage = GAME;
		
		pink.setVisible(true);
		blue.setVisible(true);
		yellow.setVisible(true);
		green.setVisible(true);
		control.setVisible(true);
		
		repaint();
	}
	
	
	void drawGamePage(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Simon.WIDTH, Simon.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
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
		
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("END GAME", Simon.WIDTH/3, Simon.HEIGHT/10);
		
		g.setFont(pressEnter);
		g.setColor(Color.WHITE);
		String word = "";
		if (numRounds == 1) {
			word = "round";
		} else {
			word = "rounds";
		}
		g.drawString("You passed: " + numRounds + " " + word, Simon.WIDTH/2, Simon.HEIGHT/5);
		
		g.setFont(pressEnter);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to RESTART", Simon.WIDTH/2, Simon.HEIGHT/5 + 25);
		
		numRounds = 0;
		sequence.clear();
		segment.resetUserSequence();
		addToSequence();
		
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
	
	void addToSequence() {
		sequence.add(rand.nextInt(4));
	}
	
	void displaySquare(int i) {
		if (i >= sequence.size()) {
			System.out.println("display square error");
			return;
		} 
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
	
	boolean pause = true;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (sequenceIndex == sequence.size()) {
			betweenButtons.stop();
			sequenceIndex = 0;
			makeButtonsVisible();
			control.setText("Your turn!");
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
				
				control.setText("Ready to play?");
				
				repaint();
			}
			if (currentPage == END) {
				currentPage = START;
				repaint();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if (currentPage == START) {
				currentPage = INSTRUCTIONS;
				
				repaint();
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
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!clickLock) {
			JLabel clicked = (JLabel) e.getSource();
			boolean isCorrect = true;
			
			if (clicked == control){
				System.out.println("works");
				if (control.getText().equals("Ready to play?")) {
					control.setText("...playing");
					betweenButtons.start();
				}
			}  else if (clicked == pink) {
				isCorrect = segment.userInput(0);
			} else if (clicked == blue) {
				isCorrect = segment.userInput(1);
			} else if (clicked == yellow) {
				isCorrect = segment.userInput(2);
			} else if (clicked == green) {
				isCorrect = segment.userInput(3);
			}
				
			if(!isCorrect) {
				makeButtonsInvisible();
				control.setVisible(false);
				currentPage = END;
				
				repaint();
			} else {
				if (sequence.size() == segment.getUserSequence().size()) {
					addToSequence();
					segment.resetUserSequence();
					numRounds++;
					control.setText("Ready to play?");
				}
			}
			
			clickLock = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		clickLock = false;
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
