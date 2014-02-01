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

    MotorControl MC;package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

public class MasterTimer extends Timer {

    Vector actindex;
    accessdata use;
    public void MasterTimer() {
        actindex = new Vector();
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
        addEventTimer("ShiftTimer");
    }
    
      public void Init() {
        actindex = new Vector();
        addEventTimer("TurboTimer");
        addEventTimer("DEBUGprints");
        addEventTimer("ShiftTimer");
    }

    public void addEventTimer(String tid) {
        use = new accessdata(tid);
        use.Init(tid);
        actindex.addElement(use);
    }

    public void listIndicesDEBUG() {
        try{
        int i;
        System.out.println("Test" + actindex.size());
        for (i = 0; i < actindex.size(); i++) {
           use = (accessdata)actindex.elementAt(i);
            System.out.println("Name: " + use.id + " at index location:[" + i + "]");
        }
        }catch(NullPointerException ex){
            System.out.println("This index doesn't exist D:");
        }
    }
    
    public double gdt(int loc){
        System.out.println("Getting gdt at location:" +loc);
        use = (accessdata)actindex.elementAt(loc);
        System.out.println(use.gdt());
        return use.gdt();
    }

    public void Freset() {//Full reset!!!
        this.reset();
        int i;
        for (i = 0; i < actindex.size(); i++) {
            use = (accessdata)actindex.elementAt(i);
            use.wipe();
        }
    }

    public void Ereset(int[] exclude) {//Exclusion reset!!!
        int i;
        for (i = 0; i < actindex.size()-1; i++) {
            use = (accessdata)actindex.elementAt(i);
            for (int k = 0; k < exclude.length; k++) {
                if (i == exclude[k]) {
                    break;
                } else {
                    use.wipe();
                }
            }
        }
    }

    public void Sreset(int[] include) {//Selective reset
        int i;
        for (i = 0; i < include.length; i++) {
            use = (accessdata)actindex.elementAt(i);
            use.wipe();
        }
    }

    public class accessdata {

         double ot;
         double dt;
         String id;

        accessdata(String tid) {
        }
        
        public void Init(String tid){
            id = tid;
            ot = get();
            dt = -10;//Just Cause 2  
        }

        public double gdt() {
            dt = get() - ot;
            ot = get();
            return dt;
        }

        public  void wipe() {
            ot = 0;
            dt = 0;
            id = null;
        }
    }
}
    RobotMap R;
    ImageProcessing IP;
    InputManager IM;
    MasterTimer MT;
    protected static Watchdog wd;
    boolean turbo;
    boolean shiftSTR;

    /**
     * Any boxed code like this do not delete, KEEP *
     */
    /*---------------------------------------*/
    /*       RobotDrive robotDrive;          */
    /*
     /* Joystick rightJoystick, leftJoystick; */
    /*---------------------------------------*/
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        MC = new MotorControl();
        MC.init();
        wd = Watchdog.getInstance();
        wd.setExpiration(.5);
        wd.setEnabled(true);
        turbo = false;
        shiftSTR = false;
        MT = new MasterTimer();
        MT.Init();
        IM = new InputManager();
        IM.init();

        // Here if code before does not call it.
        // IM.init();

        IP = new ImageProcessing();
        // Com = new Communication();

        /*-------------------------------------------*/
        /* robotDrive = new RobotDrive(1, 8, 9, 10); */
        /*                                           */
        /* rightJoystick = new Joystick(1);          */
        /* leftJoystick = new Joystick(2);           */
        /*-------------------------------------------*/

        wd = Watchdog.getInstance();
        wd.setExpiration(0.5);
        wd.feed();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        MC.drive(new double[]{1, 1});
        MC.elevator(1, false, false, true);//What booleans should be used?
        MC.elevator(0, false, false, true);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        MT.listIndicesDEBUG();
        while (true && isOperatorControl() && isEnabled()) {
            //Undefined names are placeholders

            /*----------------------------------------------------*/
            /* robotDrive.tankDrive(leftJoystick, rightJoystick); */
            /*----------------------------------------------------*/

            wd.feed();
            System.out.println("FED.");

            IM.dPadValue();

            // This is the driving, might get changed.
            System.out.println("Current time of Mt is:" + MT.get());
            if (MT.gdt(1) >= 1.0) {
                System.out.println("I am a debug print, should happen every half second");
            }
            MC.drive(IM.getPureAxis());
            // MC.drive(IM.rampSpeed(IM.getPureAxis()));
            MC.shooter(IM.raiseGrabber.getState());
            if(MT.gdt(3) < .02){//adsf
            MC.transmission();
            }
            class block{
             /*MC.grabber(Switch1);
             If robot is running out of control.
             if (IM.buttonStop.getState()) { 
             MC.stopDrive();
             System.out.println("Stopped.");
             }
             double driveX = IM.getAxis(IM.LEFT_X);
             double driveY = IM.getAxis(IM.LEFT_Y);
             double aimX = IM.getAxis(IM.RIGHT_X);
             double aimY = IM.getAxis(IM.LEFT_Y);
             MC.drive(driveX,driveY);
             if (IM.getButton(IM.SHOOT)) {
             MC.shoot(1.0); //Placeholder value
             }
             if (IM.getButton(IM.PASS)) {
             MC.shoot(0.75); //Another placeholder
             }*/
            }
            
        }
    }
}
