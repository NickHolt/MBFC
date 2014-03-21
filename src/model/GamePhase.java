package model;

import view.LedMatrix;
import controller.Engine;

/** A phase in a game of Mind Body Fitness Challenge is a particular challenge that both
 *  players face for a set period of time. Each player will accumulate a score specific to 
 *  that phase which will be used to update the global score.
 *  
 *  An entire MBFC playthrough involved progressing through several GamePhases.
 *  
 *  GamePhases have direct access to the GalileoBoard and are responsible for prompting the 
 *  user as necessary. A GamePhase is also responsible for reporting the update in player
 *  score to the Galileo so the progress bars can be updates accordingly.
 * 
 * @author nickholt
 */
public abstract class GamePhase {
	/* The unique PhaseTag describing this GamePhase */
	protected PhaseTag mPhaseTag;
	/* The duration of the game and period by which to update player scores. */
	protected long mDuration, mUpdatePeriod;
	/* The maximum score achievable in this game phase. */
	protected int mMaxScore;
	protected Player mPlayerOne, mPlayerTwo;
	protected Engine mEngine;
	
	public GamePhase(Player playerOne, Player playerTwo, Engine engine) {
		mEngine = engine;
		
		mPlayerOne = playerOne;
		mPlayerOne.setCurrentScore(0);
		
		mPlayerTwo = playerTwo;
		mPlayerTwo.setCurrentScore(0);
	}
	
	/** Play this game phase. A phase plays by simply requesting Galileo score
	 *  data periodically.
	 */
	public void play() throws InterruptedException {
		LedMatrix ledMatrix = mEngine.getLedMatrix();
		
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < mDuration) {
			Thread.sleep(mUpdatePeriod);
			incrementPlayerScores();
			
			mEngine.getMainFrame().update(mPlayerOne, mPlayerTwo, mEngine.getMaxScore());
			
			ledMatrix.clear();
			ledMatrix.drawTextCentered(String.valueOf(this.getPhaseTag()), 16, 1);
			ledMatrix.drawProgressBars(mPlayerOne.getCurrentScore() / mEngine.getMaxScore(), 
	                   mPlayerTwo.getCurrentScore() / mEngine.getMaxScore(), 
	                   Engine.COLOR_PLAYER_1, Engine.COLOR_PLAYER_2);
		}
	}
	
	/**
	 * @return the relevant player score from the Galileo board.
	 */
	public abstract float getScoreFromGalileo(Player player);
	
	/** Update the two Player's current score field based on current performance.
	 */
	public void incrementPlayerScores() {
		float playerOneScore = getScoreFromGalileo(mPlayerOne);
		float playerTwoScore = getScoreFromGalileo(mPlayerTwo);
		float[] normalizedScores = normalizeScores(playerOneScore, playerTwoScore);
		
		mPlayerOne.incrementCurrentScore((int) (normalizedScores[0]
						                         * getIncrementWeight()));
		
		mPlayerTwo.incrementCurrentScore((int) (normalizedScores[1]
                                                 * getIncrementWeight()));
	}
	
	/**
	 * @return The PhaseTag representing this game phase.
	 */
	public PhaseTag getPhaseTag() {
		return mPhaseTag;
	}
	
	/**
	 * @return The duration in millis of this game phase.
	 */
	public long getDuration() {
		return mDuration;
	}
	
	/**
	 * @return The period in millis to periodically update the player scores.
	 */
	public long getUpdatePeriod() {
		return mUpdatePeriod;
	}
	
	/**
	 * @return The maximum achievable score of this game phase.
	 */
	public int getMaxScore() {
		return mMaxScore;
	}
	
	/** 
	 * @return the maximum possible score achievable during one update increment.
	 */
	public float getIncrementWeight() {
		return (float) getUpdatePeriod() / (float) getDuration()
	          * (float) getMaxScore();
	}
	
	/** Returns a 2-array of normalized player scores such that the larger is 1.0
	 * and the other is percentage of the larger.
	 * 
	 *  E.g. normalizeScores(100, 50) => [1.0, 0.5]
	 * 
	 * @param score1 The score of the first player.
	 * @param score2 The score of the second player.
	 * @return The normalized scores.
	 */
	public static float[] normalizeScores(float score1, float score2) {
//		System.out.println("Normalizing: " + score1 + ", " + score2);
		
		if (score1 == score2) {
			return new float[]{0.0f, 0.0f};
	    } else if (score1 > score2) {
			return new float[]{1.0f, score2 / score1};
		} else {
			return new float[]{score1 / score2, 1.0f};
		}
	}
	
	/**
	 * @param tag the PhaseTag of the desired GamePhase.
	 * @return The GamePhase representative of the TAG.
	 */
	public static GamePhase makeGamePhase(PhaseTag tag, Player playerOne,
											Player playerTwo, Engine engine) {
		switch (tag) {
			case ATTENTION: 
				return new AttentionGamePhase(playerOne, playerTwo, engine);
			case MEDITATE: 
				return new MeditateGamePhase(playerOne, playerTwo, engine);
			case HEARTRATE_HIGH:
				return new HeartRateHighGamePhase(playerOne, playerTwo, engine);
			case HEARTRATE_LOW:
				return new HeartRateLowGamePhase(playerOne, playerTwo, engine);
			case PRESSURE:
				return new PressureGamePhase(playerOne, playerTwo, engine);
			case SQUEEZE:
				return new SqueezeGamePhase(playerOne, playerTwo, engine);
			default:
				return null;
		}
	}
}
