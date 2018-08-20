import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


/**
 * Created by hexu1 on 9/9/2017.
 */
public class PercolationStats {
    private int size;
    private int max;
    private int[] random;

    public PercolationStats(int n, int T){
        if (n < 0 || T < 0) throw new IllegalArgumentException("invalid input");
        size = n;
        max = n*n;
        random = new int[max];

        for (int i=0;i<max;i++){
            random[i] = i;
        }
    }

    public double quickOpen(Percolation pc) {
        StdRandom.shuffle(random);
        int i = 0;

        while (!pc.percolates() && i < random.length) {
            int m = random[i];
            int x = getXposition(m);
            int y = getYposition(m);

                pc.open(x, y);
                i++;

       }

       return (double)i / max;
    }

    public double slowOpen(PercolationQUF percolationQUF){
        StdRandom.shuffle(random);
        int i=0;
        while(!percolationQUF.percolates() && i<random.length) {
            int m = random[i];
            int x = m % size;
            int y = m / size;

            percolationQUF.open(x, y);
            i++;
        }

        return (double)i / max;
    }

    public int getXposition(int p){
        return p % size;
    }

    public int getYposition(int p)
    {
        return p / size;
    }

    public static void main(String args[]){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        String type = args[2];

        double[] data = new double[T];
        double[] time = new double[T];
        double totalT = 0;


        PercolationStats ps =  new PercolationStats(n, T);
        if (type.equals("fast")) {
            for (int i=0;i<T;i++) {
                Stopwatch stopwatch = new Stopwatch();
                Percolation percolation = new Percolation(n);
                data[i] = ps.quickOpen(percolation);
                time[i] = stopwatch.elapsedTime();
                totalT = totalT + time[i];
            }
        } else if (type.equals("slow")){
            for (int i=0; i<T;i++) {
                Stopwatch stopwatch = new Stopwatch();
                PercolationQUF pq = new PercolationQUF(n);
                data[i] = ps.slowOpen(pq);
                time[i] = stopwatch.elapsedTime();
                totalT = totalT + time[i];
            }
        }

        else
            throw new IllegalArgumentException("invalid input");


        StdOut.println("mean threshold = " + StdStats.mean(data));
        StdOut.println("std dev = " + StdStats.stddev(data));
        StdOut.println("time = " + totalT);
        StdOut.println("mean time = " + StdStats.mean(time));
        StdOut.println("stddev time = " + StdStats.stddev(time));
    }
}
