package model;

import controller.Engine;
import controller.GalileoInterfacer;

public abstract class GamePhase {
	protected PhaseTag mPhaseTag;
	protected long mDuration, mUpdatePeriod;
	protected int mMaxScore, mPlayerOneScore, mPlayerTwoScore;
	protected GalileoInterfacer mGalileoInterfacer;
	
	/** Play this game phase.
	 */
	public abstract void play();
	
	/**
	 * @return the relevant player score from the Galileo board.
	 */
	public abstract float getScoreFromGalileo(int player);
	
	public void updatePlayerScores() {
		mPlayerOneScore += 
				   (int) (getScoreFromGalileo(Engine.PLAYER_ONE)
				   * getIncrementWeight());
		mPlayerTwoScore +=
				   (int) (getScoreFromGalileo(Engine.PLAYER_TWO)
	               * getIncrementWeight());
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
	 * @return player one's current score in this game phase.
	 */
	public int getPlayerOneScore() {
		return mPlayerOneScore;
	}
	
	/**
	 * @return player two's current score in this game phase.
	 */
	public int getPlayerTwoScore() {
		return mPlayerTwoScore;
	}
	
	/** 
	 * @return the weight by which to multiply the player's score on each
	 * update.
	 */
	public float getIncrementWeight() {
		return (float) getUpdatePeriod() / (float) getDuration()
	          * (float) getMaxScore();
	}
	
	/**
	 * @param tag the PhaseTag of the desired GamePhase.
	 * @return The GamePhase representative of the TAG.
	 */
	public static GamePhase makeGamePhase(PhaseTag tag, GalileoInterfacer galileoInterfacer) {
		// TODO finish this
		switch (tag) {
			case CONCENTRATE: 
				return new ConcentrateGamePhase(galileoInterfacer);
			case MEDITATE: 
				return new MeditateGamePhase(galileoInterfacer);
			case HEARTRATE_HIGH:
				return new HeartRateHighGamePhase(galileoInterfacer);
			case HEARTRATE_LOW:
				return new HeartRateLowGamePhase(galileoInterfacer);
			case BEAT:
				return new BeatGamePhase(galileoInterfacer);
			case REACTION_TIME:
				return new ReactionTimeGamePhase(galileoInterfacer);
			case PRESSURE:
				return new PressureGamePhase(galileoInterfacer);
			default:
				return null;
		}
	}
}
