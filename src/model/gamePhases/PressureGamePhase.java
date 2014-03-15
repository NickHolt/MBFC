package model.gamePhases;

import controller.GalileoInterfacer;
import model.Player;

public class PressureGamePhase extends GamePhase {

	public PressureGamePhase() {
		this.mPhaseTag = PhaseTag.PRESSURE;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getPressureScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
