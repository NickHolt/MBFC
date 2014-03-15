package model;

public abstract class GamePhase {
	public abstract PhaseTag getPhaseTag();
	public abstract int getDuration();
	public abstract int getMaxScore();
	
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
			default:
				return null;
		}
	}
}
