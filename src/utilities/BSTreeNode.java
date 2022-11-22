package utilities;

import java.io.Serializable;

public class BSTreeNode<E extends Comparable<? super E>> implements Serializable {

	private static final long serialVersionUID = 1L;

	private E element;
	private BSTreeNode<E> left, right;
	
	public BSTreeNode (E elem, BSTreeNode<E> left, BSTreeNode<E> right) {
		this.element = elem;
		this.left = left;
		this.right = right;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public BSTreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}

	public BSTreeNode<E> getRight() {
		return right;
	}

	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}
	
	
}
