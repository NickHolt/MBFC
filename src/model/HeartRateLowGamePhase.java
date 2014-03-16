package model;

import controller.Engine;


public class HeartRateLowGamePhase extends GamePhase {

	public HeartRateLowGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_LOW;
		this.mDuration = 20000;
		this.mUpdatePeriod = 200;
		this.mMaxScore = 20000;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		if(player.equals(mPlayerOne)) {
			return 1 / mEngine.getPlayerOneGalileoInterfacer().getHeartRateValue();
		} else {
			return 1 / mEngine.getPlayerTwoGalileoInterfacer().getHeartRateValue();
		}
	}

}
