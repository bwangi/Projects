package mini3;

import java.util.*;

/**
 * 
 * @author Wangi Bae
 *
 */
public class MyPlayer implements Player {

	/**
	 * Instance variable for keeping name
	 */
	private String myName;

	/**
	 * Instance variable for keeping counter
	 */
	private int counter;

	private int turn;

	public MyPlayer() // Constructor
	{
		myName = "Wangi-" + counter++;
	}

	public MyPlayer(Random givenRand) // Constructor
	{
		myName = "Wangi-" + counter++;
	}

	public Move play(TicTacToeGame g) {

		return this.simulation(g);

	}

	/**
	 * 
	 * @param board
	 * @return All the possible winning cases of X given the current state of
	 *         board
	 */
	public ArrayList<Integer> PositioninX(char[][] board) {

		ArrayList<Integer> positionedMove = new ArrayList<Integer>();

		int positionIndex = 0;

		for (int i = 0; i < 3; i++) { // change them to number

			for (int j = 0; j < 3; j++) {

				if (board[i][j] == 'X' || board[i][j] == '-') {

					positionedMove.add(positionIndex);

				}

				positionIndex++;

			}

		}

		return positionedMove;

	}

	/**
	 * 
	 * @param board
	 * @return All the possible winning cases of O given the current state of
	 *         board
	 */
	public ArrayList<Integer> PositioninO(char[][] board) {

		ArrayList<Integer> positionedMove = new ArrayList<Integer>();

		int positionIndex = 0;

		for (int i = 0; i < 3; i++) { // change them to number

			for (int j = 0; j < 3; j++) {

				if (board[i][j] == 'O' || board[i][j] == '-') {

					positionedMove.add(positionIndex);

				}

				positionIndex++;

			}

		}

		return positionedMove;

	}

