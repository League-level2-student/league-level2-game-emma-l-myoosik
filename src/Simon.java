import javax.swing.JFrame;

public class Simon {
	JFrame frame; 
	GamePanel gamePanel;
	
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	
	Simon(){
		frame = new JFrame();
		gamePanel = new GamePanel();
	}
	
	void setup() {
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(WIDTH, HEIGHT);
		
	}
	
	public static void main(String[] args) {
		Simon game = new Simon();
		game.setup();
	}
}
