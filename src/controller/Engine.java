package controller;

import model.*;
import view.*;

public class Engine {
	private GamePhase[] mGamePhases;
	private Player mPlayerOne, mPlayerTwo;
	private MainFrame mMainFrame;
	private ProgressBar mProgressBarOne, mProgressBarTwo;
	
	public Engine() {
		PhaseTag[] phaseTags = PhaseTag.values();
		mGamePhases = new GamePhase[phaseTags.length];
		for (int i = 0; i < mGamePhases.length; i++) {
			mGamePhases[i] = GamePhase.makeGamePhase(phaseTags[i]);
		}
		
		mPlayerOne = new Player();
		mPlayerTwo = new Player();
	}
	
	public void run() {
		constructGUI();
		welcome();
		runMainGame();
		conclude();
	}
	
	public void constructGUI() {
		// TODO
	}
	
	public void welcome() {
		// TODO 
	}
	
	public void runMainGame() {
		// TODO 
	}
	
	public void conclude() {
		// TODO 
	}

}
