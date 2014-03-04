
/*************************************************************************
 * Name: Mahesh Vemula
 * Email:  
 *
 * Compilation:  javac Fast.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: Fast  sorting based method for finding out collinear points 
 * 1) For each point use a comparator to sort the points by the slope made by this point.
 * 2) Find subarrays all of which have the same slope. Special care for the vertical lines
 *     made by currnet point  otherwise the current  appears twice  
 * 3) This subarray is then sorted again using the comparable interface. If the current point == first of the sorted array print the array
 * 
 *************************************************************************/


import java.util.Arrays;
import java.util.Comparator;

public class Fast {
    
   private static double convnegslope(double slope) {
        if( slope == Double.NEGATIVE_INFINITY){
            return -slope;
        }
        return slope;
   }

   public static void main(String[] args) {

        String fnm = args[0];
        In exp1 = new In(fnm);
        int[] xx  = exp1.readAllInts();
        Point[] pointsarr = new Point[xx[0]];
        Point[] pointsorig = new Point[xx[0]];
         
        int count = 0;
        // Reading all the points from a file
        for (int i = 1; i < xx.length; i = i+2) {
            pointsarr[count] = new Point(xx[i], xx[i+1]);
            count += 1;
        }

    Arrays.sort(pointsarr);
    for( int i = 0; i < pointsarr.length; i++)
      pointsorig[i] = pointsarr[i];

        StdDraw.setXscale(0, 32768); 
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < pointsorig.length; i++) {
            Point px = pointsorig[i];
            px.draw();
            Comparator slopeComparator =  px.SLOPE_ORDER;
            //StdOut.printf("Comparing for %s\n", px);

              // Copying the Already Sorted Array
            for( int cpidx = 0; cpidx < pointsorig.length; cpidx++)
                pointsarr[cpidx] = pointsorig[cpidx];

                 
            Arrays.sort(pointsarr,  slopeComparator);
            
            //for (int ll =0; ll < pointsarr.length; ll++)
                //StdOut.printf("Comparing from px %s to %s is %f\n", px, pointsarr[ll], px.slopeTo(pointsarr[ll]));
             
            // Find slopes made from px to all other points
            double []slopetopx = new double [pointsorig.length];

            int curlidx = 0;
            int curridx = 0;
  
            double curslope = Double.NEGATIVE_INFINITY;
            for (int j = 0; j < pointsorig.length; j++) {
                slopetopx[j] = px.slopeTo(pointsarr[j]);
                if ( slopetopx[j]  == Double.NEGATIVE_INFINITY )  slopetopx[j] = -slopetopx[j];
                
                if ((curslope != slopetopx[j]) ||  (j == pointsorig.length-1)) {
                    if  ((curslope == slopetopx[j]) && (j == pointsorig.length-1))  curridx++;

                    int reqdelta = 2;
                    if (curslope ==  Double.NEGATIVE_INFINITY || curslope == Double.POSITIVE_INFINITY)  reqdelta = 3;
                    //StdOut.printf( " Point i %d, curlidx %d curridx %d reqdelta %d %d\n", i, curlidx, curridx, reqdelta, px.compareTo(pointsarr[curlidx]) );

                    if (curridx - curlidx >= reqdelta) {
                    // Points arr from curlidx to curridx represents collinear points
                        if (px.compareTo(pointsarr[curlidx]) <= 0) {
                            StdOut.printf("%s -> ", px);
                            for (int idx = curlidx; idx < curridx; idx++) {
                                StdOut.printf("%s -> ", pointsarr[idx]);
                            }
                            StdOut.printf("%s\n", pointsarr[curridx]);
                  
                            px.drawTo(pointsarr[curridx]);
                        }
                        curlidx = j;
                        curridx = j;

                    } else {
                        curlidx = j;
                        curridx = j;
                    }
                }  

                if (curslope == slopetopx[j]) {
                    curridx = j;
                }
                curslope = slopetopx[j];
                // StdOut.printf( "After curlidx = %d curridx = %d curslope %f\n", curlidx, curridx, curslope);
            }


         }
         
    }
}