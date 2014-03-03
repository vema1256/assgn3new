
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
        	pointsorig[count] = new Point(xx[i], xx[i+1]);
        	count += 1;
        }


 		StdDraw.setXscale(0, 32768); 
        StdDraw.setYscale(0, 32768);
 		for (int i = 0; i < pointsorig.length; i++) {
 			Point px = pointsorig[i];
			Comparator slopeComparator =  px.SLOPE_ORDER;
 
        	Arrays.sort(pointsarr,  slopeComparator);

        	// Find slopes made from px to all other points
        	double []slopetopx = new double [pointsorig.length];

         	int curlidx = 0;
        	int curridx = 0;
  
        	double curslope = Double.NEGATIVE_INFINITY;
        	for (int j = 0; j < pointsorig.length; j++) {
        		slopetopx[j] = px.slopeTo(pointsarr[j]);
        		px.draw();
  
        		if ((curslope != slopetopx[j]) ||  (j == pointsorig.length-1)) {
        			if  ((curslope == slopetopx[j]) && (j == pointsorig.length-1))  curridx++;

        			int reqdelta = 2;
        			if (curslope ==  Double.NEGATIVE_INFINITY)  reqdelta = 3;

        			if (curridx - curlidx >= reqdelta) {
        			// Points arr from curlidx to curridx represents collinear points
        			// Sort the Array from pointsarr[curlidx] to  pointsarr[curridx]
         				int cN = curridx-curlidx + 2;
						if (curslope ==  Double.NEGATIVE_INFINITY)  cN = curridx-curlidx + 1;
						Point[] copypointsarr = new Point[cN]; 

						int countx = 0;
						copypointsarr[countx++] = px;
						for (int l = curlidx; l <= curridx; l++) {
							if (px.compareTo(pointsarr[l]) != 0) {
								copypointsarr[countx++] = pointsarr[l];
							}
						}
 
	        			Arrays.sort(copypointsarr);
 	        			if (px.compareTo(copypointsarr[0]) == 0) {
  	        				for (int idx = 0; idx < copypointsarr.length-1; idx++) {
        						copypointsarr[idx].drawTo(copypointsarr[idx+1]);
        						StdOut.printf("%s -> ", copypointsarr[idx]);
        					}
         					StdOut.printf("%s\n", copypointsarr[ copypointsarr.length-1]);
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