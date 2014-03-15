package controller;

import model.*;
import model.gamePhases.GamePhase;
import view.*;

public class Engine {
	private GamePhase[] mGamePhases;
	private Player mPlayerOne, mPlayerTwo;
	private GalileoInterfacer mGalileoInterfacer;
	private MainFrame mMainFrame;
	private ProgressBar mProgressBarOne, mProgressBarTwo;
	private TextPrompt mTextPrompt;
	
	public Engine() {
		PhaseTag[] phaseTags = PhaseTag.values();
		mGamePhases = new GamePhase[phaseTags.length];
		for (int i = 0; i < mGamePhases.length; i++) {
			mGamePhases[i] = GamePhase.makeGamePhase(phaseTags[i]);
		}
		
		mPlayerOne = new Player();
		mPlayerTwo = new Player();
		
		mGalileoInterfacer = new GalileoInterfacer();
		
		mMainFrame = new MainFrame();
		mProgressBarOne = new ProgressBar();
		mProgressBarTwo = new ProgressBar();
		mTextPrompt = new TextPrompt();
	}
	
	public void run() {
		try {
			constructGUI();
			welcome();
			runMainGame();
			conclude();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void constructGUI() {
		// TODO
	}
	
	/** Displays a welcome message and counts down from 3.
	 * @throws InterruptedException 
	 */
	public void welcome() throws InterruptedException {
		mTextPrompt.putText("WELCOME");
		Thread.sleep(2000);
		for (int i = 3; i > 0; i--) {
			mTextPrompt.putText(String.valueOf(i));
			Thread.sleep(1000);
		}
	}
	
	public void runMainGame() throws InterruptedException {
		for (GamePhase currentPhase : mGamePhases) {
			long currTime, 
			      startTime = currTime = System.currentTimeMillis();

			// get scores periodically and update state
			while (currTime - startTime < currentPhase.getDuration()) {
				// [0.0, 1.0] player score is translated based on phase weight
				mPlayerOne.updateScore((int) (currentPhase.getScoreFromGalileo(mPlayerOne)
						               * currentPhase.getIncrementWeight()));
				mPlayerTwo.updateScore((int) (currentPhase.getScoreFromGalileo(mPlayerTwo)
			               * currentPhase.getIncrementWeight()));
				
				Thread.sleep(currentPhase.getUpdatePeriod()); // wait for next update period
				
				currTime = System.currentTimeMillis();
			}
		}
	}
	
	public void conclude() throws InterruptedException {
		int playerOneScore = mPlayerOne.getScore()
		    , playerTwoScore = mPlayerTwo.getScore();
		
		mTextPrompt.putText("Player 1 Score: " + playerOneScore);
		Thread.sleep(3000);
		mTextPrompt.putText("Player 2 Score: " + playerTwoScore);
		Thread.sleep(3000);
		mTextPrompt.putText("Player " + (playerOneScore > playerTwoScore ? "1" : "2") + " wins!");
		Thread.sleep(10000);
	}

}
