package model;

import view.LedMatrix;
import controller.Engine;
import controller.GalileoInterfacer;

public class SqueezeGamePhase extends GamePhase {
	private float mMaxPlayerOnePressure;
	private float mMaxPlayerTwoPressure;
	
	public SqueezeGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.SQUEEZE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 100;
		this.mMaxScore = 10000;
		this.mMaxPlayerOnePressure = mMaxPlayerTwoPressure = 0;
		// TODO
	}
	
	@Override
	public void play() throws InterruptedException {
		mEngine.getMainFrame().putText("Squeeze hard!");
		super.play();
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		float currentPressure;
		if(player.equals(mPlayerOne)) {
			currentPressure = mEngine.getPlayerOneGalileoInterfacer().getPressureValue();
			mMaxPlayerOnePressure = Math.max(mMaxPlayerOnePressure, currentPressure);
			return mMaxPlayerOnePressure;
		} else {
			currentPressure = mEngine.getPlayerTwoGalileoInterfacer().getPressureValue();
			mMaxPlayerTwoPressure = Math.max(mMaxPlayerTwoPressure, currentPressure);
			return mMaxPlayerTwoPressure;
		}
	}

}
