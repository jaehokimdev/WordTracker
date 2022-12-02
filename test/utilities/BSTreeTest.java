/**
 * 
 */
package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.TreeException;

/**
 * @author joekim
 *
 */
class BSTreeTest {
	
	private BSTree<Integer> myBSTree;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.myBSTree = new BSTree<Integer>();
		
		myBSTree.add(20);
		myBSTree.add(10);
		myBSTree.add(5);
		myBSTree.add(1);
		myBSTree.add(7);
		myBSTree.add(15);
		myBSTree.add(30);
		myBSTree.add(25);
		myBSTree.add(35);
		myBSTree.add(32);
		myBSTree.add(40);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		myBSTree.clear();
	}

	/**
	 * Test method for {@link utilities.BSTree#getRoot()}.
	 * @throws TreeException 
	 */
	@Test
	void testGetRoot() throws TreeException {
		int root = (int) this.myBSTree.getRoot().getElement();
		
		assertEquals(20, root);	
	}

	/**
	 * Test method for {@link utilities.BSTree#getHeight()}.
	 */
	@Test
	void testGetHeight() {
		int height = this.myBSTree.getHeight();
		
		assertEquals(4, height);	
	}

	/**
	 * Test method for {@link utilities.BSTree#size()}.
	 */
	@Test
	void testSize() {
		int size = this.myBSTree.size();
		
		assertEquals(11, size);	
	}

	/**
	 * Test method for {@link utilities.BSTree#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		assertEquals(false, myBSTree.isEmpty());
		
		myBSTree.clear();
		assertEquals(true, myBSTree.isEmpty());	
	}

	/**
	 * Test method for {@link utilities.BSTree#clear()}.
	 */
	@Test
	void testClear() {
		myBSTree.clear();
		
		int size = this.myBSTree.size();
		
		assertEquals(0, size);	
	}

	/**
	 * Test method for {@link utilities.BSTree#contains(java.lang.Comparable)}.
	 * @throws TreeException 
	 */
	@Test
	void testContains() throws TreeException {
		boolean isThere = myBSTree.contains(10);
		boolean isThere2 = myBSTree.contains(7);
		
		assertEquals(true, isThere);	
		assertEquals(true, isThere2);
	
	}

	/**
	 * Test method for {@link utilities.BSTree#search(java.lang.Comparable)}.
	 * @throws TreeException 
	 */
	@Test
	void testSearch() throws TreeException {
		BSTreeNode<Integer> searchingNode = myBSTree.search(10);
		int searchElement = searchingNode.getElement();
		
		assertEquals(10, searchElement);	
	}

	/**
	 * Test method for {@link utilities.BSTree#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		assertEquals(true, myBSTree.add(300));
		assertEquals(12, myBSTree.size());
	}

	/**
	 * Test method for {@link utilities.BSTree#inorderIterator()}.
	 */
	@Test
	void testInorderIterator() {
		int next;
		Iterator<Integer> it = myBSTree.inorderIterator();
		int[] integerArray = new int[] {1, 5, 7, 10, 15, 20, 25, 30, 32, 35, 40};
		
		for (int i = 0; i < myBSTree.size(); i++)	{
			next = it.next();
			assertEquals(integerArray[i], next);
		}	
	}

	/**
	 * Test method for {@link utilities.BSTree#preorderIterator()}.
	 */
	@Test
	void testPreorderIterator() {
		int next;
		Iterator<Integer> it = myBSTree.preorderIterator();
		int[] integerArray = new int[] {20, 10, 5, 1, 7, 15, 30, 25, 35, 32, 40};
		
		for (int i = 0; i < myBSTree.size(); i++)	{
			next = it.next();
			assertEquals(integerArray[i], next);
		}
	}

	/**
	 * Test method for {@link utilities.BSTree#postorderIterator()}.
	 */
	@Test
	void testPostorderIterator() {
		int next;
		Iterator<Integer> it = myBSTree.postorderIterator();
		int[] integerArray = new int[] {1, 7, 5, 15, 10, 25, 32, 40, 35, 30, 20};
		
		for (int i = 0; i < myBSTree.size(); i++)	{
			next = it.next();
			assertEquals(integerArray[i], next);
		}
	}

}
