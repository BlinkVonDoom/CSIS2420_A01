
package a01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private int N;
    private boolean[][] grid;
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int HEAD;
    private int TAIL;
    private WeightedQuickUnionUF dualEnd;
    private WeightedQuickUnionUF singleEnd;
    
    // create N­by­N grid, with all sites blocked
    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException();
        };
        this.N = N;
        this.grid = new boolean[N][N];  
        this.HEAD = N * N;
        this.TAIL = N * N + 1;
    }
    
    private int flaten(int i, int j){
        return i * this.N + j;
    }
    
    private void unionUf(int a, int b){
        this.dualEnd.union(a, b);
        this.singleEnd.union(a, b);
    }
    
    // open site (row i, column j) if it is not open already
    public void open(int i, int j){
        try{
            i -= 1;
            j -= 1;
            this.grid[i][j] = true;
            for(int[]dir : this.dirs){
                int i1 = i + dir[0];
                int j1 = j + dir[1];
                if(i1 >= 0 && i1 < this.N && j1 >= 0 && j1 < this.N && this.grid[i1][j1]){
                    this.unionUf(this.flaten(i, j), this.flaten(i1, j1));
                }    
            }
            if (i == 0) {
                this.unionUf(this.flaten(i, j), this.HEAD);
            }
            if (i == this.N - 1) {
                this.dualEnd.union(this.flaten(i, j), this.TAIL);
            }
            
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
        
    } 
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        try{
            i -= 1;
            j -= 1;
            return this.grid[i][j];            
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
        
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j){
        
    }
    
    // does the system percolate?
    public boolean percolates(){
        
    }
}