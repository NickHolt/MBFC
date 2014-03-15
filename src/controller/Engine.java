package controller;

import model.*;
import model.gamePhases.GamePhase;
import model.gamePhases.PhaseTag;
import view.*;

public class Engine {
	private static final int PLAYER_ONE = 0, PLAYER_TWO = 1;
	
	private GamePhase[] mGamePhases;
	private int mPlayerOneScore, mPlayerTwoScore;
	private GalileoInterfacer mGalileoInterfacer;
	private MainFrame mMainFrame;
	
	public Engine() {
		PhaseTag[] phaseTags = PhaseTag.values();
		mGamePhases = new GamePhase[phaseTags.length];
		for (int i = 0; i < mGamePhases.length; i++) {
			mGamePhases[i] = GamePhase.makeGamePhase(phaseTags[i]);
		}
		
		mPlayerOneScore = 0;
		mPlayerTwoScore = 0;
		
		mGalileoInterfacer = new GalileoInterfacer();
		
		mMainFrame = new MainFrame();
	}
	
	public void run() {
		try {
			constructGUI();
			calibrate();
			welcome();
			runMainGame();
			conclude();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void constructGUI() {
		// TODO
	}
	
	public void calibrate() throws InterruptedException {
		// TODO
	}
	
	/** Displays a welcome message and counts down from 3.
	 * @throws InterruptedException 
	 */
	public void welcome() throws InterruptedException {
		mMainFrame.putText("WELCOME");
		Thread.sleep(2000);
		for (int i = 3; i > 0; i--) {
			mMainFrame.putText(String.valueOf(i));
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
				mPlayerOneScore += 
						   (int) (currentPhase.getScoreFromGalileo(PLAYER_ONE, mGalileoInterfacer)
						   * currentPhase.getIncrementWeight());
				mPlayerTwoScore +=
						   (int) (currentPhase.getScoreFromGalileo(PLAYER_TWO, mGalileoInterfacer)
			               * currentPhase.getIncrementWeight());
				
				Thread.sleep(currentPhase.getUpdatePeriod()); // wait for next update period
				
				currTime = System.currentTimeMillis();
			}
		}
	}
	
	public void conclude() throws InterruptedException {
		mMainFrame.putText("Player 1 Score: " + mPlayerOneScore);
		Thread.sleep(3000);
		mMainFrame.putText("Player 2 Score: " + mPlayerTwoScore);
		Thread.sleep(3000);
		mMainFrame.putText("Player " + (mPlayerOneScore > mPlayerTwoScore ? "1" : "2") + " wins!");
		Thread.sleep(10000);
	}

}
