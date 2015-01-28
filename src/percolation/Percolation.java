
/**
 * @author in order of appearance: David Weber, Vance Green
 * @Date created: 1/24/2015 - David Weber
<<<<<<< HEAD
 * 
 * @Date last modified: 1/25/2015 - David Weber
=======
 * @Date last modified: 1/27/2015 - Vance Green
>>>>>>> branch 'master' of https://github.com/davidlweber/2420_Assignments.git
 */
package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int size;
	// private int[][] percolationBoard; //open=1; close=0; ??
	private boolean[] openCloseBoard; // open=true; close=false; ??
	// don't forget arrayOffsets
	// how to get index of openCloseBoard, where to find number?
	private WeightedQuickUnionUF wquUF;
	private int topConnector;
	private int bottomConnector;

	/**
	 * create N­by­N grid, with all sites blocked 
	 * 
	 * @param N = size
	 */
	public Percolation(int n) {
		if (n < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		size = n;
		wquUF = new WeightedQuickUnionUF(n * n + 2); // plus two? include virtual spots
		openCloseBoard = new boolean[n * n];
		topConnector = n * n;
		bottomConnector = n * n + 1;
	}

	/**
	 * is site (row i, column j) full? 
	 * 
	 * @param i = row
	 * @param j = column
	 * @return = whether (row i, column j) is full, true if full
	 */
	public boolean isFull(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			throw new IndexOutOfBoundsException("row = " + row + " col = " + col);
		} else {
			return wquUF.connected(topConnector, (row) * size + (col));
		}
	}

	/**
	 * is site (row i, column j) open? 
	 * 
	 * @param i = row
	 * @param j = column
	 * @return whether (row i, column j) is open, true if open
	 */
	private boolean isOpen(int index) {
		return openCloseBoard[index];
	}
	
	public boolean isOpen(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			throw new IndexOutOfBoundsException("row = " + row + " col = " + col);
		} 			
		return isOpen((row) * size + (col));
	}

	/**
	 * does the system percolate?
	 * 
	 * @return whether the current system percolates, true if percolates
	 */
	public boolean percolates() {
		return wquUF.connected(topConnector, bottomConnector);
	}

	/**
	 * open site (row i, column j) if it is not open already
	 * 
	 * @param i = row
	 * @param j = column
	 */
	public void open(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			throw new IndexOutOfBoundsException("row = " + row + " col = " + col);
		} else {
			openCloseBoard[(row - 1) * size + (col - 1)] = true; // due to offset
		}		
		
//		 if (row == 0) {
//		 //top row must be connected to virtual section (first blue spot too)
//		 wquUF.union((row) * size + (col), topConnector);
//		 }
		
//		 if (row == size) {
//		 //bottom row must be connected to virtual section (last blue spot, defines percolation)
//		 wquUF.union(((row - 1) * size + (col - 1)), bottomConnector);
//		 }
		
//		 if (col > 1 && isOpen(row, col - 1)) {
//		 //if cell open to left, then union with it
//		 //col > 1 has to happen or forces index out of bounds
//		 wquUF.union((row) * size + (col), (row) * size + (col - 1));
//		 }
		
//		 if (col < size && isOpen(row, col + 1)) {
//		 //if cell open to right, then union with it
//		 wquUF.union((row) * size + (col), (row) * size + (col + 1));
//		 }
		
//		 if (row > 1 && isOpen(row - 1, col)) {
//		 //if cell open below, then union with it
//		 wquUF.union(findIndex(row, col), findIndex(row - 1, col));
//		 }
		
//		 if (row < size && isOpen(row + 1, col)) {
//		 //if cell open above, then union with it
//		 wquUF.union(findIndex(row, col), findIndex(row + 1, col));
//		 }
	}
}
