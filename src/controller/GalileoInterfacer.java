package controller;


/** The interface between Mind Body Fitness Challenge software and hardware. This class is responsible
 * for retrieving data from the Galileo board as well as sending data such as challenge prompts.
 * 
 * @author nickholt
 */
public class GalileoInterfacer {
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the concentration score of the player.
	 */
	public float getConcentrationScore(int player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the meditation score of the player.
	 */
	public float getMeditationScore(int player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the heartbeat_high score of the player.
	 */
	public float getHeartbeatHighScore(int player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the heartbeat_low score of the player.
	 */
	public float getHeartbeatLowScore(int player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the beat score of the player.
	 */
	public float getBeatScore(int player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the pressure score of the player.
	 */
	public float getPressureScore(int player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the reaction score of the player.
	 */
	public float getReactionTimeScore(int player) {
		return 1.0f;
	}
}
