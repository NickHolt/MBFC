package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class HeartRateHighGamePhase extends GamePhase {

	public HeartRateHighGamePhase(GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.HEARTRATE_HIGH;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getHeartbeatHighScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
