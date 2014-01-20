/*This class is where all calls to the actual motor hardware should occur, they should be methods callable from the main function.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;

public class MotorControl {
    
    static Victor[] VDriveL = new Victor[2];
    static Victor[] VDriveR = new Victor[2];
    static Jaguar[] JDriveL = new Jaguar[2];
    static Jaguar[] JDriveR = new Jaguar[2];
    static RobotMap R;
    void init() {
        VDriveL[0] = new Victor(R.lm1);//Will be hard coded for this test
        VDriveL[1] = new Victor(R.lm2);
        VDriveR[0] = new Victor(R.rm1);
        VDriveR[1] = new Victor(R.rm2);
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
            VDriveL[0].set(limit(turnboost(cmd[0]),unlocked));
            VDriveL[1].set(limit(turnboost(cmd[0]),unlocked));
            //Right
            VDriveR[0].set(limit(turnboost(cmd[1]),unlocked));
            VDriveR[1].set(limit(turnboost(cmd[1]),unlocked));
            
            
        }
       protected static double turnboost(double cmd){
           
           return cmd;
       }
        
    
    
}