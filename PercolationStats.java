
package a01;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {
    
    private double[] xs;
    private int T;
    
    // perform T independent experiments on an N­by­N grid
    public PercolationStats(int N, int T){
        if( N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        
        this.xs = new double[T];
        this.T = T;
        
        for(int t = 0; t < T; t++){
            Percolation percolation = new Percolation(N);
            int cnt = 0;
            
            while(!percolation.percolates()){
                cnt++;
                int i, j;
                do {
                    i = StdRandom.uniform(N)+1;
                    j = StdRandom.uniform(N)+1;
                } while(percolation.isOpen(i, j));
                percolation.open(i, j);
            }
            this.xs[t] = cnt / (double)(N*N);
        }
    }
    
    // sample mean of percolation threshold
    public double mean(){
        double result = 0;
        for(double xi: this.xs){
            result += xi;
        }
        result /= this.T;
        return result;
    }
            
    // sample standard deviation of percolation threshold
    public double stddev(){
        double result  = 0;
        double mean1 = this.mean();
        for(double xi: this.xs){
            result += (xi-mean1)*(xi-mean1);
        }
        result /= this.T-1;
        return Math.sqrt(result);
    }
            
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        double mean1 = this.mean();
        double stddev1 = this.stddev();
        return mean1 - 1.96 * stddev1 / Math.sqrt(this.T);
    }

    // high endpoint of 95% confidence interval        
    public double confidenceHigh() {
        double mean1 = this.mean();
        double stddev1 = this.stddev();
        return mean1 - 1.96 * stddev1 / Math.sqrt(this.T);
    }
    
    public static void main(String[] args){
        PercolationStats stats = new PercolationStats(5, 5);
        StdOut.println(stats.mean());
        StdOut.println(stats.stddev());
        StdOut.println(stats.confidenceLow());
        StdOut.println(stats.confidenceHigh());
    }
}
