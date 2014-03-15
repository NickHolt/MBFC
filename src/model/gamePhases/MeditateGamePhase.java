package model.gamePhases;

import controller.GalileoInterfacer;
import model.Player;

public class MeditateGamePhase extends GamePhase {

	public MeditateGamePhase() {
		this.mPhaseTag = PhaseTag.MEDITATE;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getMeditationScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
