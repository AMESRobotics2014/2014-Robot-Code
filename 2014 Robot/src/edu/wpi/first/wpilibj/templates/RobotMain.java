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
//import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * crprivate static InputManager IM;
    private static MotorControl MC;
    private static boolean turbo;
    private static boolean shiftSTR;//False is speed, true is strenght
    private static Watchdog wd;
    private static MasterTimer MT;eating this project, you must also update the manifest file in the resource
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
    private static boolean shiftSTR;//False is speed, true is strenght
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
        shiftSTR = false;
        MT = new MasterTimer();
        MT.Init();
    }
    public void autonomousPeriodic() {
        wd.feed();
        //Nah
    }

    public void teleopPeriodic() {
        wd.feed();
        MT.start();
        MT.listIndicesDEBUG();
        while (isOperatorControl() && isEnabled()) {
            wd.feed();
            TurboToggle();
            Shift();
            if(MT.gdt(1) >= 10){
                System.out.println(IM.getFinalAxis(turbo)[0] + IM.getFinalAxis(turbo)[1]);
                System.out.println(MT.get());
            }
            if (turbo & MT.gdt(1) >= 5) {
                System.out.println("Turbo enabled, watch your toes!");
            }
            MC.Shift(shiftSTR);
           
            MC.Drive(IM.getFinalAxis(turbo), turbo);
            wd.feed();
        }
        System.out.println("Watch-dog-not-fed");
    }

    private static void TurboToggle() {
            if(ButtonEvents.TTurboE() & MT.gdt(0) >= .4){
            turbo = !turbo;
            }
    }
    private static void Shift(){
        if(ButtonEvents.MaxSpeed() & MT.gdt(2) >= .4){
            shiftSTR = false;
        }
        else if(ButtonEvents.MaxStrenth() & MT.gdt(2) >= .4){
            shiftSTR = true;
        }
    }
    
        public static class ButtonEvents{
        static boolean b; // Used by all methods temporarily
        public static boolean TTurboE(){
            b = false;
            b = (IM.rb.getState() & IM.m9.getState());
            return(b);
        }
        public static boolean MaxStrenth(){
            b= false;
            b = (IM.lb.getState());
            return(b);
        }
        public static boolean MaxSpeed(){
            b = false;
            b = (IM.lf.getState());
            return(b);
        }
    }
    
}