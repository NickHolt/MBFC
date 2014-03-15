package controller;

import model.GamePhase;
import model.PhaseTag;
import view.MainFrame;

public class Engine {
	public static final int PLAYER_ONE = 0, PLAYER_TWO = 1;
	
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
			currentPhase.play();
			mPlayerOneScore += currentPhase.getPlayerOneScore();
			mPlayerTwoScore += currentPhase.getPlayerTwoScore();
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
	
	public static void main(String[] args) {
		new Engine().run();
	}
}
