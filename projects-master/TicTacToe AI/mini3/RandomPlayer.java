package mini3;

import java.util.*;

/**
  RandomPlayer
  @author Guang Song
  @date 2012
*/

public class RandomPlayer implements Player {
   private static int counter = 0;
   private Random rand;
   private String myName;
   
   public RandomPlayer() {
     this(new Random());
   }
   
   public RandomPlayer(Random givenRand) {
     rand = givenRand;
     myName = "Random-" + counter++;
   }
   
   @Override
   public String getName() {
	   return myName;
   }

   @Override
   public Move play(TicTacToeGame g) {
	ArrayList<Move> all = g.getAllLegalMoves();
	int index = rand.nextInt(all.size());
	return all.get(index);
   }
   
   @Override
   public void gameOver(TicTacToeGame g){
     // do nothing
   }
}