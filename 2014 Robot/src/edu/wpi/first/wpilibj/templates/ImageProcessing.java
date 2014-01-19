

package edu.wpi.first.wpilibj.templates;

public class ImageProcessing {
    /**
     * 
     * @param semiBaseline half the distance between cameras
     * @param leftAngle the horrizantal angle in the left camera
     * @param rightAngle the horrizantal angle in the right camera
     * @return  the distance directly forward from a point halfway in between both cameras
     */
    public static double getDistance(double semiBaseline, double leftAngle, double rightAngle)  {
        if(rightAngle==90)   {
            return semiBaseline*2*Math.tan(leftAngle/180*Math.PI);
        }
        if(leftAngle==90)   {
            return semiBaseline*-2*Math.tan(rightAngle/180*Math.PI);
        }
        double x=(semiBaseline*Math.tan(rightAngle/180*Math.PI)-semiBaseline*Math.tan(leftAngle/180*Math.PI))/
                (Math.tan(leftAngle/180*Math.PI)-Math.tan(rightAngle/180*Math.PI));
        return (x+semiBaseline)*Math.tan(leftAngle/180*Math.PI);
    }
    /**
     * 
     * @param semiBaseline half the distance between the cameras
     * @param leftAngle the horrizantal angle in the left camera
     * @param rightAngle the horrizantal angle in the right camera
     * @return the distance to the right from a point halfway in between the cameras
     */
    public static double getOffset(double semiBaseline, double leftAngle, double rightAngle)  {
        if(rightAngle==90)   {
            return semiBaseline;
        }
        if(leftAngle==90)   {
            return -semiBaseline;
        }
        return (semiBaseline*Math.tan(rightAngle/180*Math.PI)-semiBaseline*Math.tan(leftAngle/180*Math.PI))/
                (Math.tan(leftAngle/180*Math.PI)-Math.tan(rightAngle/180*Math.PI));
    }
    /**
     * 
     * @param semiBaseline half the distance between the cameras
     * @param leftAngle the horrizantal angle in the left camera
     * @param rightAngle the horrizantal angle in the right camera
     * @param declination the declination of the object
     * @return the elevation above the cameras
     */
    public static double getHeight(double semiBaseline, double leftAngle, double rightAngle, double declination)  {
        double y=0;
        if(rightAngle==90)   {
            y=semiBaseline*2*Math.tan(leftAngle/180*Math.PI);
        }
        if(leftAngle==90)   {
            y=semiBaseline*-2*Math.tan(rightAngle/180*Math.PI);
        }
        double x=(semiBaseline*Math.tan(rightAngle/180*Math.PI)-semiBaseline*Math.tan(leftAngle/180*Math.PI))/
                (Math.tan(leftAngle/180*Math.PI)-Math.tan(rightAngle/180*Math.PI));
        y=(x+semiBaseline)*Math.tan(leftAngle/180*Math.PI);
        return y*Math.tan(declination/180*Math.PI);
    }
}
