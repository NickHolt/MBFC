package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class ConcentrateGamePhase extends GamePhase {

	public ConcentrateGamePhase(Player playerOne, Player playerTwo,
            GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(playerOne, playerTwo, galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.CONCENTRATE;
		this.mDuration = 5000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mGalileoInterfacer.getConcentrationScore(player);
	}

	@Override
	public void play() throws InterruptedException {
		// TODO Auto-generated method stub
		mMainFrame.putText("Concentrate!");
		
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < mDuration) {
			mPlayerOneScore++;
			mPlayerTwoScore++;
			
			Thread.sleep(mUpdatePeriod);
		}
		
	}

}
