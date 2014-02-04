package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.*;

/**
 * This class is responsible for all communication with other devices such as the Raspberry pie, the driver's station, some 
 * cameras, and anything else external.
 * @author Alex, Evan
 */
//=================================================================================================================

public class Communication {
    
    public void RobotSpeed(double getPureAxis){
         String RSpeed = Double.toString(getPureAxis);
         
         if (!RSpeed.equals(" ")){
             String Speed = " ";
             SmartDashboard.putString(Speed, RSpeed);
                   }
    }   
 //=================================================================================================================
    public void RobotDirection(double UNKNOWN){
        String RDirection = Double.toString(UNKNOWN);
        
        if(!RDirection.equals(" ")){
            String Direction = " ";
            SmartDashboard.putString(Direction, RDirection);
        }
    }
//================================================================================================================
    public void RobotAngle(double UNKNOWN){
        String RAngle = Double.toString(UNKNOWN);
        
        if(!RAngle.equals(" ")){
            String Angle = " ";
            SmartDashboard.putString(Angle, RAngle);
        }
    }
//==================================================================================================================
}
