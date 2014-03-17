package model;

import java.util.Random;

import view.LedMatrix;
import controller.Engine;


public class PressureGamePhase extends GamePhase {
	public static final float[] PRESSURE_RANGE = new float[]{0.0f, 1000.0f};
	private float mTargetPressure;

	public PressureGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.PRESSURE;
		this.mDuration = 30000;
		this.mUpdatePeriod = 5000;
		this.mMaxScore = 15000;
		// TODO
	}
	
	@Override
	public void play() throws InterruptedException {
		LedMatrix ledMatrix = mEngine.getLedMatrix();
		
		long startTime = System.currentTimeMillis();
		Random rand = new Random(System.currentTimeMillis());
		
		while(System.currentTimeMillis() - startTime < mDuration) {
			mTargetPressure = rand.nextFloat() * (PRESSURE_RANGE[1] - PRESSURE_RANGE[0]) +
					          PRESSURE_RANGE[0];
			mEngine.getMainFrame().putText("Match the pressure: " + 100 * (mTargetPressure / 1000.0f) + "%");
//			ledMatrix.clear();
//			ledMatrix.fillRect(0, 0, Math.round(LedMatrix.WIDTH * mTargetPressure / 1000f), 4, LedMatrix.COLOR_CYAN);
//			
//			ledMatrix.fillRect(0, 12, LedMatrix.WIDTH * mPlayerOne.getGlobalScore() / mEngine.getMaxScore(), 2, Engine.COLOR_PLAYER_1);
//			ledMatrix.fillRect(0, 14, LedMatrix.WIDTH * mPlayerTwo.getGlobalScore() / mEngine.getMaxScore(), 2, Engine.COLOR_PLAYER_2);
			
			Thread.sleep(mUpdatePeriod);
			incrementPlayerScores();
			mEngine.getMainFrame().update(mPlayerOne, mPlayerTwo, mEngine.getMaxScore());
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
		mEngine.getMainFrame().update(mPlayerOne, mPlayerTwo, mEngine.getMaxScore());
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
