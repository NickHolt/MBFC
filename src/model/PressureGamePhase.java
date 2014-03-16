package model;

import controller.Engine;


public class PressureGamePhase extends GamePhase {

	public PressureGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.PRESSURE;
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
		
	}

}
