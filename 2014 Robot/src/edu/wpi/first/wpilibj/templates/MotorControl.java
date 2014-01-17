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
    
    
    void init() {
        VDriveL[0] = new Victor(1);//Will be hard coded for this test
        VDriveL[1] = new Victor(8);
        VDriveR[0] = new Victor(9);
        VDriveR[1] = new Victor(10);
    }
    
        static double limit(double value, boolean unlocked) {
            if(unlocked){
        if (value < -1) {
            value = -1;
        }
        if (value > 1) {
            value = 1;
        }
            }
            else if(!unlocked){
        if (value < -.5) {
            value = -.5;
        }
        if (value > .5) {
            value = .5;
        }
            }
        return (value);
    }
        void Drive(double[] cmd, boolean unlocked){
            //left
            VDriveL[0].set(limit(cmd[0],unlocked));
            VDriveL[1].set(limit(cmd[0],unlocked));
            //Right
            VDriveR[0].set(limit(cmd[1],unlocked));
            VDriveR[1].set(limit(cmd[1],unlocked));
            
            
        }
        
    
    
}