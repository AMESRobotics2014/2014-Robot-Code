/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *note: all units measured in inches, inches per second, or degrees and are relative to the shooter height.
 * @author jonah.barber
 */
public abstract class ThrowingTools {
    /* this code currently not working. do not use or delete.
    /*
     * determines the lower angle of trajectory to hit the target.
     * @param speed the speed of the projectile in in/s
     * @param targetDistance the relative distance of the target in inches
     * @param targetElevation the relative elevation of the target in inches
     * @param ceilingHeight the relative height of the ceiling in inches
     * @return the angle that will hit the target in degrees (takes into account gravity but not air resistance.)
     
    public static double getLowAngle(double speed, double targetDistance,
            double targetElevation, double ceilingHeight, double trussDistance, double trussHeight)
                throws HitCeilingException,NotEnoughSpeedException,TrussException{
        double angle=0;
        if((Math.sin(angle*180/Math.PI)*Math.sin(angle*180/Math.PI)/2/386.12)>=ceilingHeight)    {
            throw new HitCeilingException();
        }
        if(trussDistance>0&&trussDistance<targetDistance) {
            if(Math.abs(trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))))>30) {
                throw new TrussException(false,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
            } else throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
        }
        return angle;
    }
    /**
     * determines the higher angle of trajectory to hit the target.
     * @param speed the speed of the projectile in in/s
     * @param targetDistance the relative distance of the target in inches
     * @param targetElevation the relative elevation of the target in inches
     * @param ceilingHeight the relative height of the ceiling in inches
     * @return the angle that will hit the target in degrees (takes into account gravity but not air resistance.)
     
    public static double getLobAngle(double speed, double targetDistance, double targetElevation,
            double ceilingHeight, double trussDistance, double trussHeight)
    throws HitCeilingException,NotEnoughSpeedException,TrussException{
        double angle=0;
        if((Math.sin(angle*180/Math.PI)*Math.sin(angle*180/Math.PI)/2/386.12)>=ceilingHeight)    {
            throw new HitCeilingException();
        }
        if(trussDistance>0&&trussDistance<targetDistance) {
            if(Math.abs(trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))))>30) {
                throw new TrussException(false,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
            } else throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
        }
        return angle;
    }
    */
    /**
     * 
     * @param angle the angle of trajectory in degrees
     * @param speed the speed of the ball in in/s
     * @param targetRelativeHeight the relative height of the target in inches
     * @return  a distance that will alow the robot to hit the target.
     */
    
    public static double getDistance(double angle, double speed,
            double height,double ceilingHeight)   
            throws HitCeilingException,NotEnoughSpeedException{
        double a=193.06/speed/speed/Math.cos(angle*180/Math.PI)/Math.cos(angle*180/Math.PI);
        double b=speed*Math.sin(angle*2/Math.PI)/speed/Math.cos(angle*180/Math.PI);
        double c=-height;
        if(b*b-4*a*c<0) throw new NotEnoughSpeedException();
        double distance=(-b-Math.sqrt(b*b-4*a*c))/2/a;
        if((speed/286.12*Math.sin(angle*180/Math.PI))*(speed/286.12*Math.sin(angle/180*Math.PI))/2>=ceilingHeight)    {
            throw new HitCeilingException();
        }
        
        return distance;
    }
     /**
     * 
     * @param angle the angle of trajectory in degrees
     * @param speed the speed of the ball in in/s
     * @param targetRelativeHeight the relative height of the target in inches
     * @return  a distance that will alow the robot to hit the target.
     */
    public static double getFarDistance(double angle, double speed,
            double height,double ceilingHeight)   
            throws HitCeilingException,NotEnoughSpeedException{
        double a=193.06/speed/speed/Math.cos(angle)/Math.cos(angle);
        double b=speed*Math.sin(angle)/speed/Math.cos(angle);
        double c=-height;
        if(b*b-4*a*c<0) throw new NotEnoughSpeedException();
        double distance=(-b+Math.sqrt(b*b-4*a*c))/2/a;
        if((speed/286.12*Math.sin(angle/180*Math.PI))*(speed/286.12*Math.sin(angle/180*Math.PI))/2>=ceilingHeight)    {
            throw new HitCeilingException();
        }
        
        return distance;
    }
    /**
     * 
     * @param angle the angle of trajectory
     * @param distance the distance of the target in inches
     * @param relativeHeight the relative height of the target in inches
     * @return the speed that would hit the target
     */
    public static double getSpeed(double angle, double distance, double relativeHeight,
            double ceilingHeight)
    throws HitCeilingException{
        double a=-relativeHeight+distance*Math.sin(angle*180/Math.PI)/Math.cos(angle*180/Math.PI);
        double b=0;
        double c=distance*distance*193.06/Math.cos(angle*180/Math.PI)*Math.cos(angle*180/Math.PI);
        double speed=(-b+Math.sqrt(b*b-4*a*c))/2/a;
       // if(b*b-4*a*c<0) throw new AngleException();
        if((speed/286.12*Math.sin(angle*180/Math.PI))*(speed/286.12*Math.sin(angle/180*Math.PI))/2>=ceilingHeight)    {
            throw new HitCeilingException();
        }
        return speed;
    }
    /**
     * 
     * @param angle the angle of trajectory
     * @param speed the speed of trajectory
     * @param distance the distance of the target
     * @param relativeHeight the height of the target
     * @param trussDistance the distance of the truss
     * @param trussHeight the height of the truss
     * @throws robot.throwing.ThrowingTools.TrussException
     */
    public static void willHitTruss(double angle, double speed, double distance, double relativeHeight,
            double trussDistance,double trussHeight) throws TrussException   {
        if(trussDistance>0&&trussDistance<distance) {
            if(Math.abs(trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))))<30) {
                throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
            }
            
        double a=193.06/speed/speed/Math.cos(angle*180/Math.PI)/Math.cos(angle*180/Math.PI);
        double b=speed*Math.sin(angle*2/Math.PI)/speed/Math.cos(angle*180/Math.PI);
        double c=-trussHeight;
        if((-b+Math.sqrt(b*b-4*a*c))/2/a-trussDistance<30)
            throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
        if((-b-Math.sqrt(b*b-4*a*c))/2/a-trussDistance<30)
            throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
        
        }
        throw new TrussException(false,trussHeight-(trussDistance/speed/Math.cos(angle*180/Math.PI)*
                    ((trussDistance/speed/Math.cos(angle*180/Math.PI)-2*speed*Math.sin(angle*180/Math.PI)))));
    }
    /**
     * thrown if more speed is necessary.
     */
    public static class NotEnoughSpeedException extends Exception {
        public String getMessage()   {
            return "the speed givin is not able to hit the target";
        }
    }
    
    /**
     * thrown if the angle is not able to hit the target.
     */
    public static class AngleException extends Exception {
        public String getMessage()   {
            return "the angle givin is not able to hit the target";
        }
    }
    /**
     * thrown if the ball will hit the ceiling
     */
    public static class HitCeilingException extends Exception {
        public String getMessage()   {
            return "if thrown the projectile will hit the ceiling";
        }
    }
    /**
     * passes back weather the truss will be hit and the relative height the ball will pass over or below the truss.
     */
    public static class TrussException extends Exception {
        int netHeight;
        boolean hitTruss;
        public TrussException(boolean hitTruss, double netHeight)  {
            super();
            
        }
        public String getMessage()   {
            if(hitTruss) return "if thrown the ball will hit the truss.";
            else if(netHeight>0) return "the ball will go above the truss. this is not an error.";
            else return "the ball will go below the truss. this is not an error.";
        }
    }
}