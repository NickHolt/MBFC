package model;

import controller.GalileoInterfacer;


public class PressureGamePhase extends GamePhase {

	public PressureGamePhase(GalileoInterfacer galileoInterfacer) {
		this.mPhaseTag = PhaseTag.PRESSURE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		this.mGalileoInterfacer = galileoInterfacer;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getPressureScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
