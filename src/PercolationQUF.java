import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hexu1 on 9/10/2017.
 */
public class PercolationQUF {
    private boolean[] grid;
    private int size;
    private QuickFindUF quickFindUF;
    private int top;
    private int bottom;

    /*create a grid with size n*n. {B,O,F} = {-1,0,1}*/
    public PercolationQUF(int n) {
        if (n < 0) throw new IllegalArgumentException("invalid input");
        size = n;
        quickFindUF = new QuickFindUF(n*n+2);
        grid = new boolean[n*n];
        top = n*n;
        bottom = n*n + 1;

    }

    public boolean validate(int x, int y) {
        if ((x < 0 || x >= size || y < 0 || y >= size))
            throw new IllegalArgumentException("invalid input");

        return true;
    }

    public void open(int x, int y) {
        if (validate(x, y)) {
            grid[y*size + x] = true;
            int index = y*size + x;

            if(y>0 && isOpen(x, y - 1))
                quickFindUF.union(index, index - size);

            if(x>0 && isOpen(x - 1, y))
                quickFindUF.union(index, index - 1);

            if(x<size-1 && isOpen(x + 1, y)) quickFindUF.union(index, index + 1);

            if (y<size-1 && isOpen(x, y + 1)) quickFindUF.union(index, index + size);

            if (y == size - 1) quickFindUF.union(top, index);
            else if (y==0) quickFindUF.union(bottom,index);

        }

    }

    public boolean isOpen(int x, int y) {return grid[y*size + x];
    }

    /* check if there is a path connects the site(x,y) to the surface.
   */

    public boolean isFull(int x, int y){
         return quickFindUF.connected(y*size + x,top);
    }

    public boolean percolates(){
       return quickFindUF.connected(top,bottom);
    }

    public static void main(String args[]) {
        int n = StdIn.readInt();
        Stopwatch stopwatch = new Stopwatch();
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

        System.out.println(stopwatch.elapsedTime());
    }
}
