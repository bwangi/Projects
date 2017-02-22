package mini3;

import java.util.Random;

public class TicTacToeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		playInteractive();
		//playIterations(1000);
	}

	/**
	 * Play interactively with a random player.
	 */
	private static void playInteractive() {
		int seed = (int) System.currentTimeMillis() % 1000;
		System.out.println("Using seed " + seed);
		Random rand = new Random(seed);
		Player[] players = {new InteractivePlayer() , new MyPlayer()};// changehappened
		TicTacToeGame game = new TicTacToeGame(players);
		game.run();
		//new InteractivePlayer() , new MyPlayer()
		//new MyPlayer() , new InteractivePlayer()
		
	}

	/**
	 * Play two automated players against each other for multiple iterations.
	 */
	 private static void playIterations(int numIters) {
	 int[] counts = new int[3];
	 for (int i = 0; i < numIters; ++i) {
	 Player[] players = { new RandomPlayer() , new MyPlayer()};
	 TicTacToeGame game = new TicTacToeGame(players);
	 game.run();
	 int index = game.getWinner() + 1; // returns -1, 0, or 1
	 counts[index] += 1;
	 }
	 System.out.println("Wins: " + counts[2]);
	 System.out.println("Losses: " + counts[1]);
	 System.out.println("Ties: " + counts[0]);
	 System.out.println(" the percentage of losses is " + counts[1] / 1000.0 *
	 100);
	 //new RandomPlayer() , new MyPlayer()
	 // new MyPlayer() , new RandomPlayer()
	 }
}
