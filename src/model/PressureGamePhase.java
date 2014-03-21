package model;

import java.util.Random;

import view.LedMatrix;
import controller.Engine;


public class PressureGamePhase extends GamePhase {
	private float mTargetPressure;
	private long mRandomizeInterval = 5000;//milliseconds
	
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
		
		float targetPressure = 0.5f;
		float targetPressureVelocity = 0.0f;
		float targetPressureAcceleration = 0.0f;
		long prevMillis = System.currentTimeMillis();
		long nextRandomizeMillis = prevMillis;
		while(System.currentTimeMillis() - startTime < mDuration) {
			long currentMillis = System.currentTimeMillis();
			float timestep = (currentMillis - prevMillis) / 1000f;
			
			if(currentMillis >= nextRandomizeMillis) {
				targetPressureAcceleration = (rand.nextFloat() - 0.5f) * 5.0f;
			}
			
			targetPressureAcceleration -= 2.0f * (targetPressure - 0.5f);
			targetPressureVelocity += targetPressureAcceleration * timestep;
			targetPressure += targetPressureVelocity * timestep;
			mTargetPressure = Math.min(1.0f, Math.max(0.0f, targetPressure));
			
			mEngine.getMainFrame().putText("Match the pressure: " + 100 * mTargetPressure + "%");
			mEngine.getMainFrame().update(mPlayerOne, mPlayerTwo, mEngine.getMaxScore());
			
			ledMatrix.clear();
			ledMatrix.fillRect(0, 0, Math.round(LedMatrix.WIDTH * mTargetPressure), 4, LedMatrix.COLOR_CYAN);
			
//			ledMatrix.drawProgressBars(mPlayerOne.getCurrentScore() / mEngine.getMaxScore(), 
//					                   mPlayerTwo.getCurrentScore() / mEngine.getMaxScore(), 
//					                   Engine.COLOR_PLAYER_1, Engine.COLOR_PLAYER_2);
			
			incrementPlayerScores();
			
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
