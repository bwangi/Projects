package mini3;

import java.util.Scanner;

/**
 * 
 * @author Wangi Bae
 *
 */
public class InteractivePlayer implements Player {

	private static int counter = 0;
	private String name;
	private Scanner in;

	public InteractivePlayer() {
		name = "Interactive-" + counter++;
		in = new Scanner(System.in);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Move play(TicTacToeGame g) {
		g.printCurrentStatus();
		System.out.println("Enter a valid move (" + 2 + " ints): ");
		return new Move(in.nextInt(), in.nextInt());
	}

	@Override
	public void gameOver(TicTacToeGame g) {
		g.printCurrentStatus();
	}
}
