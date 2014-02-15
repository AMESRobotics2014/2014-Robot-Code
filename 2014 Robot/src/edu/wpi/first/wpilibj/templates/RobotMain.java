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
    boolean turbo, manualControl;
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
            if (IM.FaceBott.getState()) {
            }
            IM.dPadValue();
            if (MT.gdt(1) >= 1.0) {
                MT.sc(1);
            }

            /*0// Second position - robot is lowering grabber pick up ball.
            if (someButtonIsPressed && elevatorIsFlat && grabberIsUp) {
                //Event.sGrabarm();

                MC.grabber((byte) 1);
            }
            
            // Lift grabber
            else if (someOtherOtherOtherButton && grabberIsDown && elevatorIsFlat) {
                MC.grabber((byte) 2);
                
                // After arm picks up ball elevate the elevator to the fixed angle.
                // 123 is a dummy number. It signifies the potentiometer value that raises the elevator to the magic angle.
                MC.elevator(123, turbo, turbo, turbo);
            }
            
            // Third position - robot is getting ready to shoot.
            else if (someOtherButtonIsPressed && elevatorIsAtFixedAngle && grabberIsUp && robotSpeedIsZero) {
                // Get angle at which to set the elevator to.
                // Get distance.
                
                // Release clutch, ratchet, and shoot.
                MC.shooter();
            }
            
            // Get back to default position.
            else if (someOtherOtherButtonIsPressed && noBallIsInRobot) {
                // Set elevator flat.
                // Make sure grabber is up.
                MC.grabber((byte) 2);
            }*/

            MC.drive(IM.getPureAxis());
            //MC.shooter();
            if (IM.SettingsR.getState()) {
                System.out.println("Toggling");
                IM.SettingsR.buttonState = !IM.SettingsR.buttonState;
            }
            if (/*IM.SettingsR.buttonState*/true) {
                System.out.println("Manual");
                MC.manualMode();
            }
            //MC.grabber(false);
            //  MC.elevator(1.0,InputManager.raiseGrabber.getState(),InputManager.lowerGrabber.getState(),false);
            MC.transmissionOLD();
        }
        
    }

    public static class Event {
        //Sorted into scripted events and manual events by prefix s and m
        public static void m_Grab() {
            //Manually apply from input
            if (IM.R1.getState() & !IM.GrabberLowerLimit.get()) {
                MC.grabber((byte) 1);
            }
            if (IM.L1.getState() & !IM.GrabberLiftLimit.get()) {
                MC.grabber((byte) 2);
            } else {
                MC.grabber((byte) 0);
            }
        }
        public static void m_Shoot(){
            
        }
        public static void s_GrabSustain() {
            //Use scripted event
            if(!IM.GrabberLowerLimit.get()){
            MC.grabber((byte) 1);
            }
            else{
                MC.grabber((byte) 0);
            }
        }
        public static void s_GrabRetract(){
            if(!IM.GrabberLiftLimit.get()){
            MC.grabber((byte) 2);
            }
            else{
                MC.grabber((byte) 0);
            }
        }
        public static void s_XwingAttackMode(){
            UsetheForce(9001);
        }
    }

    //Not sure if I want to keep this or not, will be good for complex button combos but I'm not sure we need it
    public static class ButtonEvents {

        static boolean b;

        public static boolean R1() {
            return IM.R1.getState();
        }

        public static boolean R2() {
            return IM.R2.getState();
        }

        public static boolean L1() {
            return IM.L1.getState();
        }

        public static boolean L2() {
            return IM.L2.getState();
        }
        
        public static boolean EnterAttackMode(){
            return IM.FaceTop.getState();
        }
        public static boolean EnterPickupMode(){
            return IM.FaceRight.getState();
    }
        
    }
    public static void UsetheForce(int mitichlorians){
        System.out.println("Feel don't think");
    }
}
