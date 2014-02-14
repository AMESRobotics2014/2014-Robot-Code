package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
/**
 * This class is where all calls to the actual motor hardware should occur. They
 * should be methods callable from the main function.
 *
 * @author kolton.yager
 */
public class MotorControl {

    protected static Victor firstRightMotor;
    protected static Victor secondRightMotor;
    protected static Victor firstLeftMotor;
    protected static Victor secondLeftMotor;
    protected static Victor shooterMotor1, shooterMotor2;
    protected static Relay densoMotor, grabberMotor;
    protected static Jaguar elevatorMotor;
    protected static Relay low, ratchet, clutch;
    protected static Relay high;
    boolean TopElevator, LowerElevator,ElevatronPosition, PullbackLimit, GrabberLiftLimit;
    InputManager IM;
    
    public void init() {
        firstRightMotor = new Victor(RobotMap.firstRightMotor);
        //secondRightMotor = new Victor(RobotMap.secondRightMotor);

        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        //secondLeftMotor = new Victor(RobotMap.secondLeftMotor);

        shooterMotor1 = new Victor(RobotMap.shooterMotor2);
        //shooterMotor2 = new Victor(RobotMap.shooterMotor2);
        clutch = new Relay(RobotMap.clutch);
        //System.out.println("");
        //System.out.println(Relay.kRelayChannels);
        //System.out.println(Module.kDigitalChannels);
        //System.out.println(DigitalModule.kRelayChannels);
        ratchet = new Relay(RobotMap.ratchet);
        
        ratchet.setDirection(Relay.Direction.kForward);
        clutch.setDirection(Relay.Direction.kForward);

        
        grabberMotor = new Relay(RobotMap.grabberMotor);
        densoMotor = new Relay(RobotMap.densoMotor);
        
        grabberMotor.setDirection(Relay.Direction.kBoth);
        densoMotor.setDirection(Relay.Direction.kReverse);

        elevatorMotor = new Jaguar(RobotMap.elevatorMotor);
        
        //clutch.setDirection(Relay.Direction.kBoth);
        
        high = new Relay(RobotMap.high);
        //high = new Relay(4);
        //low = new Relay(RobotMap.low);
        //pSwitch = new Analog(RobotMap.pSwitch);
        
        
        //low.setDirection(Relay.Direction.kForward);
        
        IM = new InputManager();
    }

    public void drive(double[] mv) {
        firstRightMotor.set(limit(mv[0]));
        //secondRightMotor.set(limit(mv[0]));

        firstLeftMotor.set(limit(mv[1]));
        //secondLeftMotor.set(limit(mv[1]));
        System.out.println("Driving");
    }

    public void stopDrive() {
        firstRightMotor.set(0);
        //secondRightMotor.set(0);

        firstLeftMotor.set(0);
        //secondLeftMotor.set(0);
    }

    public static double limit(double val) {
        if (val < -1) {
            val = -1;
        }

        if (val > 1) {
            val = 1;
        }

        return val;
    }
    
    public void moveR() {
       /* PWM xds = new PWM(4);
        if (IM.shoot.getState()) {
            xds.setRaw(20);
            delay(2000);
            xds.setRaw(0);
        } else
            xds.setRaw(0);
            */
           
    }

    public void shooter() {
        if (IM.shoot.getState()) {
            //int time = 6000;
            shooterMotor1.set(0.2);
            //shooterMotor2.set(1);
            //elevatorMotor.set(0.2);
            System.out.println("Shooting");
            //delay(4000);
            clutch.set(Relay.Value.kForward);
            ratchet.set(Relay.Value.kOff);
            /*ratchet.set(Relay.Value.kForward);
            delay(100);
            ratchet.set(Relay.Value.kReverse);
            delay(100);
            ratchet.set(Relay.Value.kOff);
            
            //delay(time);
            //shooterMotor2.set(0);
            //shooterMotor1.set(0);
            //shooterMotor2.set(0);
            //if(IM.shoot.getState()){      
                //clutch.set(Relay.Value.kForward);
                //ratchet.set(Relay.Value.kReverse);
                //shooterMotor1.set(0);
                //shooterMotor2.set(0);
            //}*/
                    }
        if (PullbackLimit == true) {
            elevatorMotor.set(0);
        }
        
    
        else {
            shooterMotor1.set(0);
            //shooterMotor2.set(0);
            elevatorMotor.set(0);
            clutch.set(Relay.Value.kOff);
            ratchet.set(Relay.Value.kOff);
        }
        
    }

    public void grabber(boolean Switch1) {
        int time = 6000;
        if(IM.raiseGrabber.getState()){
            grabberMotor.set(Relay.Value.kReverse);
            densoMotor.set(Relay.Value.kOff);
            elevatorMotor.set(0.4);
            delay(4000);
            elevatorMotor.set(0.0);
            System.out.println("Grabber Motor Reverse: " + (Relay.Value.kReverse));
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.lowerGrabber.getState()){
            System.out.println("LowerGrabberif");
            grabberMotor.set(Relay.Value.kForward);
            //delay(time);
            densoMotor.set(Relay.Value.kReverse);               
        }else{
            grabberMotor.set(Relay.Value.kOff);
            densoMotor.set(Relay.Value.kOff);
        }
        if(Switch1){
            grabberMotor.set(Relay.Value.kOff);
        }
        
                if (GrabberLiftLimit == true) {
            grabberMotor.set(Relay.Value.kOff);
        }
                }

        
    

    public void elevator(double val, boolean Button1, boolean Button2, boolean autoAim) {
        double vals = IM.ps2Controller.getRawAxis(5);
        
        if (vals == 0){
            elevatorMotor.set(0);
        }
        if (vals == 1){
            elevatorMotor.set(0.5);
        }
        if (vals == -1){
            elevatorMotor.set(-0.5);
        }
        //if evevator button is true then yo gets all the sweg
        if (TopElevator == true){
            elevatorMotor.set(0);
        }
        if (LowerElevator == true){
            elevatorMotor.set(0);
        }
        if (ElevatronPosition == true) {
            elevatorMotor.set(0);
        }
            
        
        /*
        if (val == 0) {
            if (autoAim == false) {
                elevatorMotor.set(1);
                if (Button1 == true) {
                    elevatorMotor.set(-1);
                }
            }
        } else {
            if (Button2 == false) {
                elevatorMotor.set(-1);
            }
        }
        */
    }
    public void transmission(){   
        /*if(pSwitch >= 125){
            pSwitch.set(off);
        }*/
        if(IM.power.getState()){
            /*for(int y = 0; y <= 1000; y++){
                if(y % 2 == 0){
                    low.set(Relay.Value.kOn);
                }else{
                    low.set(Relay.Value.kOff);
                }
            }*/
            //low.set(Relay.Value.kForward);
           high.set(Relay.Value.kOff);
        }else{/*
            for(int x = 0; x <= 1000; x++){
            if(x % 2 == 0){
                high.set(Relay.Value.kOn);
            }else{
                high.set(Relay.Value.kOff);
            }
        }*/
         high.set(Relay.Value.kForward);
         //low.set(Relay.Value.kOff);   
        }
    }
    public void test(){
        if(IM.test.getState()){
        ratchet.set(Relay.Value.kForward);
        clutch.set(Relay.Value.kForward);
        //shooterMotor1.set(1);
        //shooterMotor2.set(1);
    } else {
            clutch.set(Relay.Value.kOff);
        }
    }

    //void shooter(boolean state) {
    //}
    
    
//Wut is this even?
    private void delay(int i) {
    }
}
