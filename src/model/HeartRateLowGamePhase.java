package model;

import controller.GalileoInterfacer;


public class HeartRateLowGamePhase extends GamePhase {

	public HeartRateLowGamePhase(GalileoInterfacer galileoInterfacer) {
		this.mPhaseTag = PhaseTag.HEARTRATE_LOW;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		this.mGalileoInterfacer = galileoInterfacer;
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
