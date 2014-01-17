/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/



//___
// | |_  o  _     o  _    _|_|_  _    __  _  o __     _  |  _  _  _
// | | | | _>     | _>     |_| |(/_   |||(_| | | |   (_  | (_|_> _>
//The main class is under control of Ali Nazzal. DO NOT EDIT WITHOUT EXPLICIT PERMISSION!

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    InputManager IM;
    MotorControl MC;
    
    public void robotInit() {
        IM = new InputManager();
        MC = new MotorControl();
        IM.init();
        MC.init();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        //Nah

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        //This is supposed to loop on it's own but it doesn't 
        
        while(isOperatorControl() && isEnabled()){
            //This wont be so long in final version
            if(!IM.Stop.getState()){
            MC.Drive(IM.getPureAxis(), (IM.UnlockR1.getState()& IM.UnlockL1.getState() & IM.misc9.getState() & IM.misc10.getState()));
        }
            else{
                break;
            }
        }
        
    }
    
}
