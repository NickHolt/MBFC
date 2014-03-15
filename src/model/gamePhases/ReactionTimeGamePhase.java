package model.gamePhases;

import controller.GalileoInterfacer;

public class ReactionTimeGamePhase extends GamePhase {

	public ReactionTimeGamePhase() {
		this.mPhaseTag = PhaseTag.REACTION_TIME;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getReactionTimeScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
