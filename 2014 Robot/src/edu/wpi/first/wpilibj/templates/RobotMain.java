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
    private static Communication Com;
    protected static Watchdog wd;
    boolean turbo, manualControl;
    boolean shiftSTR;

    public void robotInit() {
        MC = new MotorControl();
        MC.init();
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
        Com = new Communication();
        Com.init();
        IP = new ImageProcessing();
        wd = Watchdog.getInstance();
        wd.setExpiration(0.5);
        wd.feed();
    }

    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {
        while (true && isOperatorControl() && isEnabled()) {
            wd.feed();

            if (MT.gdt(3) >= 1) {
                Com.processTable();
                MT.sc(3);
            }
            Event.Alwaysrun();
            if (R.manualONLY) {
                if (!IM.SettingsL.getState()) {
                    Event.m_Elevator();
                    Event.m_Grab();
                } else {
                    MC.manualMode();
                    MC.elevatorOLD(IM.dPadValueOLD()[0]);
                }
            }
        }

    }

    public static class Event {

        //Sorted into scripted events and manual events by prefix s and m
        public static void Alwaysrun() {
            MC.drive(IM.getFinalAxis());
            if (MT.gdt(1) >= 5.0) {
                MT.sc(1);
                System.out.println("Does the robot even lift????");
                //  MT.listIndicesDEBUG();
            }
        }

        public static void m_Grab() {
            //Manually apply from input
            if (IM.L1.getState() & IM.GrabberLowerLimit.get()) {
                MC.grabber((byte) 1);
            } else if (IM.R1.getState() & IM.GrabberLiftLimit.get()) {
                MC.grabber((byte) 2);
            } else {
                MC.grabber((byte) 0);
            }
            if (IM.R2.getState()) {

            }
        }

        public static void m_Shoot() {
            if (IM.FaceBott.getState()) {
                MC.clutch(IM.clutchEngagedLimit.get());
            } else if (IM.FaceRight.getState()) {
                MC.clutch(IM.clutchReleasedLimit.get());
            }
            if (IM.FaceTop.getState()) {
                MC.ratchet(IM.ratchetLimit.get(), false);
            } else if (IM.FaceLeft.getState()) {
                MC.ratchet(IM.ratchetDownLimit.get(), true);
            }

        }

        public static void m_Pullback() {
            if (IM.dPadValue()[0] > .05) {
                MC.pullback(false, IM.PullbackLimit.get());
            }
            if (IM.dPadValue()[0] < -.05) {
                MC.pullback(true, false);
            }
        }

        public static void m_Elevator() {
            if (IM.TopElevatorLimit.get() & IM.dPadValue()[0] > .05) {
                MC.Elevator(IM.dPadValue()[0]);
            } else if (IM.LowerElevatorLimit.get() & IM.dPadValue()[0] < -.05) {
                MC.Elevator(IM.dPadValue()[0]);
            }
        }

        public static void s_GrabSustain() {
            //Use scripted event
            if (IM.GrabberLowerLimit.get()) {
                MC.grabber((byte) 1);
            } else {
                MC.grabber((byte) 0);
            }
        }

        public static void s_GrabRetract() {
            if (IM.GrabberLiftLimit.get()) {
                MC.grabber((byte) 2);
            } else {
                MC.grabber((byte) 0);
            }
        }

        public static void s_ShootAbs() {//The secret police will find you and shoot you no matter what
            if (IM.clutchReleasedLimit.get()) {
                MC.clutch(true);
            } else {
                MC.clutch(false);
            }

        }

        public static void s_Pullback() {
            MC.pullback(true, IM.PullbackLimit.get());
        }

        public static void s_Shoot(boolean dsbl) {
            // if(Com.ConfirmShot()){
            MC.clutch(IM.clutchReleasedLimit.get());
            if (!IM.PullbackLimit.get() & IM.clutchReleasedLimit.get()) {
                MC.ratchet(IM.ratchetLimit.get(), true);
            }
            // }
        }

        public static void s_Testlimits() {
            if (MT.gdt(0) >= .6 & IM.FaceRight.getState()) {
                MT.sc(0);
                System.out.println("Checking limits");
                System.out.println("Clutch Engage" + ": " + IM.clutchEngagedLimit.get());
                System.out.println("Clutch Released" + ": " + IM.clutchReleasedLimit.get());
                System.out.println("Ratchet down" + ": " + IM.ratchetDownLimit.get());
                System.out.println("Ratchet limit" + ": " + IM.ratchetLimit.get());
                System.out.println("Lift Limit" + ": " + IM.GrabberLiftLimit.get());
                System.out.println("Lower Limit" + ": " + IM.GrabberLowerLimit.get());
                System.out.println("Pullback limit" + ": " + IM.PullbackLimit.get());
                System.out.println("Elevator Top Limit" + ": " + IM.TopElevatorLimit.get());
                System.out.println("Elevator Bottom Limit" + ": " + IM.LowerElevatorLimit.get());
            }
        }

        public static void s_testPot() {
            if (IM.FaceRight.getState() & (MT.gdt(2) >= .6)) {
                MT.sc(2);
                System.out.println("Voltage: " + IM.Poten.getVoltage());
                System.out.println("Value: " + IM.Poten.getValue());
            }
        }

        public static void s_XwingAttackMode() {
            UsetheForce(9001);
        }
    }

    //Not sure if I want to keep this or not, will be good for complex button combos but I'm not sure we need it}
    public static void UsetheForce(int mitichlorians) {
        System.out.println("Feel don't think");
    }
}
