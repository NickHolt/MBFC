package model;

import controller.GalileoInterfacer;


public class ReactionTimeGamePhase extends GamePhase {

	public ReactionTimeGamePhase(GalileoInterfacer galileoInterfacer) {
		this.mPhaseTag = PhaseTag.REACTION_TIME;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		this.mGalileoInterfacer = galileoInterfacer;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getReactionTimeScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
