package model.gamePhases;

import controller.GalileoInterfacer;

public class MeditateGamePhase extends GamePhase {

	public MeditateGamePhase() {
		this.mPhaseTag = PhaseTag.MEDITATE;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getMeditationScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
