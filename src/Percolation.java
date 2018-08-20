import edu.princeton.cs.algs4.*;

/**
 * Created by hexu1 on 8/29/2017.
 */
public class Percolation {
    private boolean[] grid;
    private int size;
    private WeightedQuickUnionUF uf;
    private int top;
    private int bottom;

    /*create a grid with size n*n. {B,O,F} = {-1,0,1}*/
    public Percolation(int n) {
        if (n < 0) throw new IllegalArgumentException("invalid input");
        size = n;
        uf = new WeightedQuickUnionUF(n*n+2);
        grid = new boolean[n*n];
        top = n*n;
        bottom = n*n + 1;

    }

    public boolean validate(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new IllegalArgumentException("invalid input");

        return true;
    }

    public void open(int x, int y) {
        if (validate(x, y)) {
            grid[y*size + x] = true;
            int index = y*size + x;

            if(y>0 && isOpen(x, y - 1))
                    uf.union(y*size + x, index - size);

            if(x>0 && isOpen(x - 1, y))
                    uf.union(y*size + x, index - 1);

            if(x<size-1 && isOpen(x + 1, y))
                    uf.union(y*size + x, index + 1);

            if (y<size-1 && isOpen(x, y + 1)) uf.union(y*size + x, index + size);

            if (y == size - 1) uf.union(top,index);
            else if (y==0) uf.union(bottom,index);

        }

    }

    public boolean isOpen(int x, int y) {
         return grid[y*size + x];
    }

    /* check if there is a path connects the site(x,y) to the surface.
   */

    public boolean isFull(int x, int y){return uf.find(top) == uf.find(y*size + x);
    }

    public boolean percolates(){
        return uf.find(top) == uf.find(bottom);
    }


    public static void main(String args[]){
        int n = StdIn.readInt();
        Percolation pc = new Percolation(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            pc.open(p, q);
        }
            if (pc.percolates())
                StdOut.println("Yes");
            else
                StdOut.println("No");
 }
}
