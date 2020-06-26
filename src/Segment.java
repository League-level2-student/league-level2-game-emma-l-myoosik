import java.util.ArrayList;

public class Segment {
	//private ArrayList<Integer> answerSequence;
	private ArrayList<Integer> userSequence = new ArrayList<Integer>();
	
	Segment(ArrayList<Integer> answerSequence){
//		this.answerSequence = answerSequence;
	}

	
	public boolean userInput(int num) {
		userSequence.add(num);
		System.out.println("answer sequence " + GamePanel.sequence.size());
		System.out.println("user sequence " + userSequence.size());
		int position = userSequence.size() - 1;
		
		return GamePanel.sequence.get(position) == num;
	}
}
