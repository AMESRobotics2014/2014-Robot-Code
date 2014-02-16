/*
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class should hold all code, classes and methods for managing all inputs
 * into the system, this includes buttons, joysticks, and other user interface
 * devices.
 * <p>
 * The class should handle, and manipulate these inputs into data to be sent to
 * other parts of the robot. In most cases this class should only manage and
 * create instructions for hardware, these instructions should be sent elsewhere
 * before being fed to hardware.
 * <p>
 * For example, this class should get input from the joysticks, organize it,
 * prepare it, but never call on an actual motor to do any action. Instead, send
 * it to a motor controlling class that will take that data and use it
 * appropriately.
 *
 * @author Erin Turnley
 */
public class InputManager {

    protected static Joystick ps2Controller;
    protected static button FaceTop, SettingsL, L1, R1, R2, L2, FaceBott, SettingsR, FaceRight, FaceLeft;
    protected static DigitalInput TopElevatorLimit, LowerElevatorLimit, PullbackLimit, GrabberLowerLimit, GrabberLiftLimit, clutchEngagedLimit, clutchReleasedLimit, ratchetLimit, ratchetDownLimit;
    static double[] dir = new double[2];
    protected AnalogChannel Poten;
    static RobotMap R;

    public void init() {
        ps2Controller = new Joystick(1);
        FaceTop = new button(4, true);
        Poten = new AnalogChannel(RobotMap.PotenPin);
        L1 = new button(5, true);
        R1 = new button(6, true);
        R2 = new button(8, true);
        L2 = new button(7, true);
        FaceBott = new button(2, true);
        SettingsR = new button(10, true);
        SettingsL = new button(9,true);
        FaceRight = new button(3, true);
        FaceLeft = new button(1,true); 

        TopElevatorLimit = new DigitalInput(1);
        LowerElevatorLimit = new DigitalInput(3);
        PullbackLimit = new DigitalInput(7);
        GrabberLowerLimit = new DigitalInput(10);
        clutchEngagedLimit = new DigitalInput(12);
        GrabberLiftLimit = new DigitalInput(9);
        clutchReleasedLimit = new DigitalInput(13);
        ratchetLimit = new DigitalInput(14);
        ratchetDownLimit = new DigitalInput(11);
    }

    public double[] getFinalAxis() {
        double[] drv = new double[4];
        drv = ramp(normalize(getPureAxis()));
        return (drv);
    }

    public static double[] getPureAxis() {
        dir[0] = ps2Controller.getRawAxis(2);
        dir[1] = -ps2Controller.getRawAxis(4);

        dir = deadZone(dir);
        return dir;
    }

    public static double[] dPadValueOLD() {
        double upDownDPad = -ps2Controller.getRawAxis(6);
        double oldRange = 2, newRange;
        if ((upDownDPad == 1) && (oldRange <= 2) && (oldRange >= 1)) {
            newRange = oldRange + 0.2;
        } else if ((upDownDPad == -1) && (oldRange <= 2) && (oldRange >= 1)) {
            newRange = oldRange - 0.2;
        } else {
            newRange = 0;
        }

        double newMin = (newRange / -2);

        dir[0] = (((dir[0] + 1) * newRange) / oldRange) + newMin;
        dir[1] = (((dir[1] + 1) * newRange) / oldRange) + newMin;

        return dir;
    }

    public byte[] dPadValue() {
        byte[] val = new byte[2];
        if (ps2Controller.getRawAxis(5) > 0.05) {
            val[0] = 1;
        } else if (ps2Controller.getRawAxis(5) < -0.05) {
            val[0] = -1;
        }
        if (ps2Controller.getRawAxis(6) > 0.05) {
            val[1] = 1;
        } else if (ps2Controller.getRawAxis(6) < -.05) {
            val[1] = -1;
        } else {
            val[0] = 0;
            val[1] = 0;
        }
        System.out.println("Dpad got" + val[0] + " and "+ val[1]);
        return val;
    }

    public static double[] scaleValuesOLD(double[] values) {
        /*
         double upDownDPad = -ps2Controller.getRawAxis(6);
       
         double oldRange = 2, newRange = 0;
       
         if ((buttonDPadValue == -1) && (oldRange >= 1)) {
         newRange = oldRange - 0.2;
         } else if ((buttonDPadValue == 0)){
            
         }
         else if ((buttonDPadValue == 1) && (oldRange <= 2)) {
         newRange = oldRange + 0.2;
         }
       
         double newMin = (newRange / -2);
       
         values[0] = (((values[0] + 1) * newRange) / oldRange) + newMin;
         values[1] = (((values[1] + 1) * newRange) / oldRange) + newMin;
         */

        return values;
    }

    protected static double[] normalize(double[] axis) {
        if ((Math.abs((axis[0] - axis[1])) <= R.normalThresh) & (axis[0] * axis[1]) > 0) {
            double tinydbl = (axis[0] + axis[1]) / 2;
            axis[0] = (tinydbl);
            axis[1] = (tinydbl);
        }
        return axis;
    }

    protected static double[] ramp(double[] axis) {
        for (byte ri = 0; ri < axis.length; ri++) {
            //axis[ri] = MathUtils.pow(axis[ri], rm.expo_ramp);
            axis[ri] = (((.666) * MathUtils.pow(axis[ri], R.expo_ramp)) + ((.333) * axis[ri])) * 1;
        }
        return (axis);
    }

    protected static double[] deadZone(double[] axis) {
        for (byte si = 0; si < axis.length; si++) {
            if ((axis[si] <= 0.05) && (axis[si] >= -0.05)) {
                axis[si] = 0;
            }
        }

        return axis;
    }

    protected static class button {

        boolean buttonState;
        int buttonPin;

        public button(int buttonPin, boolean joystickState) {
            this.buttonPin = buttonPin;
        }

        public boolean getState() {
            buttonState = ps2Controller.getRawButton(this.buttonPin);

            return buttonState;
        }
    }
}
