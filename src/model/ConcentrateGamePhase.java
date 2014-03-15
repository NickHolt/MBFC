package model;


public class ConcentrateGamePhase extends GamePhase {

	public ConcentrateGamePhase() {
		this.mPhaseTag = PhaseTag.CONCENTRATE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
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
