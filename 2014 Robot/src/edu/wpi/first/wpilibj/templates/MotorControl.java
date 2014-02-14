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
        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        shooterMotor1 = new Victor(RobotMap.shooterMotor2);
        clutch = new Relay(RobotMap.clutch);
        ratchet = new Relay(RobotMap.ratchet);
        
        ratchet.setDirection(Relay.Direction.kForward);
        clutch.setDirection(Relay.Direction.kForward);
        grabberMotor = new Relay(RobotMap.grabberMotor);
        densoMotor = new Relay(RobotMap.densoMotor);
        grabberMotor.setDirection(Relay.Direction.kBoth);
        densoMotor.setDirection(Relay.Direction.kReverse);
        elevatorMotor = new Jaguar(RobotMap.elevatorMotor);
        high = new Relay(RobotMap.high);
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
        if (IM.shoot.getState()) {
            shooterMotor1.set(0.2);
            System.out.println("Shooting");
            clutch.set(Relay.Value.kForward);
            ratchet.set(Relay.Value.kOff);
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
        if(IM.raiseGrabber.getState()){
            grabberMotor.set(Relay.Value.kReverse);
            densoMotor.set(Relay.Value.kOff);
            elevatorMotor.set(0.4);
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
        if (TopElevator == true){
            elevatorMotor.set(0);
        }
        if (LowerElevator == true){
            elevatorMotor.set(0);
        }
        if (ElevatronPosition == true) {
            elevatorMotor.set(0);
        }
    }
    public void transmission(){   
        if(IM.power.getState()){

           high.set(Relay.Value.kOff);
        }else{
         high.set(Relay.Value.kForward);  
        }
    }
    public void manualMode(){
    if(IM.manual.getState()){
        
        if(IM.shoot.getState()){
            shooterMotor1.set(0.2);
        }
        if(IM.raiseGrabber.getState()){
            grabberMotor.set(Relay.Value.kForward);
        }
        if(IM.lowerGrabber.getState()){
            grabberMotor.set(Relay.Value.kReverse);
        }
        if(IM.power.getState()){
            densoMotor.set(Relay.Value.kForward);
        }
        if(IM.buttonStop.getState()){
            ratchet.set(Relay.Value.kForward);
        }
        if(IM.readpt.getState()){
            clutch.set(Relay.Value.kForward);
        }
    }
}
}
