package model;

import controller.GalileoInterfacer;

public class BeatGamePhase extends GamePhase {
	
	public BeatGamePhase() {
		this.mPhaseTag = PhaseTag.BEAT;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getBeatScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
