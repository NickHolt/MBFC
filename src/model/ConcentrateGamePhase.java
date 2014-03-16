package model;

import view.MainFrame;
import controller.Engine;


public class ConcentrateGamePhase extends GamePhase {

	public ConcentrateGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.CONCENTRATE;
		this.mDuration = 5000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mEngine.getGalileoInterfacer().getConcentrationScore(player);
	}

	@Override
	public void play() throws InterruptedException {
		// example with test GUI:
		// * display prompt through mainframe
		// * every update period, read score from Galileo 
		// * use increment weight to update player scores
		// * use engine's global max score to update mainframe progress bar
		
		// without GUI is easier:
		// * tell Galileo to start minigame routine
		// * periodically check it for values
		
		MainFrame mainFrame = mEngine.getMainFrame();
		mainFrame.putText("Concentrate!");
		
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < mDuration) {
			Thread.sleep(mUpdatePeriod);
			//incrementPlayerScores();
			mPlayerOne.incrementCurrentScore(100);
			mainFrame.update(mPlayerOne, mPlayerTwo, mEngine.getMaxScore());
		}
	}

}
