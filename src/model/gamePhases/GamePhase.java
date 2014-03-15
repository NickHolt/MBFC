package model.gamePhases;

import model.PhaseTag;

public abstract class GamePhase {
	public abstract PhaseTag getPhaseTag();
	public abstract int getDuration();
	public abstract int getUpdatePeriod();
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
