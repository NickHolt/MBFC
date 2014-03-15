package model;


public class PressureGamePhase extends GamePhase {

	public PressureGamePhase() {
		this.mPhaseTag = PhaseTag.PRESSURE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
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
