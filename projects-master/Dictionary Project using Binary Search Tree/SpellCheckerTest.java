package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpellCheckerTest {
	public static void main(String argsp[]) throws FileNotFoundException {

		SpellChecker checker = new SpellChecker();

		String[] s = { "-d", "big_words.shuffled.txt" , "alice.txt" };

		checker.main(s);

		// "big_words.alpha.txt" , "alice.txt"
		// "big_words.shuffled.txt" , "alice.txt"
		// "words.shuffled.txt" , "gettysburg.txt"
	}

}
