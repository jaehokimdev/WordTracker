package utilities;

import java.util.NoSuchElementException;
import java.util.Stack;

import exception.TreeException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT {
	
	private static final long serialVersionUID = 1L;

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
		if (root == null) {
			throw new TreeException();
		}
		
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
		if (root == null) {
			throw new TreeException();
		}
		
		BSTreeNode<E> current = root;
		
		int compareResult;
				
		while (!current.isLeaf()) {
			compareResult = entry.compareTo(current.getElement());

			if (compareResult < 0) {
				current = current.getLeft();
			}else if (compareResult > 0) {
				current = current.getRight();
			}else {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public BSTreeNode search(Comparable entry) throws TreeException {
		if (root == null) {
			throw new TreeException();
		}
		
		BSTreeNode<E> current = root;
		
		int compareResult;
				
		while (!current.isLeaf()) {
			compareResult = entry.compareTo(current.getElement());

			if (compareResult < 0) {
				current = current.getLeft();
			}else if (compareResult > 0) {
				current = current.getRight();
			}else {
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
				BSTreeNode<E> nextNode = null;
				
				nodeStack.push(current);
				
				if (!nodeStack.isEmpty()) {
					nextNode = nodeStack.pop();
					
					if (current.hasRightChild()) {
						nodeStack.push(nextNode.getRight());
					}
					if (current.hasLeftChild()) {
						nodeStack.push(nextNode.getLeft());
					}
				}
				
				return nextNode.getElement();
			}
			
		};
		
		return it;
	}

	@Override
	public Iterator<E> postorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
