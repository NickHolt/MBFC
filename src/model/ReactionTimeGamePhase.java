package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class ReactionTimeGamePhase extends GamePhase {

	public ReactionTimeGamePhase(Player playerOne, Player playerTwo,
            GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(playerOne, playerTwo, galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.REACTION_TIME;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mGalileoInterfacer.getReactionTimeScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		mMainFrame.putText("React!");
	}

}
