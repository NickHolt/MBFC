package model;

import controller.GalileoInterfacer;

public class HeartRateHighGamePhase extends GamePhase {

	public HeartRateHighGamePhase() {
		this.mPhaseTag = PhaseTag.HEARTRATE_HIGH;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getHeartbeatHighScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
