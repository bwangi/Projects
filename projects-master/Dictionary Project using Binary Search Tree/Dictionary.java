package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
/**
 * @author Wangi Bae
 * /
public class Dictionary {

	/**
	 * An instance of a BinarySearchTree which stores this Dictionary's list of
	 * words.
	 */
	private BinarySearchTree<Entry> bst = new BinarySearchTree<Entry>();

	/**
	 * instance variable for keeping the number of elements
	 */
	private int size;

	/**
	 * Constructs a new Dictionary which is empty.
	 */
	public Dictionary() {

	}

	/**
	 * Constructs a new Dictionary whose word list is exactly (a deep copy of)
	 * the list stored in the given tree. (For testing purposes, you can set
	 * this Dictionary's BST to the given BST, rather clone it, but your final
	 * method must do the deep copy)
	 * 
	 * @param tree
	 *            - The tree of the existing word list
	 */
	public Dictionary(BinarySearchTree<Entry> tree) {

		ArrayList<Entry> items = tree.getPreorderTraversal();

		size = items.size();

		for (int i = 0; i < items.size(); i++) { // do the deep copy of the
													// given tree
			bst.add(items.get(i));
		}

	}

	/**
	 * Constructs a new Dictionary from the file specified by the given file
	 * name. Each line of the file will contain at least one word with an
	 * optional definition. Each line will have no leading or trailing
	 * whitespace. For each line of the file, create a new Entry containing the
	 * word and definition (if given) and add it to the BST.
	 * 
	 * @param filename
	 *            - The file containing the wordlist
	 * @throws FileNotFoundException
	 *             - If the given file does not exist
	 */
	public Dictionary(String filename) throws FileNotFoundException {

		File file = new File(filename);

		Scanner scan = new Scanner(file);

		while (scan.hasNextLine()) {

			String next = scan.nextLine();

			if (next.contains(":")) { // when the line has word and definition

				int indexOfColon = next.indexOf(':'); // sparse between the word
														// and the definition of
														// it

				String word = next.substring(0, indexOfColon);

				String definition = next.substring(indexOfColon + 1);

				this.addEntry(word, definition);

			}

			else { // when the line has only a word

				String word = next;

				this.addEntry(word);

			}

		}

		scan.close();
	}

	/**
	 * Adds a new Entry to the Dictionary for the given word with no definition.
	 * 
	 * @param word
	 *            - The word to add to the Dictionary
	 * @return true only if the Entry was successfully added to the Dictionary,
	 *         false otherwise.
	 */
	public boolean addEntry(String word) {

		if (word == null) {
			return false;
		}

		Entry item = new Entry(word);

		boolean answer = bst.add(item);

		if (answer) {
			size++;
			return true;
		}

		else {
			return false;
		}

	}

	/**
	 * Adds a new Entry to the Dictionary for the given word and definition.
	 * 
	 * @param word
	 *            - The word to add to the Dictionary
	 * @param definition
	 *            - The definition of the given word
	 * @return true only if the Entry was successfully added to the Dictionary,
	 *         false otherwise.
	 */
	public boolean addEntry(String word, String definition) {

		if (word == null) {
			return false;
		}

		Entry item = new Entry(word, definition);

		boolean answer = bst.add(item);

		if (answer) {
			size++;
			return true;
		}

		else
			return false;

	}

	/**
	 * Tests whether or not word exists in this Dictionary.
	 * 
	 * @param word
	 *            - The word to test.
	 * @return true is word exists in this Dictionary, false otherwise.
	 */
	public boolean hasWord(String word) throws IllegalArgumentException {

		if (word == null) {

			throw new IllegalArgumentException();

		}

		Entry item = new Entry(word);

		if (bst.contains(item)) {
			return true;
		}

		else
			return false;
	}

	/**
	 * Returns the definition of the given word in the Dictionary, if it is
	 * there.
	 * 
	 * @param word
	 *            - The word to retrieve the definition of.
	 * @return the definition of the word.
	 * @throws IllegalArgumentExeception
	 *             - If word does not exist in the Dictionary.
	 */
	public String getDefinitionOf(String word) throws IllegalArgumentException {

		if (word == null) {

			throw new IllegalArgumentException();
		}

		Entry item = new Entry(word);

		String retrievedData = bst.get(item).definition;

		return retrievedData;

	}

	/**
	 * Removes the given word from the word dictionary if it is there.
	 * 
	 * @param word
	 *            - The word to remove from Dictionary.
	 * @return true only if the word is successfully removed from the
	 *         BinarySearchTree, false otherwise.
	 */
	public boolean removeEntry(String word) {

		Entry item = new Entry(word);

		boolean answer = bst.remove(item);

		if (answer) {
			size--;
			return true;
		}

		else
			return false;
	}

	/**
	 * Changes the definition of given word if it is there.
	 * 
	 * @param word
	 *            - The word to change the definition of
	 * @param newDef
	 *            - The new definition of the word
	 * @return true if the definition was successfully updated, false otherwise.
	 */
	public boolean updateEntry(String word, String newDef) {

		if (word == null || newDef == null) {
			return false;
		}

		Entry item = new Entry(word, newDef);

		Entry answer = bst.get(item);

		if (answer == null) {
			return false;
		}

		answer.word = word;
		answer.definition = newDef;

		return true;
	}

	/**
	 * Outputs this Dictionary to the given file. The file should be formatted
	 * as follows: 1) One word and definition should appear per line separated
	 * by exactly one space. 2) Lines should not have any leading or trailing
	 * whitespace except for a single newline. 3) Each line of the file should
	 * have text. There should be no empty lines. 4) The words should be sorted
	 * alphabetically (i.e. using the BST's inorder traversal)
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public void printToFile(String filename) throws FileNotFoundException {

		File file = new File(filename);

		PrintWriter printWriter = new PrintWriter(file);

		ArrayList<Entry> willBePrinted = this.getSortedEntries();

		for (int i = 0; i < willBePrinted.size(); i++) {

			if (willBePrinted.get(i).definition == null) { // when an entry only
															// have word

				printWriter.println(willBePrinted.get(i).word);

			}

			else { // when an entry does have word and definition both

				printWriter.println(willBePrinted.get(i).word + ": " + willBePrinted.get(i).definition);

			}

		}

		printWriter.close();

	}

	/**
	 * Returns the number of items stored in the Dictionary.
	 */
	public int entryCount() {

		return size;
	}

	/**
	 * Returns a sorted list of Entries (as returned by an inorder traversal of
	 * the BST)
	 * 
	 * @return an ArrayList of sorted Entries
	 */
	public ArrayList<Entry> getSortedEntries() {

		ArrayList<Entry> answer = bst.getInorderTravseral();

		return answer;
	}

	/**
	 * A Key-Value Pair class which represents an entry in a Dictionary.
	 * 
	 * @author Wangi Bae
	 */
	public static class Entry implements Comparable<Entry> {

		/**
		 * instance variable for keeping the given word
		 */
		private String word;

		/**
		 * instance variable for keeping the given definition
		 */
		private String definition;

		/**
		 * Constructs a new Entry with the given word with no definition
		 * 
		 * @param w
		 *            - The word to create an entry for.
		 */
		public Entry(String w) {
			this.word = w;
		}

		/**
		 * Constructs a new Entry with the given word and definition
		 * 
		 * @param w
		 *            - The word to create an entry for.
		 * @param d
		 *            - The definition of the given word.
		 */
		public Entry(String w, String d) {

			this.word = w;

			this.definition = d;

		}

		/**
		 * Compares the word contained in this entry to the word in other.
		 * Returns a value < 0 if the word in this Entry is alphabetically
		 * before the other word, = 0 if the words are the same, and > 0
		 * otherwise.
		 */
		@Override
		public int compareTo(Entry other) {

			if (word.equals(other.word)) {
				return 0;
			}

			else if (word.compareTo(other.word) < 0) {
				return -1;
			}

			else {
				return 1;
			}

		}

		/**
		 * Tests for equality of this Entry with the given Object. Two entries
		 * are considered equal if the words they contain are equal regardless
		 * of their definitions.
		 */
		@Override
		public boolean equals(Object o) {

			if (o == null || o.getClass() != this.getClass()) { // check for the
																// validness of
																// an argument
				return false;
			}

			Entry item = (Entry) o;

			if (this.word.equals(item.word)) {
				return true;
			}

			else {
				return false;
			}
		}
	}
}
