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
   static byte mode;
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
        mode = 1;//1 for drive, 2 for pickup, 3 for carry
    }

    public void autonomousPeriodic() {
    }

    public void teleopPeriodic() {
        MT.Freset();
        while (true && isOperatorControl() && isEnabled()) {
            wd.feed();
           Event.Alwaysrun();
           if (R.manualONLY) {
                if (!IM.SettingsL.getState()) {
                    Event.m_Elevator();
                    Event.m_Grab();
                    Event.m_Shoot();
                    Event.m_Pullback();
                    Event.m_Shift();
                } else if(mode == 1) {
                    Event.s_GrabRetract();
                    Event.s_GrabShutdown();
                    Event.s_Elevatordown();
                    Event.s_Pullback();
                    Event.s_Shift(true);
                }else if(mode == 2){
                    //Event.s_GrabSustain();
                    Event.s_Shift(false);
                    Event.s_Elevatordown();
                    if(IM.PullbackLimit.get()){
                        Event.s_Pullback();
                    }
                    Event.m_Grab();
                }
            }
        }
        MT.Freset();
    }

    //These are unesscessary but more intuitive
    public static void enterDrive(){
        mode = 1;
    }
    public static void enterPickup(){
        mode = 2;
    }
    public static void enterCarry(){
        mode = 3; 
    }
    public static class Event {
        static boolean firing = false;
        //Sorted into scripted events and manual events by prefix s and m
        public static void Alwaysrun() {
            MC.drive(IM.getFinalAxis());
            if(IM.R1.getState()){
                enterDrive();
            }
            if(IM.L2.getState()){
                enterPickup();
            }
            if(IM.R2.getState()){
                enterCarry();
            }
            if (MT.gdt(1) >= 16) {
                MT.sc(1);
                Event.Debug();
            }
        }
        public static void s_FastDebug(){
            
            if (MT.gdt(1) >= 3) {
                MT.sc(1);
                Event.Debug();
            }
        }
        public static void Debug(){
            System.out.println("Does the robot even lift????");
            System.out.println("Grabber Timer:" + MT.gdt(4));
            System.out.println("Debug timer" + MT.gdt(1));
         //   MT.listIndicesDEBUG();
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
            if (IM.L2.getState()) {
                MC.grabberWheel((byte)1);
            }
            else if(!IM.L2.getState()){
                MC.grabberWheel((byte)0);
            }
        }
        public static void m_Shoot() {
            
            if (IM.FaceRight.getState() /*& IM.clutchReleasedLimit.get()*/) {
                firing = true;
                MC.clutch(1);
            }
            else{
                MC.clutch(0);
            }
           
            if (IM.FaceTop.getState() & IM.ratchetLimit.get() ) {
                MC.ratchet(2);
            } else if (IM.FaceBott.getState() &  IM.ratchetDownLimit.get()) {
                MC.ratchet(1);
            }
            else{
                MC.ratchet(0);
            }

        }
        public static void m_Pullback() {
            if (IM.R2.getState() & IM.dPadValue()[1] > .05 & IM.PullbackLimit.get()) {
                MC.pullback(2);
            }
            else if (IM.R2.getState() & IM.dPadValue()[1] < -.05) {
                MC.pullback(1);
            }
            else if(IM.FaceLeft.getState()){
                MC.pullback(3);
            }
            else{
                MC.pullback(0);
            }
        }
        public static void m_Shift(){
            if(IM.dPadValue()[1] > .05){
            MC.transmission(true);
            }
            else if(IM.dPadValue()[1] < .05){
                MC.transmission(false);
            }
        }
        public static void m_Elevator() {
            if (IM.TopElevatorLimit.get() & IM.dPadValue()[0] > .05) {
                MC.Elevator((byte)1);
            } else if (IM.LowerElevatorLimit.get() & IM.dPadValue()[0] < -.05) {
                MC.Elevator((byte)2);
            }
            else{
                MC.Elevator((byte) 0);
            }
        }
        public static void s_GrabSustain() {
            //Use scripted event
            MC.grabberWheel((byte)1);
            if (IM.GrabberLowerLimit.get()) {
                MC.grabber((byte) 1);
            } else {
                MC.grabber((byte) 0);
            }
        }
        public static void s_GrabRetract() {
            MC.grabberWheel((byte)0);
            if (IM.GrabberLiftLimit.get()) {
                MC.grabber((byte) 2);
            } else {
                MC.grabber((byte) 0);
            }
        }
        public static void s_GrabShutdown(){
            MC.grabber((byte)0);
        }
        public static void s_GrabPrep(){
            MC.grabberWheel((byte)1);
            if (IM.GrabberLowerLimit.get()) {
                MC.grabber((byte) 1);
            } else {
                MC.grabber((byte) 0);
            }
        }
        public static void s_Elevator(boolean ovride, int overtarg/*Whether to override potentiometer target and what the target should be*/){
            
        }
        public static void s_Elevatordown(){
            
        }
        public static void s_Shift(boolean fast){
            
        }
        public static void s_ShootAbs() {//The secret police will find you and shoot you no matter what
            if (IM.clutchReleasedLimit.get()) {
                MC.clutch(0);
            } else {
                MC.clutch(0);
            }

        }
        public static void s_Pullback() {
            if(!firing){
            if(IM.PullbackLimit.get()){
            MC.pullback(3);
            }
            else{
                MC.pullback(0);
            }
            }
            else{
                MC.pullback(0);
            }
        }
        public static void s_Shoot() {
            // if(Com.ConfirmShot()){
            if(IM.FaceRight.getState() & IM.clutchReleasedLimit.get()){
            MC.clutch(1);
            }else{
                MC.clutch(0);
            }
            if (!IM.PullbackLimit.get() & IM.clutchReleasedLimit.get()) {
                MC.ratchet(1);
            }
            else{
                MC.ratchet(0);
            }
            // }
        }
        public static void s_Testlimits() {
            if (MT.gdt(0) >= .8 & IM.FaceLeft.getState()) {
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
            if (IM.FaceLeft.getState() & (MT.gdt(3) >= .8)) {
                MT.sc(3);
                System.out.println("Value: " + IM.Poten.getValue());
            }
        }
        public static void s_XwingAttackMode() {
            UsetheForce(9001);
        }
    }

    public static void UsetheForce(int mitichlorians) {
        System.out.println("Feel don't think");
    }
}
