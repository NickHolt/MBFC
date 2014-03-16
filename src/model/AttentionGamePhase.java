package model;

import controller.Engine;


public class AttentionGamePhase extends GamePhase {

	public AttentionGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.ATTENTION;
		this.mDuration = 20000;
		this.mUpdatePeriod = 200;
		this.mMaxScore = 15000;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		if(player.equals(mPlayerOne)) {
			return mEngine.getPlayerOneGalileoInterfacer().getAttentionValue();
		} else {
			return mEngine.getPlayerTwoGalileoInterfacer().getAttentionValue();
		}
	}
}
