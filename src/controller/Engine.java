package controller;

import model.GamePhase;
import model.PhaseTag;
import model.Player;
import view.LedMatrix;
import view.MainFrame;

/** The Engine controlling the software behind a game of Mind Body Fitness Challenge. It is the Engine's
 * job to maintain state such as player scores, and orchestrate the different phases of the game.
 * 
 * @author nickholt
 */
public class Engine {
	/* The two players of this game. */
	private Player mPlayerOne, mPlayerTwo;
	/* The maximum achievable score in a full game. */
	private int mMaxScore;
	/* The ordered phases that the game will progress through. */
	private GamePhase[] mGamePhases;
	/* The interface between the game code and the Galileo Boards. */
	private GalileoInterfacer mPlayerOneGalileoInterfacer, mPlayerTwoGalileoInterfacer;
	/* The LED matrix */
	private LedMatrix mLedMatrix;
	/* The main GUI component through which all view updates are done. */
	private MainFrame mMainFrame;
	
	public static final int COLOR_PLAYER_1 = LedMatrix.COLOR_RED;
	public static final int COLOR_PLAYER_2 = LedMatrix.COLOR_BLUE;
	
	public Engine() {
		mPlayerOne = new Player();
		mPlayerTwo = new Player();
		
		mPlayerOneGalileoInterfacer = new GalileoInterfacer();
		mPlayerTwoGalileoInterfacer = new GalileoInterfacer();
		
		mLedMatrix = new LedMatrix();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mLedMatrix.setTextWrap(false);
		mLedMatrix.setTextColor(LedMatrix.COLOR_GRAY_1);
		
		mMainFrame = new MainFrame();
		
		PhaseTag[] phaseTags = PhaseTag.values();
		mGamePhases = new GamePhase[phaseTags.length];
		mMaxScore = 0;
		// Generate game phases
		for (int i = 0; i < mGamePhases.length; i++) {
			mGamePhases[i] = GamePhase.makeGamePhase(phaseTags[i], mPlayerOne, mPlayerTwo, this);
			mMaxScore += mGamePhases[i].getMaxScore();
		}
	}
	
	/** Run a game of Mind Body Fitness Challenge. This method alone need be called to initiate
	 *  and play a game.
	 */
	public void run() {
		try {
			mPlayerOneGalileoInterfacer.initialize();
			mPlayerTwoGalileoInterfacer.initialize();
			
			mMainFrame.initialize();
			
			//welcome();
			playMain();
			conclude();
			
			mLedMatrix.close();
			mPlayerOneGalileoInterfacer.close();
			mPlayerTwoGalileoInterfacer.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** Displays a welcome message and counts down from 3.
	 */
	private void welcome() throws InterruptedException {
		mMainFrame.putText("Welcome!");
		Thread.sleep(2000);
		for(int i = 3; i > 0; i--) {
			mMainFrame.putText(String.valueOf(i));
			Thread.sleep(1000);
		}
		
		
		mLedMatrix.fillScreen(LedMatrix.COLOR_BLACK);
		mLedMatrix.scroll("Welcome!", 4, 80);
	}
	
	/** Cycle through the game phases and play each while handling player scores.
	 */
	private void playMain() throws InterruptedException {
		for (GamePhase currentPhase : mGamePhases) {
			mLedMatrix.clear();
			mLedMatrix.setTextSize(1);
			mLedMatrix.scroll(currentPhase.getPhaseTag().toString().replace("_", " "), 4, 80);
			
			mLedMatrix.setTextSize(2);
			for(int i = 3; i > 0; i--) {
				mLedMatrix.clear();
				mLedMatrix.drawTextCentered(String.valueOf(i), 16, 1);
				Thread.sleep(1000);
			}
			
			currentPhase.play();
			// update global scores based on phase performance
			mPlayerOne.incrementGlobalScore(mPlayerOne.getCurrentScore());
			mPlayerTwo.incrementGlobalScore(mPlayerTwo.getCurrentScore());
		}
	}
	
	/** Alert LCD to the end of the game and report scores.
	 */
	private void conclude() throws InterruptedException {
		//TODO Conclude with LCD as well
		mMainFrame.putText("Game over!");
		Thread.sleep(2000);
		mMainFrame.putText("Player 1 Score: " + mPlayerOne.getGlobalScore()
				+ " Player 2 Score: " + mPlayerTwo.getGlobalScore());
		Thread.sleep(2000);
		mMainFrame.putText("Player " + 
				(mPlayerOne.getGlobalScore() > mPlayerTwo.getGlobalScore() ? "1" : "2") + " wins!"); 
	}

	public GalileoInterfacer getPlayerOneGalileoInterfacer() {
		return mPlayerOneGalileoInterfacer;
	}
	
	public GalileoInterfacer getPlayerTwoGalileoInterfacer() {
		return mPlayerTwoGalileoInterfacer;
	}
	
	
	public LedMatrix getLedMatrix() {
		return mLedMatrix;
	}
	
	public MainFrame getMainFrame() {
		return mMainFrame;
	}

	public int getMaxScore() {
		return mMaxScore;
	}

	public void setMaxScore(int maxScore) {
		mMaxScore = maxScore;
	}
	
	/** Entry point for a new game of Mind Body Fitness Challenge.
	 */
	public static void main(String[] args) {
		new Engine().run();
	}
}
