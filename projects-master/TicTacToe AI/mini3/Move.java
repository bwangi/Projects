package mini3;

/**
 * Move for a Tic Tac Toe game.
 * 
 * @author Guang Song
 * @date 2012
 */
public class Move {
	private int row, col;

	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
