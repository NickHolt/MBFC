package model;

import controller.Engine;


public class BeatGamePhase extends GamePhase {
	
	public BeatGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.BEAT;
		this.mDuration = 15000;
		this.mUpdatePeriod = 500;
		this.mMaxScore = 10000;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		if(player.equals(mPlayerOne)) {
			return mEngine.getPlayerOneGalileoInterfacer().getBeatValue();
		} else {
			return mEngine.getPlayerTwoGalileoInterfacer().getBeatValue();
		}
	}
}
