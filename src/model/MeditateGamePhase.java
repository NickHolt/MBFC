package model;

import controller.Engine;


public class MeditateGamePhase extends GamePhase {

	public MeditateGamePhase(Player playerOne, Player playerTwo, Engine engine) {
		super(playerOne, playerTwo, engine);
		
		this.mPhaseTag = PhaseTag.MEDITATE;
		this.mDuration = 20000;
		this.mUpdatePeriod = 200;
		this.mMaxScore = 15000;
		// TODO
	}
	
	@Override
	public void play() throws InterruptedException {
		mEngine.getMainFrame().putText("Meditate!");
		super.play();
	}

	@Override
	public float getScoreFromGalileo(Player player) {
		if(player.equals(mPlayerOne)) {
			return mEngine.getPlayerOneGalileoInterfacer().getMeditationValue();
		} else {
			return mEngine.getPlayerTwoGalileoInterfacer().getMeditationValue();
		}
	}

}
