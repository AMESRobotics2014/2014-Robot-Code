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
    protected static Relay GrabWheel, grabberMotor;
    protected static Jaguar elevatorMotor;
    protected static Relay low, ratchet, clutch;
    protected static Relay high;
    protected static DigitalInput TopElevator, LowerElevator, PullbackLimit, GrabberLowerLimit, GrabberLiftLimit, clutchEngagedLimit, clutchReleasedLimit, ratchetLimit, ratchetDownLimit;
    InputManager IM;
    
    public void init() {
        firstRightMotor = new Victor(RobotMap.firstRightMotor);
        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        shooterMotor1 = new Victor(RobotMap.shooterMotor2);
        clutch = new Relay(RobotMap.clutch);
        ratchet = new Relay(RobotMap.ratchet);
        ratchet.setDirection(Relay.Direction.kBoth);
        clutch.setDirection(Relay.Direction.kForward);
        grabberMotor = new Relay(RobotMap.grabberMotor);
        GrabWheel = new Relay(RobotMap.densoMotor);
        grabberMotor.setDirection(Relay.Direction.kBoth);
        GrabWheel.setDirection(Relay.Direction.kReverse);
        elevatorMotor = new Jaguar(RobotMap.elevatorMotor);
        high = new Relay(RobotMap.high);
        
        TopElevator = new DigitalInput(1);
        LowerElevator = new DigitalInput(3);
        PullbackLimit = new DigitalInput(7);
        GrabberLowerLimit = new DigitalInput(9);
        clutchEngagedLimit = new DigitalInput(11);
        GrabberLiftLimit = new DigitalInput(10);
        clutchReleasedLimit = new DigitalInput(13);
        ratchetLimit = new DigitalInput(14);
        ratchetDownLimit = new DigitalInput(12);
        IM = new InputManager();
    }

    public void drive(double[] mv) {
        firstRightMotor.set(limit(mv[0]));
        firstLeftMotor.set(limit(mv[1]));
    }

    public void stopDrive() {
        firstRightMotor.set(0);

        firstLeftMotor.set(0);
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

    public void shooter() {
        if (IM.R2.getState()) {
            //shooterMotor1.set(1);
            System.out.println("Shooting");
            
            if (PullbackLimit.get()) {
                shooterMotor1.set(0);
            } else {
                shooterMotor1.set(1);
            }
            
            if (clutchReleasedLimit.get()) {
                clutch.set(Relay.Value.kOff);
            } else {
                clutch.set(Relay.Value.kForward);
            }
            
            if (clutchEngagedLimit.get()) {
                clutch.set(Relay.Value.kOff);
            } else {
                clutch.set(Relay.Value.kForward);
            }
            
            if (ratchetLimit.get()) {
                if (ratchetDownLimit.get()) {
                    ratchet.set(Relay.Value.kOff);
                } else {
                    ratchet.set(Relay.Value.kForward);
                }
            } else {
                ratchet.set(Relay.Value.kReverse);
            }
            
         } else {
            shooterMotor1.set(0);
            //shooterMotor2.set(0);
            elevatorMotor.set(0);
            clutch.set(Relay.Value.kOff);
            ratchet.set(Relay.Value.kOff);
        }
        
    }

    public void grabberOLD(boolean Switch1) {
        if(IM.L1.getState()){
            grabberMotor.set(Relay.Value.kReverse);
            GrabWheel.set(Relay.Value.kOff);
            elevatorMotor.set(0.4);
            elevatorMotor.set(0.0);
            System.out.println("Grabber Motor Reverse: " + (Relay.Value.kReverse));
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.R2.getState()){
            System.out.println("LowerGrabberif");
            grabberMotor.set(Relay.Value.kForward);
            //delay(time);
            GrabWheel.set(Relay.Value.kReverse);               
        }else{
            grabberMotor.set(Relay.Value.kOff);
            GrabWheel.set(Relay.Value.kOff);
        }
        if(Switch1){
            grabberMotor.set(Relay.Value.kOff);
        }
        
                if (GrabberLiftLimit.get()) {
            grabberMotor.set(Relay.Value.kOff);
        }
                }
    
    
        public void grabber(byte dir) {
            //True forward, false backwards
            if(dir == 1){
                if (GrabberLowerLimit.get()) {
                    grabberMotor.set(Relay.Value.kOff);
                    GrabWheel.set(Relay.Value.kReverse);
                } else {
                    grabberMotor.set(Relay.Value.kForward);
                    GrabWheel.set(Relay.Value.kOff);
                }
            }
            else if(dir == 2){
                if (GrabberLiftLimit.get()) {
                    grabberMotor.set(Relay.Value.kOff);
                } else {
                    grabberMotor.set(Relay.Value.kReverse);
                }
            }
            else if(dir == 3){
                grabberMotor.set(Relay.Value.kOff);
            }
        }
        
    public void elevator(double val, boolean Button1, boolean Button2, boolean autoAim) {
        double vals = IM.ps2Controller.getRawAxis(5);
        
        if (vals == 0){
            elevatorMotor.set(0);
        }
        if (vals == 1){
            if (TopElevator.get()) {
                elevatorMotor.set(0);
            } else {
                elevatorMotor.set(0.5);
            }
        }
        if (vals == -1){
            if (LowerElevator.get()) {
                elevatorMotor.set(0);
            } else {
                elevatorMotor.set(-0.5);
            }
        }
    }
    public void transmission(){   
        if(IM.L2.getState()){

           high.set(Relay.Value.kOff);
        }else{
         high.set(Relay.Value.kForward);  
        }
    }
    public void manualMode(){
  //  if(IM.SettingsR.getState()){
        
        if(IM.R2.getState()){
            shooterMotor1.set(1);
        }else{
            shooterMotor1.set(0);
        }
        if(IM.L1.getState()){
            grabberMotor.set(Relay.Value.kForward);
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.R1.getState()){
            grabberMotor.set(Relay.Value.kReverse);
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.L2.getState()){
            GrabWheel.set(Relay.Value.kReverse);
        }else{
            GrabWheel.set(Relay.Value.kOff);
        }
        if(IM.FaceTop.getState()){
            ratchet.set(Relay.Value.kReverse);
        }else{
            ratchet.set(Relay.Value.kOff);
        }
        if(IM.FaceBott.getState()){
            clutch.set(Relay.Value.kForward);
        }else{
            clutch.set(Relay.Value.kOff);
        }
    }
//}
}
