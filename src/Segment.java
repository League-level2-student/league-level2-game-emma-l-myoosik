import java.util.ArrayList;
import java.util.Random;

public class Segment {
	int answerList[];
//	ArrayList<Integer> numSequence;
	ArrayList<Integer> userInputs;
	
	static final int PINK = 0;
	static final int BLUE = 1;
	static final int YELLOW = 2;
	static final int GREEN = 3;
	
	Random rand = new Random();
	
	int numTimes = 1;
	
	Segment(){
//		numSequence = new ArrayList<Integer>();
//		numSequence.add(rand.nextInt(3));
		answerList = new int[numTimes];
		createAnswerSequence();
	}
	
	public void createAnswerSequence(){
		for (int i = 0; i < numTimes; i++) {
			answerList[i] = rand.nextInt(3);
		}
	}
	
//	public void addIncreaseSequence() {
//		numSequence.add(rand.nextInt(3));
//	}
	
	public void reset() {
		numTimes ++;
		answerList = new int[numTimes];
		createAnswerSequence();
//		numSequence = new ArrayList<Integer>();
//		addIncreaseSequence();
	}
	
	public boolean userInput(int num) {
		userInputs.add(num);
		int position = userInputs.size() - 1;
		
		return answerList[position] == num;
	}
}