	/**
	 * 
	 * @param X
	 * @param O
	 * @return Evaluation function of X's
	 */
	public int evaluationFunction(ArrayList<Integer> X, ArrayList<Integer> O) {

		int evalX = 0;

		int evalO = 0;

		// Calculate all the possible winning cases of X's
		for (int i = 0; i < X.size(); i++) {

			if (X.get(i) == 0) {

				for (int j = i + 1; j < X.size(); j++) {

					if (X.get(j) == 1) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 2) { // 012

								evalX++;

							}

						}

					}

					else if (X.get(j) == 4) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 8) { // 048

								evalX++;

							}

						}

					}

					else if (X.get(j) == 3) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 6) { // 036

								evalX++;

							}

						}

					}

				}

			}

			else if (X.get(i) == 1) { // 147

				for (int j = i + 1; j < X.size(); j++) {

					if (X.get(j) == 4) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 7) {

								evalX++;

							}

						}
					}

				}

			}

			else if (X.get(i) == 2) {

				for (int j = i + 1; j < X.size(); j++) {

					if (X.get(j) == 4) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 6) { // 246

								evalX++;
							}

						}
					}

					if (X.get(j) == 5) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 8) { // 258

								evalX++;
							}

						}
					}
				}
			}

			else if (X.get(i) == 3) {

				for (int j = i + 1; j < X.size(); j++) {

					if (X.get(j) == 4) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 5) { // 345

								evalX++;

							}
						}

					}

				}
			}

			else if (X.get(i) == 6) {

				for (int j = i + 1; j < X.size(); j++) {

					if (X.get(j) == 7) {

						for (int k = j + 1; k < X.size(); k++) {

							if (X.get(k) == 8) { // 678

								evalX++;

							}

						}

					}

				}

			}

		}

		// Calculate O's winning cases
		for (int i = 0; i < O.size(); i++) {

			if (O.get(i) == 0) {

				for (int j = i + 1; j < O.size(); j++) {

					if (O.get(j) == 1) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 2) { // 012

								evalO++;

							}

						}

					}

					else if (O.get(j) == 4) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 8) { // 048

								evalO++;

							}

						}

					}

					else if (O.get(j) == 3) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 6) { // 036

								evalO++;

							}

						}

					}

				}

			}

			else if (O.get(i) == 1) { // 147

				for (int j = i + 1; j < O.size(); j++) {

					if (O.get(j) == 4) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 7) {

								evalO++;

							}

						}
					}

				}

			}

			else if (O.get(i) == 2) {

				for (int j = i + 1; j < O.size(); j++) {

					if (O.get(j) == 4) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 6) { // 246

								evalO++;
							}

						}
					}

					if (O.get(j) == 5) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 8) { // 258

								evalO++;
							}

						}
					}
				}
			}

			else if (O.get(i) == 3) {

				for (int j = i + 1; j < O.size(); j++) {

					if (O.get(j) == 4) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 5) { // 345

								evalO++;

							}
						}

					}

				}
			}

			else if (O.get(i) == 6) {

				for (int j = i + 1; j < O.size(); j++) {

					if (O.get(j) == 7) {

						for (int k = j + 1; k < O.size(); k++) {

							if (O.get(k) == 8) { // 678

								evalO++;

							}

						}

					}

				}

			}

		}

		return evalX - evalO;
	}

	/**
	 * 
	 * @param g
	 * @return the move that the AI will take
	 */
	public Move simulation(TicTacToeGame g) {

		ArrayList<Integer> max = new ArrayList<Integer>(); // Store evaluation
															// functions

		ArrayList<int[]> result = new ArrayList<int[]>(); // Store my move and
															// evaluation
															// function

		TicTacToeGame game = g; // Make simulated games

		ArrayList<Move> givenMoves = game.getAllLegalMoves(); // Get all the
																// possible
																// moves that AI
																// can take

		if (givenMoves.size() == 1) { // If there is only one possible move, do
										// not simulate it
										// game = null;

			return givenMoves.get(0);
		}

		// Simulate every possible case
		for (int i = 0; i < givenMoves.size(); i++) {

			game.make(givenMoves.get(i)); // first depth of tree(my moves)

			if (this.win(game.getBoard())) { // If my first simulated move can
												// win,
												// then do
												// that move

				game.undo();

				return givenMoves.get(i);

			}

			{

				ArrayList<Move> enemyMoves = game.getAllLegalMoves(); // All the
																		// possible
																		// enemy's
																		// moves

				for (int j = 0; j < enemyMoves.size(); j++) {

					game.make(enemyMoves.get(j)); // second depth of
													// tree(enemy's
													// moves)

					if (this.win(game.getBoard())) { // If enemy's move can end
														// the game, block it

						for (int k = 0; k < givenMoves.size(); k++) {

							if (givenMoves.get(k).getCol() == enemyMoves.get(j).getCol()
									&& givenMoves.get(k).getRow() == enemyMoves.get(j).getRow()) {

								int[] exception = { k, 10 };

								result.add(exception);

							}

						}

					}

					char[][] updatedBoard = game.getBoard();

					ArrayList<Integer> X = PositioninX(updatedBoard);

					ArrayList<Integer> O = PositioninO(updatedBoard);

					if (updatedBoard[enemyMoves.get(j).getRow()][enemyMoves.get(j).getCol()] == 'X') { // If
																										// AI
																										// goes
																										// second

						max.add(evaluationFunction(O, X));

						game.undo();

					}

					else { // If AI goes first

						max.add(evaluationFunction(X, O));

						game.undo();

					}

				}

			} //

			int[] element = new int[2]; // Store changed possible positions and
										// winning possibility

			int highest = max.get(0);

			element[0] = i;

			for (int k = 1; k < max.size(); k++) {

				if (highest < max.get(k)) {

					highest = max.get(k);

				}

				element[1] = highest;

			}

			result.add(element);

			max.clear();

			game.undo();

		} // end of simulation

		int index = result.get(0)[0]; // Find the move that has highest winning
										// possibility

		int maxValue = result.get(0)[1];

		for (int k = 1; k < result.size(); k++) {

			if (result.get(k)[1] > maxValue) {

				maxValue = result.get(k)[1];

				index = result.get(k)[0];

			}

		}

		return givenMoves.get(index);
	}

	/**
	 * 
	 * @param board
	 * @return true if someone wins, false otherwise
	 */
	public boolean win(char[][] board) {

		if (board[0][0] != '-' && board[0][0] == board[0][1] && board[0][0] == board[0][2]) {// 012

			return true;

		}

		else if (board[0][0] != '-' && board[0][0] == board[1][0] && board[0][0] == board[2][0]) {// 036

			return true;

		}

		else if (board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {// 048

			return true;

		}

		else if (board[0][1] != '-' && board[0][1] == board[1][1] && board[0][1] == board[2][1]) {// 147

			return true;

		}

		else if (board[0][2] != '-' && board[0][2] == board[1][2] && board[0][2] == board[2][2]) {// 258

			return true;

		}

		else if (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {// 246

			return true;

		}

		else if (board[1][0] != '-' && board[1][0] == board[1][1] && board[1][0] == board[1][2]) {// 345

			return true;

		}

		else if (board[2][0] != '-' && board[2][0] == board[2][1] && board[2][0] == board[2][2]) {// 678

			return true;

		}

		return false;

	}

	public void gameOver(TicTacToeGame g) {
		// Do nothing
	}

	public String getName() {
		return myName;
	}

}
