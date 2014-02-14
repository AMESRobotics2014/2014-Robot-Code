package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import java.util.Timer;
import java.util.TimerTask;
import edu.wpi.first.wpilibj.templates.MotorControl;

/**
 *This class is for code that simulates dynamics of the robot and physical world to predict real world events. 
 * It should take data from human users, various parts of image processing, and other inputs to be developed as the build continues. 
 * It should be organized to be accessible from the main class. All elements static. Use class, not instance.
 * All units measured in inches, inches/second, and degrees.
 * 
 * @author Collin, Jonah, Adam
 */
public abstract class Simulator {
    /**
     * 
     * @param angle the desired angle
     * @param shooterLength the distance between the shaft and the hinge
     * @param shaftLength the length of the shaft
     * @return the appropriate length of the base
     */
    static final double shooterLength=18;
    static final double shaftLength=17;
    public double getBaseDistance(double angle) {
        return Math.sqrt(shooterLength*shooterLength+shaftLength*shaftLength-
                2*shooterLength*shaftLength*Math.cos(Math.toRadians(angle)));
    }
    /**
     * 
     * @param pullDistance the distance the sling is pulled from the point of 0 resistance;
     * @return the speed that would be achieved.
     */
    static double speed(double pullDistance)  {
        return pullDistance*ELASTIC_CONSTANT;
    }
    /**
     * 
     * @param speed the speed requested.
     * @return the distance from the point of 0 resistance that would achieve the speed.
     */
    static double distance(double speed)  {
        return speed/ELASTIC_CONSTANT;
    }
    /**the speed of the per inch pulled.
     * 
     */
    static double ELASTIC_CONSTANT=0;
    /* 
    public static void calibrate() {
        MotorControl.pullbackMotor1.set(1);
        MotorControl.pullbackMotor2.set(1);
        Timer timer=new Timer();
        timer.schedule(new TimerTask()  {
            public void run()   {
                MotorControl.pullbackMotor1.free();
                MotorControl.pullbackMotor2.free();
                //measure ball speed
                //ELASTIC_CONSTANT=BallSpeed;
            }
        }, 1/*motorspeed(in in/sec)*gearRatio/1.25/Math.PI*1000);
    }*/
    /*
     * determines the lower angle of trajectory to hit the target.
     * @param speed the speed of the projectile in in/s
     * @param distance the relative distance of the target in inches
     * @param height the relative elevation of the target in inches
     * @param ceilingHeight the relative height of the ceiling in inches
     * @return the angle that will hit the target in degrees (takes into account gravity but not air resistance.)
    */ 
    public static double getLowAngle(double speed, double distance,
            double height, double ceilingHeight)
                throws HitCeilingException,NotEnoughSpeedException{
        double a=193.06/speed/speed/Math.cos(Math.toRadians(45))
                /Math.cos(Math.toRadians(45));
        double b=Math.sin(Math.toRadians(45))/Math.sin(Math.toRadians(45));
        double c=-height;
        double dif=(-b-Math.sqrt(b*b-4*a*c))/2/a;
        a=193.06/speed/speed/Math.cos(Math.toRadians(0))
                /Math.cos(Math.toRadians(0));
        b=Math.sin(Math.toRadians(0))/Math.sin(Math.toRadians(0));
        c=-height;
        double prevdif=(-b-Math.sqrt(b*b-4*a*c))/2/a;
        double angle=45;
        for(int i=1;i<10;i++)   {
            a=193.06/speed/speed/Math.cos(Math.toRadians(angle))
                /Math.cos(Math.toRadians(angle));
            b=Math.sin(Math.toRadians(angle))/Math.sin(Math.toRadians(angle));
            c=-height;
            dif=(-b-Math.sqrt(b*b-4*a*c))/2/a;
            if(dif<0)   {
                angle+=45/2^i;
            } else if(dif>0)    {
                angle-=45/2^i;
            } else if(dif==0)   {
                return angle;
            }
            
            if(Math.abs(prevdif)<Math.abs(dif)&&prevdif/Math.abs(prevdif)==
                    dif/Math.abs(dif))  {
                throw new NotEnoughSpeedException();
            }
            prevdif=dif;
            
        }
        
        
        if((Math.sin(Math.toRadians(angle))*Math.sin(Math.toRadians(angle))/2/386.12)>=ceilingHeight)    {
            throw new HitCeilingException();
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
    */ 
    public static double getLobAngle(double speed, double distance, double height,
            double ceilingHeight)
    throws HitCeilingException,NotEnoughSpeedException{
        double a=193.06/speed/speed/Math.cos(Math.toRadians(45))
                /Math.cos(Math.toRadians(45));
        double b=Math.sin(Math.toRadians(45))/Math.sin(Math.toRadians(45));
        double c=-height;
        double dif=(-b+Math.sqrt(b*b-4*a*c))/2/a;
        a=193.06/speed/speed/Math.cos(Math.toRadians(0))
                /Math.cos(Math.toRadians(0));
        b=Math.sin(Math.toRadians(0))/Math.sin(Math.toRadians(0));
        c=-height;
        double prevdif=(-b+Math.sqrt(b*b-4*a*c))/2/a;
        double angle=45;
        for(int i=1;i<10;i++)   {
            a=193.06/speed/speed/Math.cos(Math.toRadians(angle))
                /Math.cos(Math.toRadians(angle));
            b=Math.sin(Math.toRadians(angle))/Math.sin(Math.toRadians(angle));
            c=-height;
            dif=(-b+Math.sqrt(b*b-4*a*c))/2/a;
            if(dif<0)   {
                angle+=45/2^i;
            } else if(dif>0)    {
                angle-=45/2^i;
            } else if(dif==0)   {
                return angle;
            }
            
            if(Math.abs(prevdif)<Math.abs(dif)&&prevdif/Math.abs(prevdif)==
                    dif/Math.abs(dif))  {
                throw new NotEnoughSpeedException();
            }
            prevdif=dif;
            
        }
        
        
        if((speed/286.12*Math.sin(Math.toRadians(angle)))*(speed/286.12*Math.sin(Math.toRadians(angle)))/2>=ceilingHeight)    {
            throw new HitCeilingException();
        }
        return angle;
    }
    
    /**
     * 
     * @param angle the angle of trajectory in degrees
     * @param speed the speed of the ball in in/s
     * @param targetRelativeHeight the relative height of the target in inches
     * @return  a distance that will alow the robot to hit the target.
     */
    //problem found in java.lang.Math. due to this, no prediction software currently works
    public static double getDistance(double angle, double speed,
            double height,double ceilingHeight)   
            throws HitCeilingException,NotEnoughSpeedException{
        double a=193.06/speed/speed/Math.cos(Math.toRadians(angle))/Math.cos(Math.toRadians(angle));
        double b=speed*Math.sin(Math.toRadians(angle))/speed/Math.cos(Math.toRadians(angle));
        double c=-height;
        if(b*b-4*a*c<0) throw new NotEnoughSpeedException();
        double distance=(-b-Math.sqrt(b*b-4*a*c))/2/a;
        if((speed/286.12*Math.sin(Math.toRadians(angle)))*(speed/286.12*Math.sin(Math.toRadians(angle)))/2>=ceilingHeight)    {
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
        double a=193.06/speed/speed/Math.cos(Math.toRadians(angle))/Math.cos(Math.toRadians(angle));
        double b=speed*Math.sin(Math.toRadians(angle))/speed/Math.cos(Math.toRadians(angle));
        double c=-height;
        if(b*b-4*a*c<0) throw new NotEnoughSpeedException();
        double distance=(-b+Math.sqrt(b*b-4*a*c))/2/a;
        if((speed/286.12*Math.sin(Math.toRadians(angle)))*(speed/286.12*Math.sin(Math.toRadians(angle)))/2>=ceilingHeight)    {
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
    throws HitCeilingException,AngleException{
        double a=-relativeHeight+distance*Math.sin(Math.toRadians(angle))/Math.cos(Math.toRadians(angle));
        double b=0;
        double c=distance*distance*193.06/Math.cos(Math.toRadians(angle))/Math.cos(Math.toRadians(angle));
        double speed=(-b+Math.sqrt(b*b-4*a*c))/2/a;
        if(b*b-4*a*c<0) throw new AngleException();
        if((speed/286.12*Math.sin(Math.toRadians(angle)))*(speed/286.12*Math.sin(Math.toRadians(angle)))/2>=ceilingHeight)    {
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
            if(Math.abs(trussHeight-(trussDistance/speed/Math.cos(Math.toRadians(angle))*
                    ((trussDistance/speed/Math.cos(Math.toRadians(angle))-2*speed*Math.sin(Math.toRadians(angle))))))<30) {
                throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(Math.toRadians(angle))*
                    ((trussDistance/speed/Math.cos(Math.toRadians(angle))-2*speed*Math.sin(Math.toRadians(angle))))));
            }
            
        double a=193.06/speed/speed/Math.cos(Math.toRadians(angle))/Math.cos(Math.toRadians(angle));
        double b=speed*Math.sin(Math.toRadians(angle))/speed/Math.cos(Math.toRadians(angle));
        double c=-trussHeight;
        if((-b+Math.sqrt(b*b-4*a*c))/2/a-trussDistance<30)
            throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(Math.toRadians(angle))*
                    ((trussDistance/speed/Math.cos(Math.toRadians(angle))-2*speed*Math.sin(Math.toRadians(angle))))));
        if((-b-Math.sqrt(b*b-4*a*c))/2/a-trussDistance<30)
            throw new TrussException(true,trussHeight-(trussDistance/speed/Math.cos(Math.toRadians(angle))*
                    ((trussDistance/speed/Math.cos(Math.toRadians(angle))-2*speed*Math.sin(Math.toRadians(angle))))));
        
        }
        throw new TrussException(false,trussHeight-(trussDistance/speed/Math.cos(Math.toRadians(angle))*
                    ((trussDistance/speed/Math.cos(Math.toRadians(angle))-2*speed*Math.sin(Math.toRadians(angle))))));
    }
    /**
     * 
     * @param speed the ball speed 
     * @param height the target height
     * @return the distance that would allow the ball to hit the target perpendicular to the ground
     */
    public static double getPrimeDistance(double speed, double height) {
        return Math.sqrt(speed*speed-Math.sqrt(height/193.06)*386.12
                *Math.sqrt(height/193.06)*386.12)*Math.sqrt(height/193.06);
    }
    /**
     * 
     * @param speed the ball speed 
     * @param height the target height
     * @return the angle that would allow the ball to hit the target perpendicular to the ground
     */
    public static double getPrimeAngle(double speed, double height) throws NotEnoughSpeedException {
        try {
            return Simulator.getLowAngle(speed,getPrimeDistance(speed,height),height,100000);
        } catch (HitCeilingException ecp){return 0;}
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
