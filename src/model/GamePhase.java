package model;

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
	
	/** Play this game phase.
	 */
	public abstract void play() throws InterruptedException;
	
	/**
	 * @return the relevant player score from the Galileo board.
	 */
	public abstract float getScoreFromGalileo(Player player);
	
	public void incrementPlayerScores() {
		mPlayerOne.incrementCurrentScore((int) (getScoreFromGalileo(mPlayerOne)
						                         * getIncrementWeight()));
		
		mPlayerTwo.incrementCurrentScore((int) (getScoreFromGalileo(mPlayerTwo)
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
	
	/**
	 * @param tag the PhaseTag of the desired GamePhase.
	 * @return The GamePhase representative of the TAG.
	 */
	public static GamePhase makeGamePhase(PhaseTag tag,
											Player playerOne,
											Player playerTwo,
			                                Engine engine) {
		// TODO finish this
		switch (tag) {
			case CONCENTRATE: 
				return new ConcentrateGamePhase(playerOne, playerTwo, engine);
			case MEDITATE: 
				return new MeditateGamePhase(playerOne, playerTwo, engine);
			case HEARTRATE_HIGH:
				return new HeartRateHighGamePhase(playerOne, playerTwo, engine);
			case HEARTRATE_LOW:
				return new HeartRateLowGamePhase(playerOne, playerTwo, engine);
			case BEAT:
				return new BeatGamePhase(playerOne, playerTwo, engine);
			case REACTION_TIME:
				return new ReactionTimeGamePhase(playerOne, playerTwo, engine);
			case PRESSURE:
				return new PressureGamePhase(playerOne, playerTwo, engine);
			default:
				return null;
		}
	}
}
