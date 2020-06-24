import java.util.ArrayList;

public class Segment {
	private ArrayList<Integer> answerSequence = new ArrayList<Integer>();
	private ArrayList<Integer> userSequence = new ArrayList<Integer>();
	
	Segment(ArrayList<Integer> answerSequence){
		this.answerSequence = answerSequence;
		for (int i = 0; i < answerSequence.size(); i++) {
			System.out.print(answerSequence.get(i) + " ");
		}
	}

	
	public boolean userInput(int num) {
		userSequence.add(num);
		int position = userSequence.size() - 1;
		
		
		
		return answerSequence.get(position) == num;
	}
}
