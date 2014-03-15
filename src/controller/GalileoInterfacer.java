package controller;

import model.Player;


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
	public float getConcentrationScore(Player player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the meditation score of the player.
	 */
	public float getMeditationScore(Player player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the heartbeat_high score of the player.
	 */
	public float getHeartbeatHighScore(Player player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the heartbeat_low score of the player.
	 */
	public float getHeartbeatLowScore(Player player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the beat score of the player.
	 */
	public float getBeatScore(Player player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the pressure score of the player.
	 */
	public float getPressureScore(Player player) {
		return 1.0f;
	}
	
	/**
	 * @param player The player whose score to return.
	 * @return A float between 0.0 and 1.0 representing the reaction score of the player.
	 */
	public float getReactionTimeScore(Player player) {
		return 1.0f;
	}
}
