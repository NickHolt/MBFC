package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class HeartRateHighGamePhase extends GamePhase {

	public HeartRateHighGamePhase(Player playerOne, Player playerTwo,
            GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(playerOne, playerTwo, galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_HIGH;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mGalileoInterfacer.getHeartbeatHighScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		mMainFrame.putText("High Heart Rate!");
		
	}

}
