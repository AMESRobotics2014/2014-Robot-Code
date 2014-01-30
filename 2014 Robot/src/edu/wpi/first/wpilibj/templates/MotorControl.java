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
    //rotected static Victor grabberMotorRaise, grabberMotorLower;
    protected static Victor elevatorMotor;

    public void init() {
        firstRightMotor = new Victor(RobotMap.firstRightMotor);
        secondRightMotor = new Victor(RobotMap.secondRightMotor);

        firstLeftMotor = new Victor(RobotMap.firstLeftMotor);
        secondLeftMotor = new Victor(RobotMap.secondLeftMotor);

        shooterLeftMotor = new Jaguar(RobotMap.shooterLeftMotor);
        shooterRightMotor = new Jaguar(RobotMap.shooterRightMotor);

        //grabberMotorRaise = new Victor(RobotMap.grabberMotorRaise);
        //grabberMotorLower = new Victor(RobotMap.grabberMotorLower);

        elevatorMotor = new Victor(RobotMap.elevatorMotor);
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
    }

    public void grabber() {
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
            while (Button2 = false) {
                elevatorMotor.set(-1);
            }
        }
    }
}
