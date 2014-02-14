/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//___
// | |_  o  _     o  _    _|_|_  _    __  _  o __     _  |  _  _  _
// | | | | _>     | _>     |_| |(/_   |||(_| | | |   (_  | (_|_> _>
//The main class is under control of Ali Nazzal & Ben Rose. DO NOT EDIT WITHOUT EXPLICIT PERMISSION!
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Watchdog;

/**
 * This class connects the data from all the other classes and defines the
 * overall flow of the robot program.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * @author AliNazzal, BenRose
 */
public class RobotMain extends IterativeRobot {

    private static MotorControl MC;
    private static RobotMap R;
    private static ImageProcessing IP;
    private static InputManager IM;
    private static MasterTimer MT;
    protected static Watchdog wd;
    boolean turbo,manualControl;
    boolean shiftSTR;
    public void robotInit() {
        MC = new MotorControl();
        MC.init();
        //MC.test();
        wd = Watchdog.getInstance();
        wd.setExpiration(.5);
        wd.setEnabled(true);
        turbo = false;
        shiftSTR = false;
        MT = new MasterTimer();
        MT.start();
        MT.Init();
        IM = new InputManager();
        IM.init();
        IP = new ImageProcessing();
        wd = Watchdog.getInstance();
        wd.setExpiration(0.5);
        wd.feed();
    }
    public void autonomousPeriodic() {        
    }
    public void teleopPeriodic() {
        while (true && isOperatorControl() && isEnabled()) {
            /*----------------------------------------------------*/
            /* robotDrive.tankDrive(leftJoystick, rightJoystick); */
            /*----------------------------------------------------*/

            wd.feed();
            if(IM.FaceBott.getState()){
            }
            IM.dPadValue();
            if (MT.gdt(1) >= 1.0) {
                MT.sc(1);
            }
      
            MC.drive(IM.getPureAxis());
            MC.shooter();
            MC.test();
            Event.mGrabarm();
           // MC.grabberOLD(false);
            MC.elevator(1.0,IM.L1.getState(),IM.R2.getState(),false);
            MC.transmission();
        }
    }
    
            public static class Event{
                //Sorted into scripted events and manual events by prefix s and m
                public static void mGrabarm(){
                    //Manually apply from input
                    if(IM.R1.getState()){MC.grabber((byte)1);}
                    if(IM.L1.getState()){MC.grabber((byte)2);}
                    else{MC.grabber((byte)3);}
                }
                public static void sGrabarm(){
                    //Use scripted event
                    MC.grabber((byte) 1);
                    
                }
            }
            
        //Not sure if I want to keep this or not, will be good for complex button combos but I'm not sure we need it
        public static class ButtonEvents{
         static boolean b;
            public static boolean R1(){
                return IM.R1.getState();
            }
            public static boolean R2(){
                return IM.R2.getState();
            }
            public static boolean L1(){
                return IM.L1.getState();
            }
            public static boolean L2(){
                return IM.L2.getState();
            }
            
 
}
}
