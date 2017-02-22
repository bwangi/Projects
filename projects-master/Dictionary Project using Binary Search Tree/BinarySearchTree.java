package edu.iastate.cs228.hw4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Extension of the AbstractCollection class based on a Binary Search Tree.
 * Efficiencies may vary by implementation, but all methods should have at least
 * the worst case runtimes of a standard Tree.
 * 
 * @author Wangi Bae
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends AbstractCollection<E> {
	/**
	 * instance variable for storing root
	 */
	private Node<E> root;

	/**
	 * instance variable for counting number of nodes in the tree
	 */
	private int count;

	/**
	 * instance variable for orders
	 */
	private ArrayList<E> answer = new ArrayList<E>();

	/**
	 * Constructs an empty BinarySearchTree
	 */
	public BinarySearchTree() {

	}

	/**
	 * Constructs a new BinarySearchTree whose root is exactly the given Node.
	 * (For testing purposes, set the root to the given Node, do not clone it)
	 * 
	 * @param root
	 *            - The root of the new tree
	 * @param size
	 *            - The number of elements already contained in the new tree
	 */
	public BinarySearchTree(Node<E> root, int size) {

		this.root = root;

		count = size;

	}

	/**
	 * Adds the given item to the tree if it is not already there.
	 * 
	 * @return false if item already exists in the tree and true otherwise.
	 * @param item
	 *            - Item to be added to the tree
	 * @throws IllegalArgumentException
	 *             - If item is null
	 */
	@Override
	public boolean add(E item) throws IllegalArgumentException {

		if (item == null) { // exception
			throw new IllegalArgumentException();
		}

		if (this.root == null) { // if the root is null

			this.root = new Node<E>(item);

			count++;

			return true;

		}

		Node<E> current = root;

		while (true) { // if the root is not null then find the right place, and
						// add the item

			int val = item.compareTo(current.getData());

			if (val == 0) { // if the item already has been added to the tree
				return false;
			}

			if (val < 0) { // go to the left
				if (current.getLeft() == null) { // if the node is null, made
													// the new one

					Node<E> newLeft = new Node<E>(item);

					current.setLeft(newLeft);

					newLeft.setParent(current);

					count++;

					return true;

				}

				current = current.getLeft();

			}

			else { // go to the right

				if (current.getRight() == null) {

					Node<E> newRight = new Node<E>(item);

					current.setRight(newRight);

					newRight.setParent(current);

					count++;

					return true;

				}

				current = current.getRight();

			}

		}

	}

	/**
	 * Removes the given item from the tree if it is there. Because the item is
	 * an Object it will need to be cast to an E type. To verify that this is a
	 * safe cast, compare its class to the class of the root Node's data.
	 * 
	 * @return false if the list is empty or item does not exist in the tree,
	 *         true otherwise
	 * @param item
	 *            - The item to be removed from the tree
	 */
	@Override
	public boolean remove(Object item) {

		if (item == null || this.isEmpty() || !this.contains(item)) {
			return false;
		}

		if (this.root.getData().getClass() != item.getClass()) {
			return false;
		}

		E key = (E) item;

		Node<E> current = this.root;

		while (true) {

			int val = key.compareTo(current.getData());

			if (val == 0) {

				if (current.getRight() == null && current.getLeft() == null) { // when
																				// the
																				// current
																				// has
																				// no
																				// child

					Node<E> p = current.getParent();

					if (p.getLeft() == current) {

						p.setLeft(null);

						current.setParent(null);

						count--;

						return true;
					}

					else if (p.getRight() == current) {

						p.setRight(null);

						current.setParent(null);

						count--;

						return true;

					}
				}

				Node<E> successor = current.getSuccessor();

				if (successor == this.root) { // when the successor is the root.
												// In this case, it does not
												// need to take the root.

					Node<E> pred = current.getPredecessor();

					current.setData(pred.getData());

					Node<E> p = pred.getParent();

					p.setLeft(null);

					pred.setParent(null);

					count--;

					return true;
				}

				if (successor == null) {

					Node<E> pred = current.getPredecessor();

					current.setData(pred.getData());

					Node<E> parent = pred.getParent();

					parent.setLeft(null);

					pred.setParent(null);

					count--;

					return true;

				}

				else if (successor != null) {
					current.setData(successor.getData()); // remove the data by
															// replacing it with
															// the
															// successor's

					Node<E> parentOfSuccesor = successor.getParent(); // unlink
																		// successor
																		// from
																		// the
																		// tree(start
																		// it
																		// from
																		// here)

					if (parentOfSuccesor.getLeft() == successor) {

						parentOfSuccesor.setLeft(null);

						successor.setParent(null);

						count--;

						return true;

					}

					else if (parentOfSuccesor.getRight() == successor) {

						parentOfSuccesor.setRight(null);

						successor.setParent(null);

						count--;

						return true;

					}

				}
			} else if (val < 0) {
				current = current.getLeft();
			}

			else if (val > 0) {
				current = current.getRight();
			}
		}
	}

	/**
	 * Retrieves data of the Node in the tree that contains item. i.e. the data
	 * such that Node.data.equals(item) is true
	 * 
	 * @return null if item does not exist in the tree, otherwise the data
	 *         stored at the Node that meets the condition above.
	 * @param item
	 *            - The item to be retrieved
	 */
	public E get(E item) {

		if (item == null) {

			throw new NullPointerException();
		}

		if (!this.contains(item)) {

			return null;

		}

		Node<E> curr = this.root;

		while (true) {

			int val = item.compareTo(curr.getData()); // Compare item to the
														// data

			if (val == 0) { // If they are same

				return curr.getData();

			}

			else if (val < 0) { // Go to left

				if (curr.getLeft() == null) {
					return null;
				}

				else {
					curr = curr.getLeft();
				}
			}

			else if (val > 0) { // Go to right

				if (curr.getRight() == null) {
					return null;
				}

				else {
					curr = curr.getRight();
				}

			}

		}

	}

	/**
	 * Tests whether or not item exists in the tree. i.e. this should only
	 * return true if a Node exists in the tree such that Node.data.equals(item)
	 * is true
	 * 
	 * @return false if item does not exist in the tree, otherwise true
	 * @param item
	 *            - The item check
	 */
	@Override
	public boolean contains(Object item) {

		if (item == null || this.isEmpty()) {
			return false;
		}

		if (this.root.getData().getClass() != item.getClass()) {
			return false;
		}

		E key = (E) item;

		Node<E> current = this.root;

		while (true) {

			if (current == null) {
				return false;
			}

			int val = key.compareTo(current.getData());

			if (val == 0 || key.equals(current.getData())) {
				return true;
			}

			if (val < 0) {
				current = current.getLeft();
			}

			else if (val > 0) {
				current = current.getRight();
			}
		}
	}

	/**
	 * Removes all elements from the tree
	 */
	@Override
	public void clear() {

		this.removeAll(this);

		count = 0;
	}

	/**
	 * Tests whether or not the tree contains any elements.
	 * 
	 * @return false if the tree contains at least one element, true otherwise.
	 */
	@Override
	public boolean isEmpty() {

		if (count > 1) {
			return false;
		}

		return true;
	}

	/**
	 * Retrieves the number of elements in the tree.
	 */
	@Override
	public int size() {

		return count;
	}

	/**
	 * Returns a new BSTIterator instance.
	 */
	@Override
	public Iterator<E> iterator() {

		BSTIterator c = new BSTIterator();

		return c;
	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a preorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getPreorderTraversal() {

		this.answer.clear(); // clear everything in it

		preorder(this.root);

		return answer;
	}

	/**
	 * private method for using recursive
	 * 
	 * @param node2
	 */
	private void preorder(Node<E> node2) {

		if (node2 == null) {
			return;
		}

		answer.add(node2.getData());

		preorder(node2.getLeft());

		preorder(node2.getRight());

	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a postorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getPostOrderTraversal() {

		this.answer.clear(); // clear everything in it

		this.postorder(this.root);

		return answer;
	}

	/**
	 * private method for using recursive
	 * 
	 * @param node2
	 */
	private void postorder(Node<E> node2) {

		if (node2 == null) {
			return;
		}

		postorder(node2.getLeft());

		postorder(node2.getRight());

		answer.add(node2.getData());

	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a inorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getInorderTravseral() {

		answer.clear();

		this.inorder(this.root);

		return answer;
	}

	private void inorder(Node<E> node2) {

		if (node2 == null) {
			return;
		}

		inorder(node2.getLeft());

		answer.add(node2.getData());

		inorder(node2.getRight());

	}

	/**
	 * Implementation of the Iterator interface which returns elements in the
	 * order of an inorder traversal using Nodes predecessor and successor.
	 * 
	 * @author
	 */
	private class BSTIterator implements Iterator<E> {
		/**
		 * node to be visited next
		 */
		private Node<E> next;

		/**
		 * node last visited by next()
		 */
		private Node<E> last = null;

		/**
		 * to keep whether next has been called or not
		 */
		private int keepNext;

		/**
		 * to keep whether remove has been called or not
		 */
		private int keepRemove;

		public BSTIterator() {
			next = root;

			if (next != null)
				while (next.getLeft() != null)
					next = next.getLeft();
		}

		/**
		 * Returns true if more elements exist in the inorder traversal, false
		 * otherwise.
		 */
		@Override
		public boolean hasNext() {

			return next != null;
		}

		/**
		 * Returns the next item in the inorder traversal.
		 * 
		 * @return the next item in the traversal.
		 * @throws IllegalStateException
		 *             - if no more elements exist in the traversal.
		 */
		@Override
		public E next() throws IllegalStateException {
			if (next == null)
				throw new IllegalStateException();

			if (keepRemove == 0) { // to keep watching next has been called
									// before the remove
				keepNext = keepNext + 1;

			}

			else { // to keep watching next has been called
					// before the remove
				keepNext = keepRemove + 1;
			}

			last = next;
			next = next.getSuccessor();

			return last.getData();

		}

		/**
		 * Removes the last item that was returned by calling next().
		 * 
		 * @throws IllegalStateException
		 *             - if next() has not been called yet or remove() is called
		 *             multiple times in a row.
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (last == null) {
				throw new IllegalStateException();
			}

			if (keepRemove > keepNext) {
				throw new IllegalStateException();
			}

			keepNext = 0;
			keepRemove++;

			if (last.getLeft() != null && last.getRight() != null)
				next = last;
			delete(last);

			last = null;
			count--;
		}

		/**
		 * private method for unlinking
		 * 
		 * @param node
		 */
		private void delete(Node<E> node) {
			if (node == null)
				throw new NullPointerException();

			Node<E> toDel = node;

			if (toDel.getLeft() != null && toDel.getRight() != null) {

				Node<E> s = toDel.getSuccessor();

				toDel.setData(s.getData());

				toDel = s;

			}

			// toDel has at most one child.
			if (toDel.getLeft() != null) // has left child
				linkChildToParent(toDel, toDel.getLeft());
			else // has right child or null
				linkChildToParent(toDel, toDel.getRight());

		}

		/**
		 * private method for Remove toDel by connecting its only child to its
		 * parent
		 * 
		 * @param toDel
		 * @param child
		 */
		private void linkChildToParent(Node<E> toDel, Node<E> child) {
			if (toDel == root) {
				root = child;
				if (child != null)
					child.setParent(null);
			}

			else {

				Node<E> checking = toDel.getParent().getLeft();

				if (checking != null) {
					if (toDel.getParent().getLeft().equals(toDel))
						toDel.getParent().setLeft(child);

					else
						toDel.getParent().setRight(child);
				} else
					toDel.getParent().setRight(child);
				if (child != null)

					child.setParent(toDel.getParent());
			}
		} // child may be null
	}
}
