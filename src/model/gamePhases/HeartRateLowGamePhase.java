package model.gamePhases;

import controller.GalileoInterfacer;
import model.Player;

public class HeartRateLowGamePhase extends GamePhase {

	public HeartRateLowGamePhase() {
		this.mPhaseTag = PhaseTag.HEARTRATE_LOW;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player, GalileoInterfacer galileoInterfacer) {
		return galileoInterfacer.getHeartbeatLowScore(player);
	}

	@Override
	public void prompt(GalileoInterfacer galileoInterfacer) {
		// TODO Auto-generated method stub
		
	}

}
