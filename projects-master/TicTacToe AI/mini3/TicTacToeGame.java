package mini3;

import java.util.ArrayList;

/**
 * TicTacToeGame
 * 
 * @author Wangi Bae
 * 
 */
public class TicTacToeGame {

	public static final char[] MARKER = { 'X', 'O' };
	public static final char BLANK = '-';
	private int moveCounter;
	private char[][] board;
	private int winner;
	private ArrayList<Move> lastMove;
	private Player[] players;
	private boolean verbose;

	/**
	 * Creates a 3 by 3 TicTacToe Game board. Sets moveCounter to 0. Imports a
	 * couple of players.
	 * 
	 * @param p
	 *            length 2 array of Player
	 */
	public TicTacToeGame(Player[] p) {
		this(p, false);
	}

	/**
	 * Creates a 3 by 3 TicTacToe Game board. Sets moveCounter to 0. Imports a
	 * couple of players.
	 * 
	 * @param p
	 *            length 2 array of Player
	 * @param verbose
	 *            true if this class should print console output for every move
	 */
	public TicTacToeGame(Player[] p, boolean verbose) {
		players = p;
		moveCounter = 0;
		winner = -1;
		board = new char[3][3];
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				board[i][j] = BLANK;
		lastMove = new ArrayList<>();
		this.verbose = verbose;
	}

	/**
	 * Returns a copy of the board.
	 * 
	 * @return copy of the board.
	 */
	public char[][] getBoard() {
		char[][] copy = board.clone();
		for (int i = 0; i < board.length; ++i)
			copy[i] = board[i].clone();
		return copy;
	}

	/**
	 * Returns the index of the player whose turn it is.
	 * 
	 * @return
	 */
	public int getTurn() {
		return moveCounter % 2;
	}

	/**
	 * Main loop runs the game by applying moves obtained from the two players.
	 */
	public void run() {
		while (!isOver()) {
			int current = moveCounter % 2;
			Move m = players[current].play(this);
			if (isLegal(m)) {
				if (verbose) {
					printCurrentStatus();
				}
				make(m);
				if (hasWon()) {
					winner = current;
				}
				if (isOver()) {
					// notify players, in case they want to know
					players[0].gameOver(this);
					players[1].gameOver(this);
				}
			}
		}
	}

	/**
	 * Finds all legal moves and returns them in an ArrayList.
	 * 
	 * @return
	 */
	public ArrayList<Move> getAllLegalMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				if (isEmpty(i, j))
					moves.add(new Move(i, j));
		return moves;

	}

	/**
	 * Make a move m by putting a marker on the board.
	 * 
	 * @param m
	 * @return
	 */
	public void make(Move m) {
		board[m.getRow()][m.getCol()] = MARKER[moveCounter % 2];
		moveCounter += 1;
		lastMove.add(m);
	}

	/**
	 * undo the last move
	 */
	public void undo() {
		Move m = lastMove.remove(lastMove.size() - 1);
		board[m.getRow()][m.getCol()] = BLANK;
		moveCounter--;
	}

	/**
	 * check if the game is over
	 * 
	 * @return
	 */
	public boolean isOver() {
		return hasWon() || isFilled();
	}

	/**
	 * Returns index of winning player, or -1 if game is not over or was a tie.
	 * 
	 * @return
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * Prints the board and game status.
	 */
	public void printCurrentStatus() {
		System.out.println("_____");
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[i].length; ++j) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("_____\n\n");

		if (!isOver()) {
			int current = moveCounter % 2;
			System.out.println("It is " + MARKER[current] + "'s turn (" + players[current].getName() + ").");
		} else if (winner >= 0) {
			System.out.println(MARKER[winner] + " has won (" + players[winner].getName() + ").");
		} else {
			System.out.println("Game has ended in a tie.");
		}
	}

	/**
	 * Determines whether the given move is for an empty cell.
	 * 
	 * @param m
	 * @return
	 */
	public boolean isLegal(Move m) {
		return isEmpty(m.getRow(), m.getCol());
	}

	/**
	 * Check if the most recent move has caused the player to win.
	 * 
	 * @return
	 */
	private boolean hasWon() {
		if (lastMove.isEmpty())
			return false;

		Move m = lastMove.get(lastMove.size() - 1);
		// check the row that the last marker m is on
		if (board[m.getRow()][0] == board[m.getRow()][1] && board[m.getRow()][0] == board[m.getRow()][2])
			return true;
		// check the column
		if (board[0][m.getCol()] == board[1][m.getCol()] && board[0][m.getCol()] == board[2][m.getCol()])
			return true;
		// check first diagonal
		if (m.getRow() == m.getCol() && board[0][0] == board[1][1] && board[0][0] == board[2][2])
			return true;
		// check second diagonal
		if (m.getRow() + m.getCol() == 2 && board[0][2] == board[1][1] && board[0][2] == board[2][0])
			return true;
		return false;

	}

	/**
	 * check if the whole board is filled
	 * 
	 * @return
	 */
	private boolean isFilled() {
		return moveCounter == 9;
	}

	/**
	 * check if spot(i,j) is empty
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean isEmpty(int i, int j) {
		return board[i][j] == BLANK;
	}
}