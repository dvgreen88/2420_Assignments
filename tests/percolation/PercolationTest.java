package percolation;

import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

	Percolation perc = new Percolation(10);
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIsFullOutOfBounds() {
		perc.isFull(-1,0);
		perc.isFull(0,-1);
		perc.isFull(10,10);
	}
	@Test
	public void testIsFull() {
		assertEquals(false,perc.isFull(0,0));
		assertEquals(false,perc.isFull(9,9));
	}
	
	/*
	@Test
	public void testNumberOfOpenSites() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testIsOpen() {
		fail("Not yet implemented");
	}

	@Test
	public void testPercolates() {
		fail("Not yet implemented");
	}

	@Test
	public void testOpen() {
		fail("Not yet implemented");
	}
	*/
}
