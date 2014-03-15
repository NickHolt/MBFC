package controller;

import model.GamePhase;
import model.PhaseTag;
import view.MainFrame;
import model.Player;

/** The Engine controlling the software behind a game of Mind Body Fitness Challenge. It is the Engine's
 * job to maintain state such as player scores, and orchestrate the different phases of the game.
 * 
 * @author nickholt
 */
public class Engine {
	/* The two players of this game. */
	private Player mPlayerOne, mPlayerTwo;
	/* The ordered phases that the game will progress through. */
	private GamePhase[] mGamePhases;
	/* The interface between the game code and the Galileo Board. */
	private GalileoInterfacer mGalileoInterfacer;
	/* The main GUI component through which all view updates are done. */
	private MainFrame mMainFrame;
	
	public Engine() {
		mGalileoInterfacer = new GalileoInterfacer();
		
		mMainFrame = new MainFrame();
		
		PhaseTag[] phaseTags = PhaseTag.values();
		mGamePhases = new GamePhase[phaseTags.length];
		int maxScore = 0;
		// Generate game phases
		for (int i = 0; i < mGamePhases.length; i++) {
			mGamePhases[i] = GamePhase.makeGamePhase(phaseTags[i], mPlayerOne, mPlayerTwo, 
					                                 mGalileoInterfacer, mMainFrame);
			maxScore += mGamePhases[i].getMaxScore();
		}
		
		mPlayerOne = new Player(maxScore);
		mPlayerTwo = new Player(maxScore);
	}
	
	/** Run a game of Mind Body Fitness Challenge. This method alone need be called to initiate
	 *  and play a game.
	 */
	public void run() {
		try {
			mMainFrame.initialize();
			welcome();
			/* Run the game phases */
			for (GamePhase currentPhase : mGamePhases) {
				currentPhase.play();
			}
			conclude();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** Displays a welcome message and counts down from 3.
	 */
	private void welcome() throws InterruptedException {
		mMainFrame.putText("Welcome!");
		Thread.sleep(2000);
		for (int i = 3; i > 0; i--) {
			mMainFrame.putText(String.valueOf(i));
			Thread.sleep(1000);
		}
	}
	
	/** Display the player scores and declare the winner.
	 */
	private void conclude() throws InterruptedException {
		mMainFrame.putText("Game over!");
		Thread.sleep(2000);
		mMainFrame.putText("Player 1 Score: " + mPlayerOne.getGlobalScore()
				+ " Player 2 Score: " + mPlayerTwo.getGlobalScore());
		Thread.sleep(2000);
		mMainFrame.putText("Player " + 
				(mPlayerOne.getGlobalScore() > mPlayerTwo.getGlobalScore() ? "1" : "2") + " wins!");
	}
	
	/** Entry point for a new game of Mind Body Fitness Challenge.
	 */
	public static void main(String[] args) {
		new Engine().run();
	}
}
