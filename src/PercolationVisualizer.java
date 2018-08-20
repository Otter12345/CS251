import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.UF;
import java.awt.*;
import java.util.ArrayList;



/**
 * Created by hexu1 on 9/5/2017.
 */
public class PercolationVisualizer {
    private static final int DELAY = 100;

    public static void draw(Percolation pc, int n){
        StdDraw.clear();
        StdDraw.setPenColor(Color.black);
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0,n);
        StdDraw.setYscale(0,n);
        StdDraw.filledSquare(n/2 ,n/2 , n/2);

        for (int i=0;i<n*n;i++){
            int x= i % n;
            int y=i / n;

                if (pc.isFull(x,y)) {StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    //System.out.println("i j fill " +  i + " " + j + " " + pc.isFull(i,j));
                }
                else if (pc.isOpen(x,y)) {StdDraw.setPenColor(Color.white);
                   // System.out.println("i j open " +  i + " " + j + " " + pc.isOpen(i,j));
                }
                else {StdDraw.setPenColor(Color.black);}

                StdDraw.filledSquare(x+0.5, y + 0.5,.45);
            }
    }

    public static void main(String args[]){
        int n = StdIn.readInt();
        if (n<0) throw new IllegalArgumentException("invalid input");
        Percolation percolation = new Percolation(n);

        StdDraw.show();
        draw(percolation, n);
        StdDraw.show();
        StdDraw.pause(DELAY);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            percolation.open(p,q);

            draw(percolation, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }

    }
}
