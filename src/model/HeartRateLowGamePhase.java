package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class HeartRateLowGamePhase extends GamePhase {

	public HeartRateLowGamePhase(Player playerOne, Player playerTwo,
            GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(playerOne, playerTwo, galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_LOW;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mGalileoInterfacer.getHeartbeatLowScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		mMainFrame.putText("Low Heart Rate!");
		
	}

}
