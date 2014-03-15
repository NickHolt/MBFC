package model;

import controller.Engine;


public class BeatGamePhase extends GamePhase {
	
	public BeatGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.BEAT;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mEngine.getGalileoInterfacer().getBeatScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		mEngine.getMainFrame().putText("Keep the Beat!");
	}
}
