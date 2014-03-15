package model.gamePhases;

import controller.GalileoInterfacer;

public class PressureGamePhase extends GamePhase {

	public PressureGamePhase() {
		this.mPhaseTag = PhaseTag.PRESSURE;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getPressureScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
