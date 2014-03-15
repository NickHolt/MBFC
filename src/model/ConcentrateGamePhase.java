package model;

import controller.GalileoInterfacer;


public class ConcentrateGamePhase extends GamePhase {

	public ConcentrateGamePhase(GalileoInterfacer galileoInterfacer) {
		this.mPhaseTag = PhaseTag.CONCENTRATE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		this.mGalileoInterfacer = galileoInterfacer;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getConcentrationScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
