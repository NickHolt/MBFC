package model;

import controller.Engine;


public class HeartRateLowGamePhase extends GamePhase {

	public HeartRateLowGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_LOW;
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
		mEngine.getMainFrame().putText("Low Heart Rate!");
		
	}

}
