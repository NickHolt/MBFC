package model;

import controller.Engine;


public class HeartRateHighGamePhase extends GamePhase {

	public HeartRateHighGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_HIGH;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return 0.0f;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		mEngine.getMainFrame().putText("High Heart Rate!");
		
	}

}
