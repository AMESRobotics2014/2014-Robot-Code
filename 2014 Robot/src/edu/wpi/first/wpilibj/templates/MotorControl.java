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
    protected static Jaguar shooterLeftMotor;
    protected static Jaguar shooterRightMotor;
    protected static Relay densoMotor, grabberMotor;
    protected static Victor elevatorMotor;
    protected static Relay high, low;

    InputManager IM;
    
    public void init() {
        firstRightMotor = new Victor(RobotMap.firstRightMotor);
        secondRightMotor = new Victor(RobotMap.secondRightMotor);

        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        secondLeftMotor = new Victor(RobotMap.secondLeftMotor);
/*
        shooterLeftMotor = new Jaguar(RobotMap.shooterLeftMotor);
        shooterRightMotor = new Jaguar(RobotMap.shooterRightMotor);
        release = new Relay(RobotMap.release);
        
        release.setDirection(Relay.Direction.kForward);
*/
        grabberMotor = new Relay(RobotMap.grabberMotor);
        densoMotor = new Relay(RobotMap.densoMotor);
        
        grabberMotor.setDirection(Relay.Direction.kBoth);
        densoMotor.setDirection(Relay.Direction.kForward);
/*
        elevatorMotor = new Victor(RobotMap.elevatorMotor);
        */
        high = new Relay(RobotMap.high);
        low = new Relay(RobotMap.low);
        
        high.setDirection(Relay.Direction.kForward);
        low.setDirection(Relay.Direction.kForward);
        
        IM = new InputManager();
    }

    public void drive(double[] mv) {
        firstRightMotor.set(limit(mv[0]));
        secondRightMotor.set(limit(mv[0]));

        firstLeftMotor.set(limit(mv[1]));
        secondLeftMotor.set(limit(mv[1]));
    }

    public void stopDrive() {
        firstRightMotor.set(0);
        secondRightMotor.set(0);

        firstLeftMotor.set(0);
        secondLeftMotor.set(0);
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
        int time = 6000;
        if(IM.shoot.getState()){      
            
            for(int pos = 0; pos <= 12; pos++){
            
            if(pos >= time){
                //release.set(Relay.Value.kOn);
            }
            }
    }
}
    public void grabber(boolean Switch1) {
        int time = 6000;
        if(IM.raiseGrabber.getState()){
            grabberMotor.set(Relay.Value.kReverse);
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(IM.lowerGrabber.getState()){
            
            grabberMotor.set(Relay.Value.kForward);
            delay(time);
                densoMotor.set(Relay.Value.kForward);               
        }else{
            grabberMotor.set(Relay.Value.kOff);
        }
        if(Switch1 == true){
            grabberMotor.set(Relay.Value.kOff);
        }
    }

    public void elevator(double val, boolean Button1, boolean Button2, boolean autoAim) {
        if (val == 0) {
            if (autoAim == false) {
                elevatorMotor.set(1);
                if (Button1 == true) {
                    elevatorMotor.set(-1);
                }
            }
        } else {
            while (Button2 == false) {
                elevatorMotor.set(-1);
            }
        }
    }
    public void transmission(){        
        if(IM.power.getState()){
            /*for(int y = 0; y <= 1000; y++){
                if(y % 2 == 0){
                    low.set(Relay.Value.kOn);
                }else{
                    low.set(Relay.Value.kOff);
                }
            }*/
            low.set(Relay.Value.kForward);
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
         low.set(Relay.Value.kOff);   
        }
    }

    void shooter(boolean state) {
    }

    private void delay(int i) {
    }
}
