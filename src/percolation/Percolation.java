
/**
 * @author in order of appearance: David Weber, Vance Green
 * @Date created: 1/24/2015 - David Weber
 * @Date last modified: 1/27/2015 - Vance Green
 */
package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int size;
	// private int[][] percolationBoard; //open=1; close=0; ??
	private boolean[][] openCloseBoard; // open=true; close=false; ??
	// don't forget arrayOffsets
	// how to get index of openCloseBoard, where to find number?
	private WeightedQuickUnionUF wquUF;

	// private int singleTopSpot = 0; virtual top spot
	// private int singleBottomSpot; virtual bottom spot

	/**
	 * create N­by­N grid, with all sites blocked 
	 * 
	 * @param N = size
	 */
	public Percolation(int n) {
		size = n;
		wquUF = new WeightedQuickUnionUF(size * size); // plus two? include
		// virtual spots
		// singleBottomSpot = size+1; ?? or is it size*size+1
		openCloseBoard = new boolean[size][size];
		// need to create the boardutOfBoundsException("row index " + i +
		// " must be between 0 and " + (N-1));
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
			throw new IndexOutOfBoundsException();
		} else {

		}
		// throw IndexOutOfBoundsException
		// if (...) else throw IndexOutOfBoundsException
		return false;
	}

	/**
	 * is site (row i, column j) open? 
	 * 
	 * @param i = row
	 * @param j = column
	 * @return whether (row i, column j) is open, true if open
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			throw new IndexOutOfBoundsException();
		} else {
			return openCloseBoard[row][col];
		}
	}

	/**
	 * does the system percolate?
	 * 
	 * @return whether the current system percolates, true if percolates
	 */
	public boolean percolates() {
		return wquUF.connected(0, size + 1);
	}

	/**
	 * open site (row i, column j) if it is not open already
	 * 
	 * @param i = row
	 * @param j = column
	 */
	public void open(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			throw new IndexOutOfBoundsException();
		} else {
			openCloseBoard[row - 1][col - 1] = true; // due to offset
		}
	}
}
