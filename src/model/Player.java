package model;

public class Player {
	private int mGlobalScore, mCurrentScore;
	
	public Player() {
		mGlobalScore = mCurrentScore = 0;
	}

	public int getGlobalScore() {
		return mGlobalScore;
	}
	
	public void incrementGlobalScore(int amount) {
		mGlobalScore += amount;
	}

	public int getCurrentScore() {
		return mCurrentScore;
	}

	public void setCurrentScore(int currentScore) {
		mCurrentScore = currentScore;
	}
	
	public void incrementCurrentScore(int amount) {
		mCurrentScore += amount;
	}

}
