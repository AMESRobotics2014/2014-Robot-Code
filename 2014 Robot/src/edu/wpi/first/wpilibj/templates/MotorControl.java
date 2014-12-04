package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

public class MotorControl {
    
    protected static Victor rightMotor;
    protected static Victor leftMotor;
    protected static Relay GrabWheel, grabberMotor;
    protected static Relay high;
    protected static InputManager IM;
    
    public void Init(){
        rightMotor = new Victor(RobotMap.rightMotor);
        leftMotor = new Victor(RobotMap.leftMotor);
        grabberMotor = new Relay(RobotMap.grabberMotor);
        GrabWheel = new Relay(RobotMap.densoMotor);
        GrabWheel.setDirection(Relay.Direction.kReverse);
        grabberMotor.setDirection(Relay.Direction.kBoth);        
        high = new Relay(RobotMap.high);
        high.setDirection(Relay.Direction.kBoth);
        IM = new InputManager();
    }
    
    public void drive(double[] mv) {
        rightMotor.set(limit(mv[0]));
        leftMotor.set(limit(mv[1]));
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
    public static void compress(boolean stahp){
        if(!stahp){
            high.set(Relay.Value.kForward);
        }
        else{
            high.set(Relay.Value.kOff);
        }
    }
    
    public void grabber(byte dir) {
            
        if(dir == 1){
            grabberMotor.set(Relay.Value.kForward);
        }
        else if(dir == 2){
            grabberMotor.set(Relay.Value.kReverse);
            GrabWheel.set(Relay.Value.kOff);
        }
        else if(dir == 0){
            grabberMotor.set(Relay.Value.kOff);
        }
        else{
            System.out.println("This shouldn't happen fix your grabber call");
            grabberMotor.set(Relay.Value.kOff); 
        }
    }
    public void grabberWheel(byte dir){
        if(dir == 1){
            GrabWheel.set(Relay.Value.kReverse);
        }
        else if(dir == 0){
            GrabWheel.set(Relay.Value.kOff);
        }
        else{
            System.out.println("This shouldn't happen fix your wheel call");
            GrabWheel.set(Relay.Value.kOff); 
        }
    }
    
    public void transmission(boolean fast){
        if(!fast){
            high.set(Relay.Value.kReverse);
        }
        else if (fast){
            high.set(Relay.Value.kForward);
        }
        
    }
}
