package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SpellChecker {

	/**
	 * Displays usage information.
	 *
	 * There's no reason that you should need to modify this.
	 */
	private static void doUsage() {
		System.out.println("Usage: SpellChecker [-i] <dictionary> <document>\n"
				+ "                    -d <dictionary>\n" + "                    -h");
	}

	/**
	 * Displays detailed usage information and exits.
	 *
	 * There's no reason that you should need to modify this.
	 */
	private static void doHelp() {
		doUsage();
		System.out.println("\n" + "When passed a dictionary and a document, spell check the document.  Optionally,\n"
				+ "the switch -n toggles non-interactive mode; by default, the tool operates in\n"
				+ "interactive mode.  Interactive mode will write the corrected document to disk,\n"
				+ "backing up the uncorrected document by concatenating a tilde onto its name.\n\n"
				+ "The optional -d switch with a dictionary parameter enters dictionary edit mode.\n"
				+ "Dictionary edit mode allows the user to query and update a dictionary.  Upon\n"
				+ "completion, the updated dictionary is written to disk, while the original is\n"
				+ "backed up by concatenating a tilde onto its name.\n\n"
				+ "The switch -h displays this help and exits.");
		System.exit(0);
	}

	/**
	 * Runs the three modes of the SpellChecker based on the input arguments. DO
	 * NOT change this method in any way other than to set the name and sect
	 * variables.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			doUsage();
			System.exit(-1);
		}

		/*
		 * In order to be considered for the competition, set these variables.
		 */
		String name = "Wangi Bae"; // First and Last
		String sect = "B"; // "A" or "B"

		Timer timer = new Timer();

		timer.start();

		if (args[0].equals("-h"))
			doHelp();
		else if (args[0].equals("-n"))
			doNonInteractiveMode(args);
		else if (args[0].equals("-d"))
			doDictionaryEditMode(args);
		else
			doInteractiveMode(args);

		timer.stop();

		System.out.println("\nStudent name: " + name);
		System.out.println("Student sect: " + sect);
		System.out.println("Execution time: " + timer.runtime() + " ms");
	}

	/**
	 * Carries out the Interactive mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doInteractiveMode(String[] args) throws FileNotFoundException {

		if (args.length < 3) { // check the length of input;
								// arg1: non-interactive mode
								// arg2: dictionary input args3:
								// args3: reading input
			doUsage();
			System.exit(-1);

		}

		Dictionary d = new Dictionary(args[1]); // make a dictionary

		File file = new File(args[2]); // read reading input file

		Scanner line = new Scanner(file);

		PrintWriter pw = new PrintWriter("wangioutput.txt");

		while (line.hasNextLine()) {

			int lineKeeper = 0; // keep the space between lines orderly

			String nextLine = line.nextLine(); // take a next line

			String nextLine2 = nextLine;

			// pw.println("\n" + nextLine);
			System.out.println("\n" + nextLine);

			Scanner word = new Scanner(nextLine); // break down a sentence into
													// words and compare them to
													// the dictionary

			ArrayList<String> markedWords = new ArrayList<String>(); // Store
																		// marked
																		// words
																		// to
																		// use
																		// them
																		// later

			ArrayList<Integer> indexOfMarking = new ArrayList<Integer>(); // Store
																			// indexes
																			// of
																			// marked
																			// words
																			// to
																			// use
																			// it
																			// later

			while (word.hasNext()) {

				String nextWord = word.next(); // original word

				if (nextWord.equals("a") || nextWord.equals("A") || nextWord.equals("an") || nextWord.equals("An")
						|| nextWord.equals("--")) { // ignore articles

					if (word.hasNext()) { // if there is next word, go to the
											// next word

						nextWord = word.next();
					}

					else // if there is no next word, go to next line
					{
						break;
					}
				}

				String compare = nextWord.replaceAll("[^a-zA-Z\\-\\']", "").toLowerCase(); // edit
																							// original
																							// word
																							// to
																							// compare
																							// it
																							// to
																							// dic

				boolean answer = d.hasWord(compare); // find out whether this
														// word is in the dic or
														// not

				if (answer) { // if answer is true, then just replace word
								// with space

					String whiteSpace = " "; // replace marked word with space
												// in order to avoid same word
												// marking
					String blank = "";

					for (int i = 0; i < nextWord.length(); i++) {

						blank = blank + whiteSpace;

					}

					try {
						nextLine = nextLine.replaceFirst(Pattern.quote(nextWord), blank);

					}

					catch (PatternSyntaxException e) {

					}

				}

				else if (!answer) { // if the word is not in the dictionary,
									// then
									// remark it

					int mark = nextLine.indexOf(nextWord); // find the index of
															// the certain word

					String space = " ";

					String result = "^";

					int indexOfMark = result.indexOf("^");

					while (indexOfMark != (mark - lineKeeper)) { // add space

						result = space + result;

						indexOfMark++;

					}

					indexOfMarking.add(mark); // Store the indexes of marking to
												// use them later

					// pw.print(result);
					System.out.print(result);

					lineKeeper = mark + 1;

					String whiteSpace = " "; // replace marked word with space
												// in order to avoid same word
												// marking
					String blank = "";

					for (int i = 0; i < nextWord.length(); i++) {

						blank = blank + whiteSpace;

					}

					try {

						nextLine = nextLine.replaceFirst(Pattern.quote(nextWord), blank);

					}

					catch (PatternSyntaxException e) {

					} // end of removing

					markedWords.add(nextWord);

				}

			}

			// Interaction mode

			Scanner userInput = new Scanner(System.in); // For getting user's
														// input

			for (int i = 0; i < markedWords.size(); i++) {

				lineKeeper = 0;

				String list = markedWords.get(i); // Dredge up the marked
													// words

				System.out.print("\n" + list + ": [r]eplace / [a]ccept ");

				String answer = userInput.next(); // Store user's input

				if (answer.equals("r")) { // User wants to replace the word

					System.out.print("\n" + "Replacement text: "); // Ask user
																	// what he
																	// or she
																	// wants

					String replacement = userInput.next();

					nextLine2 = nextLine2.replaceFirst(Pattern.quote(list), replacement); // replace
																							// the
																							// old
																							// with
																							// the
																							// given
																							// word

					System.out.println("\n" + nextLine2); // Print the line that
															// is replaced with
															// the given word

					for (int j = i + 1; j < indexOfMarking.size(); j++) { // Start
																			// print
																			// marking
																			// under
																			// the
																			// line

						int mark = nextLine2.indexOf(markedWords.get(j)); // get
																			// the
																			// next
																			// index
																			// of
																			// word
																			// that
																			// should
																			// be
																			// remarked

						String space = " ";

						String result = "^";

						int indexOfMark = result.indexOf("^");

						while (indexOfMark != (mark - lineKeeper)) {

							result = space + result;

							indexOfMark++;
						}

						System.out.print(result);

						lineKeeper = mark + 1;

					}
				}

				else if (answer.equals("a")) { // User wants to accept the word

					System.out.println("\n" + nextLine2);

					for (int j = i + 1; j < indexOfMarking.size(); j++) { // Start
																			// print
																			// marking
																			// under
																			// the
																			// line

						int mark = nextLine2.indexOf(markedWords.get(j)); // get
																			// the
																			// next
																			// index
																			// of
																			// word
																			// that
																			// should
																			// be
																			// remarked

						String space = " ";

						String result = "^";

						int indexOfMark = result.indexOf("^");

						while (indexOfMark != (mark - lineKeeper)) {

							result = space + result;

							indexOfMark++;
						}

						System.out.print(result);

						lineKeeper = mark + 1;

					}
				}

			}

		}

		line.close();

		pw.close();

	}

	/**
	 * Carries out the Non-Interactive mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doNonInteractiveMode(String[] args) throws FileNotFoundException {

		if (args.length < 3) { // check the length of input;
								// arg1: non-interactive mode
								// arg2: dictionary input args3:
								// args3: reading input
			doUsage();
			System.exit(-1);

		}

		Dictionary d = new Dictionary(args[1]); // make a dictionary

		File file = new File(args[2]); // read reading input file

		Scanner line = new Scanner(file);

		PrintWriter pw = new PrintWriter("wangioutput.txt");

		while (line.hasNextLine()) {

			int lineKeeper = 0; // keep the space between lines orderly

			String nextLine = line.nextLine(); // take a next line

			pw.println("\n" + nextLine);

			Scanner word = new Scanner(nextLine); // break down a sentence into
													// words and compare them to
													// dictionary

			while (word.hasNext()) {

				String nextWord = word.next(); // original word

				if (nextWord.equals("a") || nextWord.equals("A") || nextWord.equals("an") || nextWord.equals("An")
						|| nextWord.equals("--")) { // ignore articles

					if (word.hasNext()) { // if there is next word, go to the
											// next word

						nextWord = word.next();
					}

					else // if there is no next word, go to next line
					{
						break;
					}
				}

				String compare = nextWord.replaceAll("[^a-zA-Z\\-\\']", "").toLowerCase(); // edit
																							// original
																							// word
																							// to
																							// compare
																							// it
																							// to
																							// dic

				boolean answer = d.hasWord(compare); // find out whether this
														// word is in the dic or
														// not

				if (answer) { // if answer is true, then just replace word
					// with space

					String whiteSpace = " "; // replace marked word with space
												// in order to avoid same word
												// marking
					String blank = "";

					for (int i = 0; i < nextWord.length(); i++) {

						blank = blank + whiteSpace;

					}

					try {
						nextLine = nextLine.replaceFirst(Pattern.quote(nextWord), blank);

					}

					catch (PatternSyntaxException e) {

					}

				}

				else if (!answer) { // if the word is not in the dictionary,
									// then
					// remark it

					int mark = nextLine.indexOf(nextWord); // find the index of
															// the certain word

					String space = " ";

					String result = "^";

					int indexOfMark = result.indexOf("^");

					while (indexOfMark != (mark - lineKeeper)) { // add space

						result = space + result;

						indexOfMark++;

					}

					pw.print(result);

					lineKeeper = mark + 1;

					String whiteSpace = " "; // replace marked word with space
												// in order to avoid same word
												// marking
					String blank = "";

					for (int i = 0; i < nextWord.length(); i++) {

						blank = blank + whiteSpace;

					}

					try {

						nextLine = nextLine.replaceFirst(Pattern.quote(nextWord), blank);

					}

					catch (PatternSyntaxException e) {

					}

				}

			}

		}

		line.close();

		pw.close();

	}

	/**
	 * Carries out the Dictionary Edit mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doDictionaryEditMode(String[] args) throws FileNotFoundException {

		if (args.length < 3) { // args[0] = mode
								// args[1] = dictionary file
								// args[2] = saved file name

			doUsage();

			System.exit(-1);
		}

		Dictionary d = new Dictionary(args[1]); // make a dictionary

		Scanner answer = new Scanner(System.in); // get the user's answer

		System.out.println("This is Dictionary Eidt Mode");

		System.out.print("Word: ");

		String user = answer.next();

		String savedWord = user;

		if (user.equals("!quit")) { // quit the mode
			return;
		}

		while (!user.equals("!quit")) { // not quit

			if (user.matches(".*\\d+.*")) { // screen invalid words

				System.out.print("Please enter valid word! Enter again: ");

				user = answer.next();

			}

			else if (d.hasWord(user)) { // if the word is in dic

				System.out.println("' " + user + " '" + " was found");

				System.out.print("[r]emove / [g]et definition / [c]hange difinition / do [n]othing ");

				user = answer.next();

				if (user.equals("r")) { // Remove

					d.removeEntry(savedWord);

					System.out.print("Word: ");

				}

				if (user.equals("g")) { // Get definition

					if (d.getDefinitionOf(savedWord) == null) {

						System.out.println(" <Undefined> ");

						System.out.print("Word: ");

					}

					else {

						System.out.println(d.getDefinitionOf(savedWord));

						System.out.print("Word: ");

					}

				}

				if (user.equals("c")) { // Change definition

					System.out.print("Definition: ");

					user = answer.next();/////////////////////////// bug

					d.removeEntry(savedWord);

					d.addEntry(savedWord, user);

					System.out.print("Word: ");

				}

				if (user.equals("n")) { // Do nothing

					System.out.print("Word: ");

				}

				user = answer.next();

			}

			else if (!d.hasWord(user)) { // if the word is not in dic

				System.out.println("' " + user + " '" + " was NOT found");

				System.out.print("[a]dd / add with [d]efinition / do [n]othing ");

				user = answer.next();

				if (user.equals("a")) { // Add without definition

					d.addEntry(savedWord);

					System.out.print("Word: ");

				}

				else if (user.equals("d")) { // Add with definition

					System.out.print("Definition: ");

					user = answer.next();////////// bug

					d.removeEntry(savedWord);

					d.addEntry(savedWord, user);

					System.out.print("Word: ");

				}

				else if (user.equals("n")) { // Do nothing

					System.out.print("Word: ");

				}

				user = answer.next();

			}

		}

	}

	/**
	 * Timer class used for this project's competition. DO NOT modify this class
	 * in any way or you will be ineligible for Eternal Glory.
	 */
	private static class Timer {
		private long startTime;
		private long endTime;

		public void start() {
			startTime = System.nanoTime();
		}

		public void stop() {
			endTime = System.nanoTime();
		}

		public long runtime() {
			return endTime - startTime;
		}
	}
}
