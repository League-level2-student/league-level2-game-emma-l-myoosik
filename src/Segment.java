import java.util.ArrayList;

public class Segment {
	private ArrayList<Integer> userSequence = new ArrayList<Integer>();
	
	public boolean userInput(int num) {
		userSequence.add(num);
		int position = userSequence.size() - 1;
		
		if(position >= GamePanel.sequence.size()) {
			System.out.println("out of bounds");
			return false;
		}
		
		return GamePanel.sequence.get(position) == num;
	}
	
	public ArrayList<Integer> getUserSequence(){
		return userSequence;
	}
	
	public void resetUserSequence() {
		userSequence.clear();
	}
}
