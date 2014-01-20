/*This class is where all calls to the actual motor hardware should occur, they should be methods callable from the main function.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;

public class MotorControl {
    
    //static Victor[] VDriveL = new Victor[2];
    //static Victor[] VDriveR = new Victor[2];
    static Jaguar JDriveL;
    static Jaguar JDriveR;
    static RobotMap R;
    void init() {
        JDriveL = new Jaguar(R.lm1);
        JDriveR = new Jaguar(R.rm1);
        R = new RobotMap();
    }
    
       protected static double limit(double value, boolean unlocked) {
            if(unlocked){
        if (value < -1) {
            value = -1;
        }
        if (value > 1) {
            value = 1;
        }
            }
            else if(!unlocked){
        if (value < -R.defaultlimit) {
            value = -R.defaultlimit;
        }
        if (value > R.defaultlimit) {
            value = R.defaultlimit;
        }
            }
        return (value);
    }
       public void Drive(double[] cmd, boolean unlocked){
            //left
            JDriveL.set(limit(turnboost(cmd[0]),unlocked));
            JDriveR.set(limit(turnboost(cmd[1]),unlocked));
            //Right
            
        }
       protected static double turnboost(double cmd){
           
           return cmd;
       }
        
    
    
}