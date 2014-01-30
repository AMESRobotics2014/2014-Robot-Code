package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Jaguar;

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
    protected static Victor grabberMotor, densoMotor;
    protected static Victor elevatorMotor;

    InputManager IM;
    
    public void init() {
        firstRightMotor = new Victor(RobotMap.firstRightMotor);
        secondRightMotor = new Victor(RobotMap.secondRightMotor);

        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        secondLeftMotor = new Victor(RobotMap.secondLeftMotor);

        shooterLeftMotor = new Jaguar(RobotMap.shooterLeftMotor);
        shooterRightMotor = new Jaguar(RobotMap.shooterRightMotor);

        grabberMotor = new Victor(RobotMap.grabberMotor);
        densoMotor = new Victor(RobotMap.densoMotor);

        elevatorMotor = new Victor(RobotMap.elevatorMotor);
        
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
        float cir = 4.25f;
        float lenght;
    }

    public void grabber(boolean Switch1, boolean Switch2) {
        if(IM.raiseGrabber.getState()){
            grabberMotor.set(-1);
        }else{
            grabberMotor.set(0);
        }
        if(IM.lowerGrabber.getState()){
            grabberMotor.set(1);
            if(Switch2 == true){
            densoMotor.set(1);
            //shooter cant shoot
            }
        }else{
            grabberMotor.set(0);
        }
        if(Switch1 == true){
            grabberMotor.set(0);
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

    void shooter(boolean state) {
    }
}
