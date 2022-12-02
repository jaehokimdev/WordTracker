package utilities;

import java.util.NoSuchElementException;
import java.util.Stack;

import exception.TreeException;
import wordDomain.Word;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT {
	
	private static final long serialVersionUID = 103125396562901601L;

	private BSTreeNode<E> root;
	
	private int size;
	
	public BSTree() {
		root = null;
	}
	
	public BSTree (E element) {
		root = new BSTreeNode<E> (element);
	}

	@Override
	public BSTreeNode getRoot() throws TreeException {
		
		return this.root;
	}

	@Override
	public int getHeight() {

		return this.root.getHeight();
	}

	@Override
	public int size() {

		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		
		return false;
	}

	@Override
	public void clear() {

		this.root = null;
		this.size = 0;
	}

	@Override
	public boolean contains(Comparable entry) throws TreeException {
		BSTreeNode<E> current = root;
		
		while (current != null) {
			if (entry.compareTo(current.getElement()) > 0) {
					current = current.getRight();
			} else if (entry.compareTo(current.getElement()) < 0) {				
					current = current.getLeft();
			} else {
				return true;
			}
		}
		
		return false;
	}
		
	@Override
	public BSTreeNode search(Comparable entry) throws TreeException {
		
		BSTreeNode<E> current = root;
		
		while (current != null) {
			if (entry.compareTo(current.getElement()) > 0) {
					current = current.getRight();
			} else if (entry.compareTo(current.getElement()) < 0) {				
					current = current.getLeft();
			} else {
				return current;
			}
		}
		
		return null;
	}

	@Override
	public boolean add(Comparable newEntry) throws NullPointerException {
		if (root == null) {
			root = new BSTreeNode<E> ((E) newEntry);
		} else {
			BSTreeNode<E> prev = null;
			BSTreeNode<E> current = root;
			int compareResult;
			
			while (current != null) {
				compareResult = newEntry.compareTo(current.getElement());
				
				if ( compareResult < 0) {
					prev = current;
					current = current.getLeft();
				} else if ( compareResult > 0) {
					prev = current;
					current = current.getRight();
				} else {
					return false;
				}
			}
			
			if (newEntry.compareTo(prev.getElement()) < 0) {
				prev.setLeft(new BSTreeNode<E> ((E) newEntry));
			} else {
				prev.setRight(new BSTreeNode<E> ((E) newEntry));
			}
		}
		
		size++;
		return true;
	}

	@Override
	public Iterator<E> inorderIterator() {

		Iterator<E> it = new Iterator<E>() {
			
			private Stack<BSTreeNode<E>> nodeStack = new Stack<BSTreeNode<E>>();
			private BSTreeNode<E> current = root;

			@Override
			public boolean hasNext() {
				
				return !nodeStack.isEmpty() || (current != null);
			}

			@Override
			public E next() throws NoSuchElementException {
				BSTreeNode<E> nextNode = null;
				
				while (current != null) {
					nodeStack.push(current);
					current = current.getLeft();
				}
				
				if (!nodeStack.isEmpty()) {
					nextNode = nodeStack.pop();
					assert nextNode != null;
					current = nextNode.getRight();
				} else {
					throw new NoSuchElementException();
				}
				
				return nextNode.getElement();
			}
		
		};
		
		return it;
	}

	@Override
	public Iterator<E> preorderIterator() {
		Iterator<E> it = new Iterator<E>() {
			
			private Stack<BSTreeNode<E>> nodeStack = new Stack<BSTreeNode<E>>();
			private BSTreeNode<E> current = root;			

			@Override
			public boolean hasNext() {

				return !nodeStack.isEmpty() || (current != null);
			}

			@Override
			public E next() throws NoSuchElementException {
								
				if (nodeStack.isEmpty()) {
					nodeStack.push(root);
				}
				
				if (!nodeStack.isEmpty()) {
					current = nodeStack.pop();
					
					if (current.hasRightChild()) {
						nodeStack.push(current.getRight());
					}
					if (current.hasLeftChild()) {
						nodeStack.push(current.getLeft());
					}
				} else {
					throw new NoSuchElementException();
				}
				
				return current.getElement();
			}
			
		};
		
		return it;
	}

	@Override
	public Iterator<E> postorderIterator() {
		Iterator<E> it = new Iterator<E>() {
			
			private Stack<BSTreeNode<E>> firstNodeStack = new Stack<BSTreeNode<E>>();
			private Stack<BSTreeNode<E>> secondNodeStack = new Stack<BSTreeNode<E>>();
			private BSTreeNode<E> current = root;
			
			@Override
			public boolean hasNext() {
				
				return !secondNodeStack.isEmpty() || (current != null);
			}

			@Override
			public E next() throws NoSuchElementException {
				
				if (secondNodeStack.isEmpty()) {
					firstNodeStack.push(root);
				}
				
				while (!firstNodeStack.isEmpty()) {
					
					BSTreeNode<E> prev = firstNodeStack.pop();					
					secondNodeStack.push(prev);
					
					if (prev.hasLeftChild()) {
						firstNodeStack.push(prev.getLeft());
					}
					if (prev.hasRightChild()) {
						firstNodeStack.push(prev.getRight());
					}
				}
				
				return secondNodeStack.pop().getElement();
			}
		
		};
		
		return it;
	}

	
}
