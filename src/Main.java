import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;

/**
 * Created by hexu1 on 9/8/2017.
 */
public class Main {
    public int size;
    public Main(int n){
        size = n;
    }
    public int getXposition(int p){
        return p % size;
    }

    public int getYposition(int p)
    {
        return p / size;
    }

    public static void main(String[] args) {
        int n = edu.princeton.cs.algs4.StdIn.readInt();         // N-by-N percolation system
        Main m = new Main(n);

        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            System.out.println(m.getXposition(p) + " " + m.getYposition(p));
        }
    }
}
