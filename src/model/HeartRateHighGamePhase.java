package model;

import controller.Engine;


public class HeartRateHighGamePhase extends GamePhase {

	public HeartRateHighGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_HIGH;
		this.mDuration = 30000;
		this.mUpdatePeriod = 200;
		this.mMaxScore = 15000;
		// TODO
	}
	
	@Override
	public void play() throws InterruptedException {
		mEngine.getMainFrame().putText("Raise your heart rate!");
		super.play();
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		if(player.equals(mPlayerOne)) {
			return mEngine.getPlayerOneGalileoInterfacer().getHeartRateValue();
		} else {
			return mEngine.getPlayerTwoGalileoInterfacer().getHeartRateValue();
		}
	}

}
