package model;

public class Player {
	private int mScore;

	public Player() {
		setScore(0);
	}

	public int getScore() {
		return mScore;
	}

	public float getScoreAsPercentage(int maxScore) {
		return (float) mScore / (float) maxScore;
	}

	public void setScore(int score) {
		mScore = score;
	}
	
	public void updateScore(int updateAmount) {
		mScore += updateAmount;
	}
}
