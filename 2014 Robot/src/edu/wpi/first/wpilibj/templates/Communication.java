package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.*;

/**
 * This class is responsible for all communication with other devices such as the Raspberry pie, the driver's station, some 
 * cameras, and anything else external.
 * @author Alex, Evan
 */
public class Communication {
    
    public void RobotSpeed(double getPureAxis){
         String RSpeed = Double.toString(getPureAxis);
         
         if (!RSpeed.equals(" ")){
             String Speed = ""
             SmartDashboard.putString(RSpeed, RSpeed);
         }
    }
}
