package model;

import controller.Engine;

public class SqueezeGamePhase extends GamePhase {
	private float mMaxPressure;
	
	public SqueezeGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.SQUEEZE;
		this.mDuration = 5000;
		this.mUpdatePeriod = 100;
		this.mMaxScore = 5000;
		this.mMaxPressure = 0;
		// TODO
	}
	
	@Override
	public void play() throws InterruptedException {
		mEngine.getMainFrame().putText("Squeeze Hard!");
		super.play();
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		float currentPressure;
		if(player.equals(mPlayerOne)) {
			currentPressure = mEngine.getPlayerOneGalileoInterfacer().getPressureValue();
		} else {
			currentPressure = mEngine.getPlayerTwoGalileoInterfacer().getPressureValue();
		}
		
		mMaxPressure = Math.max(mMaxPressure, currentPressure);
		return mMaxPressure;
	}

}
