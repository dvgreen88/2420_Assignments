/**
 * @author in order of appearance: David Weber, Vance Green
 * @Date created: 1/24/2015 - David Weber
 * @Date last modified: 2/2/2015 - Vance Green
 */

package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	private static int t; // number of experiments
	private static int n; // N x N grid
	private static double[] doubleArray;
	private static double howManyOpen;
	private static double numToPercolation;
	private static int rowRandomValue;
	private static int colRandomValue;

	/**
	 * perform T independent experiments on an N x N grid both T & N >= 0
	 * 
	 * @param N = size of the grid (N row X N col)
	 * @param T = number of experiments
	 */
	public PercolationStats(int N, int T) {
		// perform T independent experiments on an N x N grid both T & N >= 0
		// possible loop - cycle through the number of experiments
		// loop inside loop - run through all (T) cases
		// run percolation through the loop?
		// randomizer? for which location to open StdRandom.uniform
		// randomizer from 1 -> n+1 (array offset)
		// int won't be less than 1 causing arrayOffset, -1 from indices
		// what goes in doubleArray?? counter (number of spots opened for
		// percolation)/N*N
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("N <= 0 || T <= 0");
		}

		t = T;
		n = N;
		
	}

	private static double runPercExperiments(int N) {
			Percolation p = new Percolation(N); // outside or inside loop?
			howManyOpen = 0;
			do {
				// opens two random values
				rowRandomValue = StdRandom.uniform(0, N);
				colRandomValue = StdRandom.uniform(0, N);
				// what if spot is already open?? check ifOpen
				// move howManyOpen to only increase if spot not already open
				if (!p.isOpen(rowRandomValue, colRandomValue)) {
					howManyOpen++;
					p.open(rowRandomValue, colRandomValue);
				}
			} while (!p.percolates());
			numToPercolation = howManyOpen / Math.pow(N, 2);
			return numToPercolation;
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

		return mean - ((1.96 * deviation) / Math.sqrt(t));
	}

	/**
	 * high = mean + ((1.96*deviation) / sqrt(T))
	 * 
	 * @return high endpoint of 95% confidence interval
	 */
	public double confidenceHigh() {
		double mean = mean();
		double deviation = stddev();

		return mean + ((1.96 * deviation) / Math.sqrt(t));
	}

	public static void main(String[] args) {
		PercolationStats percStats = new PercolationStats(100, 200);
		
		doubleArray = new double[t];
		
		for (int i = 0; i < t; i++) {
			doubleArray[i] = runPercExperiments(n);
		}
		

		StdOut.printf("Ran with PercolationStats(%d, %d) %n%n", n, t);
		
		StdOut.println("Mean():                 " + percStats.mean());
		StdOut.println("Standard Deviation :    " + percStats.stddev());
		StdOut.println("Low Confidence Int :    " + percStats.confidenceLow());
		StdOut.println("High Confidence Int:    " + percStats.confidenceHigh());
	}

}
