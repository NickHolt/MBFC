package model;

public class Player {
	/* The score values for a Player. Note that each Player may have a different
	 * maximum achievable score to allow for handicaps. */
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
		//System.out.println("Score incremented by " + amount);
		mCurrentScore += amount;
	}
}
