package model.gamePhases;

import controller.GalileoInterfacer;
import model.Player;

public class ConcentrateGamePhase extends GamePhase {

	public ConcentrateGamePhase() {
		this.mPhaseTag = PhaseTag.CONCENTRATE;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getConcentrationScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
