/**
 * @author in order of appearance: David Weber, Vance Green
 * @Date created: 1/24/2015 - David Weber
 * @Date last modified: 1/28/2015 - Vance Green
 */

package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * @author Vance
 *
 */
public class PercolationStats {
	private static int T = 100; // number of experiments
	private static int N = 200; // N x N grid
	private static double[] doubleArray;
	private static double howManyOpen = 0;
	private double numToPercolation;
	private int randomValue1;
	private int randomValue2;

	/**
	 * perform T independent experiments on an N x N grid both T & N >= 0
	 * 
	 * @param N = size of the grid
	 * @param T = number of experiments
	 */
	public PercolationStats(int N, int T) {
		// perform T independent experiments on an N x N grid both T & N >= 0
		// possible loop - cycle through the number of experiments
		// loop inside loop - run through all (T) cases
		// randomizer? for which location to open StdRandom.uniform
		// what goes in doubleArray?? counter (number of spots opened for
		// percolation)/N*N
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("N <= 0 || T <= 0");
		}

		doubleArray = new double[T];
		for (int i = 0; i < T; i++) {
			Percolation p = new Percolation(N); // outside or inside loop?
			while (!p.percolates()) {
				// opens two random values
				randomValue1 = StdRandom.uniform(1, N + 1);
				randomValue2 = StdRandom.uniform(1, N + 1);
				// what if spot is already open?? check ifOpen
				// move howManyOpen to only increase if spot not already open
				if (!p.isOpen(randomValue1, randomValue2)) {
					howManyOpen++;
					p.open(randomValue1, randomValue2);
				}
			}
			numToPercolation = howManyOpen / N * N;
			doubleArray[i] = numToPercolation;
		}
	}

	/**
	 *  
	 * @return sample mean of percolation threshold
	 */
	public double mean() {
		// mean requires a double[] to be passed
		return StdStats.mean(doubleArray);
	}

	/**
	 * @return sample standard deviation of percolation threshold
	 */
	public double stddev() {
		// stddev requires a double[] to be passed
		return StdStats.stddev(doubleArray);
	}

	/**
	 * low = mean - ((1.96*deviation) / sqrt(T))
	 * 
	 * @return low endpoint of 95% confidence interval
	 */
	public double confidenceLow() {
		double mean = mean();
		double deviation = stddev();

		return mean - ((1.96 * deviation) / Math.sqrt(T));
	}

	/**
	 * high = mean + ((1.96*deviation) / sqrt(T))
	 * 
	 * @return high endpoint of 95% confidence interval
	 */
	public double confidenceHigh() {
		double mean = mean();
		double deviation = stddev();

		return mean + ((1.96 * deviation) / Math.sqrt(T));
	}

	public static double getHowManyOpen() {
		return howManyOpen;
	}

	public static void main(String[] args) {
		PercolationStats myPercolationStats = new PercolationStats(N, T);

		StdOut.printf("Ran with PercolationStats(%d, %d) %n", N, T);
		StdOut.println("Mean():              " + myPercolationStats.mean());
		StdOut.println("Standard Deviation:  " + myPercolationStats.stddev());
		StdOut.println("Low Confidence Int:  "
				+ myPercolationStats.confidenceLow());
		StdOut.println("High Confidence Int: "
				+ myPercolationStats.confidenceHigh());
	}

}
