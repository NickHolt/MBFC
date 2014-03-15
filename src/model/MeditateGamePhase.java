package model;

import controller.Engine;


public class MeditateGamePhase extends GamePhase {

	public MeditateGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.MEDITATE;
		this.mDuration = 10000;
		this.mUpdatePeriod = 1000;
		this.mMaxScore = 100;
		// TODO
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		return mEngine.getGalileoInterfacer().getMeditationScore(player);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

}
