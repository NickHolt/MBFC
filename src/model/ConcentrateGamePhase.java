package model;

import view.MainFrame;
import controller.GalileoInterfacer;


public class ConcentrateGamePhase extends GamePhase {

	public ConcentrateGamePhase(GalileoInterfacer galileoInterfacer, MainFrame mainFrame) {
		super(galileoInterfacer, mainFrame);
		
		this.mPhaseTag = PhaseTag.CONCENTRATE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(int player) {
		return mGalileoInterfacer.getConcentrationScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
