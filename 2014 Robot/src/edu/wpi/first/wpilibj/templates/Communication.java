package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 * This class is responsible for all communication with other devices such as the Raspberry pie, the driver's station, some 
 * cameras, and anything else external.
 * @author Alex, Evan, Devon(rasepie), Collin, Erin
 */
//=================================================================================================================

public class Communication {
    
    public void RobotSpeed(double getPureAxis){
         String RSpeed = Double.toString(getPureAxis);
         
         if (!RSpeed.equals(" ")){
             String Speed = "Robot Speed: ";
             SmartDashboard.putString(Speed, RSpeed);
                   }
    }   
 //=================================================================================================================
    public void RobotDirection(double UNKNOWN){
        String RDirection = Double.toString(UNKNOWN);
        
        if(!RDirection.equals(" ")){
            String Direction = "Robot Direction: ";
            SmartDashboard.putString(Direction, RDirection);
        }
    }
//================================================================================================================
    public void RobotAngle(double UNKNOWN){
        String RAngle = Double.toString(UNKNOWN);
        
        if(!RAngle.equals(" ")){
            String Angle = "Robot Angle: ";
            SmartDashboard.putString(Angle, RAngle);
        }
    }
//==================================================================================================================
    public void ErrorOne(String EOne){
        
        if(!EOne.equals(" ")){
            SmartDashboard.putString("Error: ", EOne);
        }
    }
    //================================================================================================================
    // This class is acessing .equals and then using an if else statement
    public void ErrorTwo(String ETwo){
        
        if(!ETwo.equals(ETwo)){
            SmartDashboard.putString("Error: ", ETwo);
        }
    }
    //======================================================================================================================
    public void ErrorThree(String EThree){
        
        if(!EThree.equals(" ")){
            SmartDashboard.putString("Error: ", EThree);
        }
    }
    //===================================================================================================================
        public void ErrorFour(String EFour){

            if(!EFour.equals(" ")){
                SmartDashboard.putString("Error: ", EFour);
            }
        }
//===============================================================================
        public void ErrorFive(String EFive){

            if(!EFive.equals(" ")){
                SmartDashboard.putString("Error: ", EFive);
        }
        }
//===============================================================================
        public void ErrorSix(String ESix){
            
            if(ESix.equals(" ")){
                SmartDashboard.putString("Error: ", ESix);
            }
        }
//=====================================================================================================
        public void ErrorSeven(String ESeven){
            
            if(ESeven.equals(" ")){
                SmartDashboard.putString("Error: ", ESeven);
            }
        }
//=========================================================================================
    public class PISocket{
        boolean active;
        SocketConnection psock = null;
        Integer rcnum;
        int angleInt, heightInt, distanceInt, confInt;
        
        public PISocket(boolean activated) throws Exception{
            active = activated;
            psock = (SocketConnection) Connector.open("socket://127.0.0.1:3243");
            angleInt = 0;
            heightInt = 0;
            distanceInt = 0;
            confInt = 0;
            }
        public void GetData() throws Exception{
            InputStream is = psock.openDataInputStream();
            rcnum = new Integer(is.read());
            String strNumber = rcnum.toString();
            
            if(strNumber.charAt(0) == '4' && strNumber.charAt(5) == '4' && strNumber.charAt(8) == '4'){
                String angleX = strNumber.substring(0, 2);
                angleInt = Integer.parseInt(angleX);
                
                String heightY = strNumber.substring(3, 5);
                heightInt = Integer.parseInt(heightY);
                
                String distZ = strNumber.substring(6, 8);
                distanceInt = Integer.parseInt(distZ);
                
                String confLevel = strNumber.substring(9, 11);
                confInt = Integer.parseInt(distZ);
            } else {
                SmartDashboard.putString("Check Numbers", "Numbers Are Wrong");
            }
        }
//===============================================================================
    }
}
