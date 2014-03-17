package model;

import java.util.Random;

import controller.Engine;


public class PressureGamePhase extends GamePhase {
	public static final float[] PRESSURE_RANGE = new float[]{200.0f, 800.0f};
	private float mTargetPressure;

	public PressureGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.PRESSURE;
		this.mDuration = 20000;
		this.mUpdatePeriod = 5000;
		this.mMaxScore = 10000;
		// TODO
	}
	
	@Override
	public void play() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Random rand = new Random(System.currentTimeMillis());
		
		mEngine.getMainFrame().putText("Match the pressure!");
		
		while(System.currentTimeMillis() - startTime < mDuration) {
			mTargetPressure = rand.nextFloat() * (PRESSURE_RANGE[1] - PRESSURE_RANGE[0]) +
					          PRESSURE_RANGE[0];
			//TODO display target pressure
			Thread.sleep(mUpdatePeriod);
			incrementPlayerScores();
//			mEngine.getLEDGalileoInterfacer().writeToGalileo("P1=" + mPlayerOne.getGlobalScore());
//			mEngine.getLEDGalileoInterfacer().writeToGalileo("P2=" + mPlayerTwo.getGlobalScore());
		}
	}
	
	@Override
	public void incrementPlayerScores() {
		float playerOneScore = Math.abs(1 / (getScoreFromGalileo(mPlayerOne) - mTargetPressure + 1));
		float playerTwoScore = Math.abs(1 / (getScoreFromGalileo(mPlayerTwo) - mTargetPressure + 1));
		float[] normalizedScores = normalizeScores(playerOneScore, playerTwoScore);
		
		mPlayerOne.incrementCurrentScore((int) (normalizedScores[0]
						                         * getIncrementWeight()));
		
		mPlayerTwo.incrementCurrentScore((int) (normalizedScores[1]
                                                 * getIncrementWeight()));
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		if(player.equals(mPlayerOne)) {
			return mEngine.getPlayerOneGalileoInterfacer().getPressureValue();
		} else {
			return mEngine.getPlayerTwoGalileoInterfacer().getPressureValue();
		}
	}

}
