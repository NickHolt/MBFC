package model;


public class BeatGamePhase extends GamePhase {
	
	public BeatGamePhase() {
		this.mPhaseTag = PhaseTag.BEAT;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getBeatScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}


}
