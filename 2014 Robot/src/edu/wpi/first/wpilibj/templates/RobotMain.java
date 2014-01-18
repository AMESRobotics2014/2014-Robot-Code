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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {

    class Ktimer extends Timer {

        double[] t = new double[2];
        boolean firstaccess;

        public void Ktimer() {
            this.start();
            this.firstaccess = true;
            this.t[0] = 0;
            this.t[1] = 0;
        }

        public void Freset() {
            this.reset();
            this.t[1] = 0;
        }

        public boolean firstaccess() {
            boolean b = this.firstaccess;
            this.firstaccess = false;
            return (b);
        }

        public double[] gettimes() {
            // First in index is the current time, second is the change in time since last check
            this.t[1] = this.t[0];
            this.t[0] = this.get();
            return (this.t);
        }
    }
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    private static InputManager IM;
    private static MotorControl MC;
    private static boolean turbo;
    private static Watchdog wd;
    private static Ktimer rt;

    public void robotInit() {
        IM = new InputManager();
        IM.init();
        MC = new MotorControl();
        MC.init();
        wd = Watchdog.getInstance();
        wd.setExpiration(.5);
        turbo = false;
        rt = new Ktimer();
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
        rt.start();
        while (isOperatorControl() && isEnabled()) {
            System.out.println("Times:" + rt.get() + " , " + rt.t[1]);
            //This wont be so long in final version
           // System.out.println("Times:" + rt.gettimes()[0] + "OldTime" + rt.gettimes()[1]);
                //System.out.println("chking");
                chkturbo();
            
            if (turbo/* & rt.gettimes()[1] >= 2000 | rt.firstaccess()*/) {
                System.out.println("Turbo enabled, watch your toes!");
            }
            //turbo = false;
            if(IM.R2.getState()){
                turbo = true;
            }
            MC.Drive(IM.getFinalAxis(turbo), turbo);
            
        }

    }

    private static void chkturbo() {
        rt.gettimes();
        if (turbo & rt.t[1] >= 1 | rt.firstaccess()) {
            turbo = !(IM.UnlockR1.getState() & IM.UnlockL1.getState() & IM.misc9.getState());
        } else if(!turbo & rt.t[1] >= 1 | rt.firstaccess()) {
            turbo = (IM.UnlockR1.getState() & IM.UnlockL1.getState() & IM.misc9.getState());
        }
        
    }

    void UpdateAll() {
        //This is a bad idea
    }
}
