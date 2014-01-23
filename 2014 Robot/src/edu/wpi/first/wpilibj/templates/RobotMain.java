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
import edu.wpi.first.wpilibj.Timer;
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
    private static MasterTimer MT;

    public void robotInit() {
        IM = new InputManager();
        IM.init();
        MC = new MotorControl();
        MC.init();
        wd = Watchdog.getInstance();
        wd.setExpiration(.5);
        wd.setEnabled(true);
        turbo = false;
        MT = new MasterTimer();
        MT.Init();
        //MT = new MasterTimer();
       //MT.Init();
       //System.out.println(MT.actindex.length);
       //MT.lstadd = 0;
       //MT.addEventTimer("TurboTimer");
       //MT.addEventTimer("DEBUGprints");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        wd.feed();
        //Nah
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        wd.feed();
        MT.start();
        //MT.listIndicesDEBUG();
        while (isOperatorControl() && isEnabled()) {
            wd.feed();
            TurboToggle();
            if (turbo & MT.actindex[1].gdt() >= 1.5) {
                System.out.println("Turbo enabled, watch your toes!");
            }
            MC.Drive(IM.getFinalAxis(turbo), turbo);
            wd.feed();
        }
    }

    private static void TurboToggle() {
            if(ButtonEvents.TTurboE() /*& MT.actindex[0].gdt() >= .4*/){
            turbo = !turbo;
            }
    }
    
        public static class ButtonEvents{
        static boolean b; // Used by all methods temporarily
        public static boolean TTurboE(){
            b = false;
            b = (IM.rb.getState() & IM.lb.getState() & IM.m9.getState());
            return(b);
        }
    }
    
}
