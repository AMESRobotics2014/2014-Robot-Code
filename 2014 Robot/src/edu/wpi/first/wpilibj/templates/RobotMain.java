package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Watchdog;

public class RobotMain extends IterativeRobot {
    
    private static MotorControl MC;
    private static RobotMap R;
    private static InputManager IM;
    private static MasterTimer MT;
    private static Communication Com;
    protected static Watchdog WD;
    boolean turbo;
    boolean shiftSTR;
    public static boolean start;
    
    public void RobotInit(){
        MC = new MotorControl();
        MC.Init();
        WD = Watchdog.getInstance();
        WD.setExpiration(.5);
        WD.setEnabled(true);
        turbo = false;
        shiftSTR = false;
        MT = new MasterTimer();
        //MT.Start();
        MT.Init();
        Com = new Communication();
        Com.Init();
        WD = Watchdog.getInstance();
        WD.setExpiration(.5);
        WD.feed();
        start = true;        
    }
    
    public void autonomous() {
        
        while(isEnabled()){
            WD.feed();
            if(start){
                MT.Freset();
            }
            start = false;
            System.out.println("Time: " + MT.gdt(5));
            if(MT.gdt(5) <= 2.5){
                System.out.println("Autonomous forward");
                MC.rightMotor.set(1);
                MC.leftMotor.set(-1);
            }
        }      
    }

    public void operatorControl() {
        
        MT.Freset();
        while(true && isOperatorControl() && isEnabled()){
            WD.feed();
            Event.Alwaysrun();
            Event.m_Shift();
            if(R.manualONLY){
                Event.m_Shift();
            } 
        }
        MT.Freset();
    }
    
    public static void autoInit(){
        if(start){
            MT.Freset();
        }
    }
    public static class Event{
        
        public static void Alwaysrun(){
            if(IM.SettingsL.getState() & MT.gdt(7) >= .2){
                MT.sc(7);
            }        
            DashboardPost();
            MC.drive(IM.getFinalAxis());
            if(MT.gdt(1) >= 16) {
                MT.sc(1);
                Event.Debug();
            }
        }        
        public static void DashboardPost(){
            Com.RobotSpeed(IM.getFinalAxis()[0]);
            Com.RobotSpeed(IM.getFinalAxis()[1]);
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
        }
        public static void m_Grab() {
            
            if (IM.L1.getState()) {
                MC.grabber((byte) 1);
            } else if (IM.R1.getState()) {
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
        public static void m_Shift(){
            if(IM.dPadValue()[1] > .05){
            MC.transmission(true);
            }
            else if(IM.dPadValue()[1] < .05){
                MC.transmission(false);
            }
        }        
    }    
}
