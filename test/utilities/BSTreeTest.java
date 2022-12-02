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
 *  Test for BSTree
 * 
 * @author Jaeho Kim, Valentin Morales, Ho Chun Sun, Miller Gee
 * @version 1.1
 * Created: Nov 15, 2022
 * Updated: Dec 02, 2022
 * 
 */
class BSTreeTest {
	
	private BSTree<String> BSTreeTest;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.BSTreeTest = new BSTree<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		BSTreeTest.clear();
	}

	/**
	 * Test method for {@link utilities.BSTree#getRoot()}.
	 * @throws TreeException 
	 */
	@Test
	void testGetRoot() throws TreeException {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");

		
		String root = (String) this.BSTreeTest.getRoot().getElement();
		
		assertEquals("D", root);	
	}

	/**
	 * Test method for {@link utilities.BSTree#getHeight()}.
	 */
	@Test
	void testGetHeight() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		int height = this.BSTreeTest.getHeight();
		
		assertEquals(3, height);	
	}

	/**
	 * Test method for {@link utilities.BSTree#size()}.
	 */
	@Test
	void testSize() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		int size = this.BSTreeTest.size();
		
		assertEquals(5, size);	
	}

	/**
	 * Test method for {@link utilities.BSTree#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		assertFalse(BSTreeTest.isEmpty());
		
		BSTreeTest.clear();
		assertTrue(BSTreeTest.isEmpty());	
	}

	/**
	 * Test method for {@link utilities.BSTree#clear()}.
	 */
	@Test
	void testClear() {
		BSTreeTest.clear();
		
		int size = this.BSTreeTest.size();
		
		assertEquals(0, size);	
	}

	/**
	 * Test method for {@link utilities.BSTree#contains(java.lang.Comparable)}.
	 * @throws TreeException 
	 */
	@Test
	void testContains() throws TreeException {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		boolean test = BSTreeTest.contains("A");
		boolean test2 = BSTreeTest.contains("Q");
		
		assertTrue(test);	
		assertFalse(test2);
	
	}

	/**
	 * Test method for {@link utilities.BSTree#search(java.lang.Comparable)}.
	 * @throws TreeException 
	 */
	@Test
	void testSearch() throws TreeException {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		BSTreeNode<String> searchingNode = BSTreeTest.search("A");
		String searchElement = searchingNode.getElement();
		
		assertEquals("A", searchElement);	
	}

	/**
	 * Test method for {@link utilities.BSTree#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		assertEquals(true, BSTreeTest.add("Q"));
		assertEquals(6, BSTreeTest.size());
	}

	/**
	 * Test method for {@link utilities.BSTree#inorderIterator()}.
	 */
	@Test
	void testInorderIterator() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		
		String next;
		Iterator<String> it = BSTreeTest.inorderIterator();
		String[] integerArray = new String[] {"A", "C", "D", "F", "G"};
		
		for (int i = 0; i < BSTreeTest.size(); i++)	{
			next = it.next();
			assertEquals(integerArray[i], next);
		}	
	}

	/**
	 * Test method for {@link utilities.BSTree#preorderIterator()}.
	 */
	@Test
	void testPreorderIterator() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		
		String next;
		Iterator<String> it = BSTreeTest.preorderIterator();
		String[] integerArray = new String[] {"D", "A", "C", "F", "G"};
		
		for (int i = 0; i < BSTreeTest.size(); i++)	{
			next = it.next();
			assertEquals(integerArray[i], next);
		}	
	}

	/**
	 * Test method for {@link utilities.BSTree#postorderIterator()}.
	 */
	@Test
	void testPostorderIterator() {
		BSTreeTest.add("D");
		BSTreeTest.add("A");
		BSTreeTest.add("C");
		BSTreeTest.add("F");
		BSTreeTest.add("G");
		
		String next;
		Iterator<String> it = BSTreeTest.postorderIterator();
		String[] integerArray = new String[] {"C", "A", "G", "F", "D"};
		
		for (int i = 0; i < BSTreeTest.size(); i++)	{
			next = it.next();
			assertEquals(integerArray[i], next);
		}
	}

}
