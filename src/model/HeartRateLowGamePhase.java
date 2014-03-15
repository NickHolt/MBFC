package model;


public class HeartRateLowGamePhase extends GamePhase {

	public HeartRateLowGamePhase() {
		this.mPhaseTag = PhaseTag.HEARTRATE_LOW;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getHeartbeatLowScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
