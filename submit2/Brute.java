/*************************************************************************
 * Name: Mahesh Vemula
 * Email:  
 *
 * Compilation:  javac Brute.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: Brute Force method for finding out collinear points 
 *************************************************************************/


import java.util.Arrays;
public class Brute {
	public static void main(String[] args) {
        String fnm = args[0];
        In exp1 = new In(fnm);
         
        int[] xx  = exp1.readAllInts();
        Point[] pointsarr = new Point[xx[0]];
        int count = 0;
        for (int i = 1; i < xx.length; i = i+2) {
         	pointsarr[count++] = new Point(xx[i], xx[i+1]);
        }

        StdDraw.setXscale(0, 32768); 
        StdDraw.setYscale(0, 32768);
 
        for (int i = 0; i < pointsarr.length; i++) {
        	Point a =  pointsarr[i];
            a.draw();
        	for (int j = i+1; j < pointsarr.length; j++) {
        		Point b =  pointsarr[j];
        		double s1 = a.slopeTo(b);
        		for (int k = j+1; k < pointsarr.length; k++) {
        			Point c =  pointsarr[k];
        			double s2 = a.slopeTo(c);
        			for (int l = k+1; l < pointsarr.length; l++){
        				Point d =  pointsarr[l];
         				double s3 = a.slopeTo(d);
        				boolean sameline = (s1 == s2) && (s1 == s3);
        				if (sameline) {
        					Point[] pointsonline = new Point[4];
        					pointsonline[0] = a;
        					pointsonline[1] = b;
        					pointsonline[2] = c;
        					pointsonline[3] = d;
         					Arrays.sort(pointsonline);
        					
        					for (int idx=0; idx < pointsonline.length-1; idx++) {
        						
        						StdOut.printf("%s -> ", pointsonline[idx]);
        					}
        					StdOut.printf("%s\n", pointsonline[pointsonline.length-1]);
                            pointsonline[0].drawTo(pointsonline[pointsonline.length-1]);
         				}  
        			}
        		}
        	}	
        }
   	}
}