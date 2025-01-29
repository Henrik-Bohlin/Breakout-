package lab3;

import java.util.Comparator;

public class NyaHighScores {
	int score;
	String name; 

	public NyaHighScores(int score, String name) {
		this.score = score;
		this.name = name; 

	}
	public void setScore(int score) {
		this.score = score; 
	}
	public int getScore() {
		return score;
	}
	public void setName(String name) {
		this.name = name; 
	}
	public String getName() {
		return name; 
	}

	@Override 
	public String toString() {
		return name + "  " +  score ;
	}
	
}
