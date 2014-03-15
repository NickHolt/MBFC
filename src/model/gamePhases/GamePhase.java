package model.gamePhases;

import model.Player;
import controller.*;

public abstract class GamePhase {
	protected PhaseTag mPhaseTag;
	protected long mDuration, mUpdatePeriod;
	protected int mMaxScore;
	
	/**
	 * @return the relevant player score from the Galileo board.
	 */
	public abstract float getScoreFromGalileo(Player player, GalileoInterfacer galileoInterfacer);
	
	/** Run the necessary prompt procedure on the Galileo when this phase begins.
	 */
	public abstract void prompt(GalileoInterfacer galileoInterfacer);
	
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
	 * @return the weight by which to multiply the player's score on each
	 * update.
	 */
	public float getIncrementWeight() {
		return (float) getUpdatePeriod() 
	          / (float) getDuration()
	          * (float) getMaxScore();
	}
	
	/**
	 * @param tag the PhaseTag of the desired GamePhase.
	 * @return The GamePhase representative of the TAG.
	 */
	public static GamePhase makeGamePhase(PhaseTag tag) {
		// TODO finish this
		switch (tag) {
			case CONCENTRATE: 
				return new ConcentrateGamePhase();
			case MEDITATE: 
				return new MeditateGamePhase();
			case HEARTRATE_HIGH:
				return new HeartRateHighGamePhase();
			case HEARTRATE_LOW:
				return new HeartRateLowGamePhase();
			case BEAT:
				return new BeatGamePhase();
			case REACTION_TIME:
				return new ReactionTimeGamePhase();
			case PRESSURE:
				return new PressureGamePhase();
			default:
				return null;
		}
	}
}
