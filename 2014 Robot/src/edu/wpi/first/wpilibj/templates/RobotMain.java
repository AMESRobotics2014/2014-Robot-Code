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
import edu.wpi.first.wpilibj.Watchdog;
//import edu.wpi.first.wpilibj.Timer;

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
    private static InputManager IM;
    private static MotorControl MC;
    private static boolean turbo;
    private static Watchdog wd;
    private static MasterTimer rt;

    public void robotInit() {
        IM = new InputManager();
        IM.init();
        MC = new MotorControl();
        MC.init();
        wd = Watchdog.getInstance();
        wd.setExpiration(.5);
        turbo = false;
        rt = new MasterTimer();
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
        rt.start();
        while (isOperatorControl() && isEnabled()) {
            chkturbo();
            if (turbo & rt.actindex[1].gdt() >= .8) {
                System.out.println("Turbo enabled, watch your toes!");
            }
            MC.Drive(IM.getFinalAxis(turbo), turbo);
        }

    }

    private static void chkturbo() {
            if(turbo & rt.actindex[0].gdt() >= .4){
            turbo = !(IM.UnlockR1.getState() & IM.UnlockL1.getState() & IM.misc9.getState());
            }else if((!turbo) & rt.actindex[0].gdt() >= .4){
            turbo = (IM.UnlockR1.getState() & IM.UnlockL1.getState() & IM.misc9.getState());
            }
        

    }

    void UpdateAll() {
        //This is a bad idea
    }
}
