package model;

import controller.GalileoInterfacer;


public class HeartRateHighGamePhase extends GamePhase {

	public HeartRateHighGamePhase(GalileoInterfacer galileoInterfacer) {
		this.mPhaseTag = PhaseTag.HEARTRATE_HIGH;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		this.mGalileoInterfacer = galileoInterfacer;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getHeartbeatHighScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
