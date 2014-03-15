package controller;

import model.GamePhase;
import model.PhaseTag;
import view.MainFrame;

/** The Engine controlling the software behind a game of Mind Body Fitness Challenge. It is the Engine's
 * job to maintain state such as player scores, and orchestrate the different phases of the game.
 * 
 * @author nickholt
 */
public class Engine {
	/* Constants representing the identities of both players. */
	public static final int PLAYER_ONE = 0, PLAYER_TWO = 1;
	
	/* The ordered phases that the game will progress through. */
	private GamePhase[] mGamePhases;
	/* The global scores of the two players. */
	private int mPlayerOneScore, mPlayerTwoScore;
	/* The interface between the game code and the Galileo Board. */
	private GalileoInterfacer mGalileoInterfacer;
	/* The main GUI component through which all view updates are done. */
	private MainFrame mMainFrame;
	
	public Engine() {
		PhaseTag[] phaseTags = PhaseTag.values();
		mGamePhases = new GamePhase[phaseTags.length];
		for (int i = 0; i < mGamePhases.length; i++) {
			mGamePhases[i] = GamePhase.makeGamePhase(phaseTags[i], mGalileoInterfacer);
		}
		
		mPlayerOneScore = 0;
		mPlayerTwoScore = 0;
		
		mGalileoInterfacer = new GalileoInterfacer();
		
		mMainFrame = new MainFrame();
	}
	
	/** Run a game of Mind Body Fitness Challenge. This method alone need be called to initiate
	 *  and play a game.
	 */
	public void run() {
		try {
			constructGUI();
			welcome();
			runMainGame();
			conclude();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void constructGUI() {
		// TODO
	}
	
	/** Displays a welcome message and counts down from 3.
	 */
	private void welcome() throws InterruptedException {
		mMainFrame.putText("WELCOME");
		Thread.sleep(2000);
		for (int i = 3; i > 0; i--) {
			mMainFrame.putText(String.valueOf(i));
			Thread.sleep(1000);
		}
	}
	
	/** Cycles through the game phases and plays each individually.
	 *  Global scores are updated at the end of each phase. 
	 */
	private void runMainGame() throws InterruptedException {
		for (GamePhase currentPhase : mGamePhases) {
			currentPhase.play();
			mPlayerOneScore += currentPhase.getPlayerOneScore();
			mPlayerTwoScore += currentPhase.getPlayerTwoScore();
		}
	}
	
	/** Display the player scores and declare the winner.
	 */
	private void conclude() throws InterruptedException {
		mMainFrame.putText("Game over!");
		Thread.sleep(3000);
		mMainFrame.putText("Player 1 Score: " + mPlayerOneScore);
		Thread.sleep(3000);
		mMainFrame.putText("Player 2 Score: " + mPlayerTwoScore);
		Thread.sleep(3000);
		mMainFrame.putText("Player " + (mPlayerOneScore > mPlayerTwoScore ? "1" : "2") + " wins!");
		Thread.sleep(10000);
	}
	
	/** Entry point for a new game of Mind Body Fitness Challenge.
	 */
	public static void main(String[] args) {
		new Engine().run();
	}
}
