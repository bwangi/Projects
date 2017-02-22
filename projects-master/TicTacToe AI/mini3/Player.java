package mini3;

/**
 * TicTacToe Game Player interface. Any class that implements this interface can
 * be imported to the TicTacToeTest to play the game.
 * 
 * @author Wangi Bae
 * 
 */
public interface Player {
	/**
	 * Returns a valid move for the given game.
	 * 
	 * @param g
	 *            current game state
	 * @return a valid move for the game
	 */
	Move play(TicTacToeGame g);

	/**
	 * Informs player of final game state when game ends.
	 * 
	 * @param g
	 *            final game state
	 */
	void gameOver(TicTacToeGame g);

	/**
	 * Returns a string name for this player.
	 * 
	 * @return name for this player
	 */
	String getName();
}