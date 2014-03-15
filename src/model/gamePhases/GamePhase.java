package model.gamePhases;

import model.PhaseTag;

public abstract class GamePhase {
	/**
	 * @return The PhaseTag representing this game phase.
	 */
	public abstract PhaseTag getPhaseTag();
	
	/**
	 * @return The duration in millis of this game phase.
	 */
	public abstract long getDuration();
	
	/**
	 * @return The period in millis to periodically update the player scores.
	 */
	public abstract long getUpdatePeriod();
	
	/**
	 * @return The maximum achievable score of this game phase.
	 */
	public abstract int getMaxScore();
	
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
		switch (tag) {
			case CONCENTRATE: 
				return new ConcentrateGamePhase();
			case MEDITATE: 
				return new MeditateGamePhase();
			case HEARTRATE_HIGH:
				return new HeartRateHighGamePhase();
			case HEARTRATE_LOW:
				return new HeartRateLowGamePhase();
			case KEEP_THE_BEAT:
				return new KeepTheBeatGamePhase();
			default:
				return null;
		}
	}
}
