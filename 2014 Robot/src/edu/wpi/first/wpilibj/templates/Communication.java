package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for all communication with other devices such as the Raspberry pie, the driver's station, some 
 * cameras, and anything else external.
 * @author Alex, Evan, Devon(raspie), Collin, Erin
 */
//=================================================================================================================
public class Communication {
    //Creates a new void to access the robot speed which is located in the main class getPure access int. Then it will project the speed on a moniter using the Dashboard.
    public void RobotSpeed(double getPureAxis){
         String RSpeed = Double.toString(getPureAxis);
         
         if (!RSpeed.equals(" ")){
             String Speed = "Robot Speed: ";
             SmartDashboard.putString(Speed, RSpeed);
                   }
    }   
 //=================================================================================================================
    //Creates new void to access the robot direction, the direction is accessed under the main class in UNKNOWN. Then it will be presented on the Dashboard.
    public void RobotDirection(double UNKNOWN){
        String RDirection = Double.toString(UNKNOWN);
        
        if(!RDirection.equals(" ")){
            String Direction = "Robot Direction: ";
            SmartDashboard.putString(Direction, RDirection);
        }
    }
//================================================================================================================
    //Creates new viod to access the Robot angle and then like the other voids it prints it to the Dashboard.
    public void RobotAngle(double UNKNOWN){
        String RAngle = Double.toString(UNKNOWN);
        
        if(!RAngle.equals(" ")){
            String Angle = "Robot Angle: ";
            SmartDashboard.putString(Angle, RAngle);
        }
    }
//==================================================================================================================
    /*
     * all of the errors will check using an if then statement where if something is wrong with the Robot then it will
     * check witch error it is and then print whatever error that malfunction is defined under
     */
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
        
        public void run() {
            NetworkTable.setClientMode();
            NetworkTable.setIPAddress("10.32.43.2");
            NetworkTable table = NetworkTable.getTable("Table");
            
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(NetworkTablesDesktopClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                double dist = table.getNumber("Dist", 0.0);
                System.out.println("Dist: " + dist);
            }
        }
}
