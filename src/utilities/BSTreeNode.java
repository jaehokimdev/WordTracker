package utilities;

import java.io.Serializable;

public class BSTreeNode<E> implements Serializable {

	private static final long serialVersionUID = 103125396562901601L;

	private E element;
	private BSTreeNode<E> left, right;
	
	public BSTreeNode (E elem) {
		this.element = elem;
		this.left = null;
		this.right = null;
	}
	
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
	
	public boolean hasLeftChild() {
		if (this.left != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean hasRightChild() {
		if (this.right != null) {
			return true;
		}
		return false;
	}
	
	public boolean isLeaf() {
		if (!hasLeftChild() && !hasRightChild()) {
			return true;
		}
		return false;
	}
	
	public int getNumberNodes() {
		if (element == null) {
			return 0;
		} else {
			int l = this.left.getNumberNodes();
			int r = this.right.getNumberNodes();
			
			return 1 + l + r;
		}
		
	}
	
	public int getHeight() {
		if (element == null) {
			return 0;
		} else if (this.isLeaf()) {
			return 1;
		} else {
			int heightLeft = this.left.getHeight();
			int heightRight = this.right.getHeight();
		
			if (heightLeft > heightRight) {
				return heightLeft + 1;
			} else {
				return heightRight + 1;
			}
		}
	}
}
