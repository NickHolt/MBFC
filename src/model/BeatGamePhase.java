package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class BeatGamePhase extends GamePhase {
	
	public BeatGamePhase(Player playerOne, Player playerTwo,
			              GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(playerOne, playerTwo, galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.BEAT;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mGalileoInterfacer.getBeatScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		mMainFrame.putText("Keep the Beat!");
	}
}
