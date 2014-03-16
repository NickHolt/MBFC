package controller;

import model.Player;


/** The interface between Mind Body Fitness Challenge software and hardware. This class is responsible
 * for retrieving data from the Galileo board as well as sending data such as challenge prompts.
 * 
 * @author nickholt
 */
public class GalileoInterfacer {
	
	public float getConcentrationScore(Player player) {
		return 1.0f;
	}
	
	public float getMeditationScore(Player player) {
		return 1.0f;
	}
	
	public float getHeartbeatHighScore(Player player) {
		return 1.0f;
	}

	public float getHeartbeatLowScore(Player player) {
		return 1.0f;
	}

	public float getBeatScore(Player player) {
		return 1.0f;
	}

	public float getPressureScore(Player player) {
		return 1.0f;
	}

	public float getReactionTimeScore(Player player) {
		return 1.0f;
	}
}
