/**
 * @author in order of appearance: David Weber, Vance Green
 * @Date created: 1/24/2015 - David Weber
<<<<<<< HEAD
 * @Date last modified: 2/2/2015 - Vance Green
>>>>>>> branch 'master' of https://github.com/davidlweber/2420_Assignments.git
 */
package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int size;
	private boolean[][] openCloseBoard; // open=true; close=false; ??
	private WeightedQuickUnionUF wquUF;
	private WeightedQuickUnionUF backwashEraser;
	private int topConnector;
	private int bottomConnector;

	/**
	 * create N­by­N grid, with all sites blocked
	 *
	 * @param n = size
	 */
	public Percolation(int n) {
		if (n < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		size = n;
		wquUF = new WeightedQuickUnionUF(n * n + 2); // plus two? include
														// virtual spots
		backwashEraser = new WeightedQuickUnionUF(n * n + 1);
		openCloseBoard = new boolean[n][n];
		topConnector = n * n; // 10x10 = 100 (due to board its 100 on UF)
		bottomConnector = n * n + 1; // 10x10+1 = 101 (due to board its 101 on
										// UF)
	}

	/**
	 * is site (row, column) full?
	 *
	 * @param row = row
	 * @param col = column
	 * @return = whether (row i, column j) is full, true if full
	 */
	public boolean isFull(int row, int col) {
		if (validate(row, col)) {
			return backwashEraser.connected(findIndexFrom2d(row, col), topConnector);
		}
		return false;
	}

	/**
	 * is site (row i, column j) open?
	 *
	 * @param row = row
	 * @param col = column
	 * @return whether (row i, column j) is open, true if open
	 */
	public boolean isOpen(int row, int col) {
		if (validate(row, col)) {
			return openCloseBoard[row][col];
		} else {
			return false;
		}
	}

	/**
	 * does the system percolate?
	 *
	 * @return whether the current system percolates, true if percolates
	 */
	public boolean percolates() {
		return wquUF.connected(topConnector, bottomConnector);
	}

	/**
	 * open site (row i, column j) if it is not open already
	 *
	 * @param row = row
	 * @param col = column
	 */
	public void open(int row, int col) {
		if (validate(row, col)) {
			openCloseBoard[row][col] = true;
			if (row == 0) {
				wquUF.union(findIndexFrom2d(row, col), topConnector);
				backwashEraser.union(findIndexFrom2d(row, col), topConnector);
			}
			
			if (row == size - 1) {
				wquUF.union(findIndexFrom2d(row, col), bottomConnector);
			}
			
			if (row > 0 && isOpen(row - 1, col)) {
				wquUF.union(findIndexFrom2d(row, col), findIndexFrom2d(row - 1, col));
				backwashEraser.union(findIndexFrom2d(row, col),	findIndexFrom2d(row - 1, col));
			}
			
			if (row < size - 1 && isOpen(row + 1, col)) {
				wquUF.union(findIndexFrom2d(row, col), findIndexFrom2d(row + 1, col));
				backwashEraser.union(findIndexFrom2d(row, col), findIndexFrom2d(row + 1, col));
			}
			
			if (col > 0 && isOpen(row, col - 1)) {
				wquUF.union(findIndexFrom2d(row, col), findIndexFrom2d(row, col - 1));
				backwashEraser.union(findIndexFrom2d(row, col), findIndexFrom2d(row, col - 1));
			}
			
			if (col < size - 1 && isOpen(row, col + 1)) {
				wquUF.union(findIndexFrom2d(row, col), findIndexFrom2d(row, col + 1));
				backwashEraser.union(findIndexFrom2d(row, col), findIndexFrom2d(row, col + 1));
			}
		}
	}

	/**
	 * Determines whether or not a row/column value pair are within bounds or
	 * not. If outside of bounds an IndexOutOfBounds exception is thrown,
	 * otherwise returns "true".
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean validate(int row, int col) {
		if (row < 0 || row > size || col < 0 || col > size) {
			throw new IndexOutOfBoundsException("row = " + row + " col = "
					+ col);
		} else {
			return true;
		}
	}

	private int findIndexFrom2d(int row, int col) {
		return (row * size) + col;
	}
}
