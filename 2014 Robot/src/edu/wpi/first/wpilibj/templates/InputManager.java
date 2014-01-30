/*
 */


package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class should hold all code, classes and methods for managing all inputs into the system, this includes buttons, joysticks, and other user interface devices.
 * <p>The class should handle, and manipulate these inputs into data to be sent to other parts of the robot. In most cases this class should only manage and create instructions for hardware, these instructions should be sent elsewhere before being fed to hardware.
 * <p>For example, this class should get input from the joysticks, organize it, prepare it, but never call on an actual motor to do any action.
 * Instead, send it to a motor controlling class that will take that data and use it appropriately.
 * @author Erin Turnley
 */

public class InputManager {

    protected static Joystick ps2Controller;
    protected static InputManager.button buttonStop, raiseGrabber, lowerGrabber;
    
    static double[] dir = new double[2];
  
    public void init() {
        ps2Controller = new Joystick(1);
        buttonStop = new InputManager.button(4, true);
        raiseGrabber = new InputManager.button(5, true);
        lowerGrabber = new InputManager.button(6, true);
    }
   
    public static double[] getPureAxis() {
        // double[] dir = new double[2];
        dir[0] = -ps2Controller.getRawAxis(2);
        dir[1] = ps2Controller.getRawAxis(4);
       
       
       
        // System.out.println(upDownDPad);
       
        dir = deadZone(dir);
        // dir = scaleValues(dir, dPadValue());
       
        // System.out.println(dir[0] + ":" + dir[1]);
       
        // Might need it - we'll see.
        // dir = translate(dir);
       
        return dir;
    }
   
    public static double[] dPadValue() {
        double upDownDPad = -ps2Controller.getRawAxis(6);
        double oldRange = 2, newRange;
        
        if ((upDownDPad == 1) && (oldRange <= 2) && (oldRange >= 1))
            newRange = oldRange + 0.2;
        else if ((upDownDPad == -1) && (oldRange <= 2) && (oldRange >= 1))
            newRange = oldRange - 0.2;
        else
            newRange = 0;
        
        double newMin = (newRange / -2);
        
        dir[0] = (((dir[0] + 1) * newRange) / oldRange) + newMin;
        dir[1] = (((dir[1] + 1) * newRange) / oldRange) + newMin;
        
        System.out.println(dir[0] + ":" + dir[1]);
        
        /*
        if (upDownDPad == 1)
            System.out.println("1");
        else if (upDownDPad == -1)
            System.out.println("-1");
         */
       
        return dir;
    }
   
    /*
    public static double[] scaleValues(double[] values, double buttonDPadValue) {
        // values = new double[4];
        double upDownDPad = -ps2Controller.getRawAxis(6);
       
        double oldRange = 2, newRange = 0;
       
        if ((buttonDPadValue == -1) && (oldRange >= 1)) {
            newRange = oldRange - 0.2;
        } else if ((buttonDPadValue == 0))
        else if ((buttonDPadValue == 1) && (oldRange <= 2)) {
            newRange = oldRange + 0.2;
        }
       
        double newMin = (newRange / -2);
        // double oldRange = 2, newRange = 1.4, newMin = (newRange / -2);
       
        values[0] = (((values[0] + 1) * newRange) / oldRange) + newMin;
        values[1] = (((values[1] + 1) * newRange) / oldRange) + newMin;
       
        //System.out.println(values[0] + ":" + values[1]);
       
        return values;
    }
    */
   
    /*
    protected static double[] rampSpeed(double[] axis) {
        for (byte si = 0; si < axis.length; si++) {
            axis[si] = ((0.666) * MathUtils.pow(axis[si], 3)) + ((0.333) * axis[si]);
        }
       
        return axis;
    }
    */
   
    protected static double[] deadZone(double[] axis) {
        for (byte si = 0; si < axis.length; si++) {
            if ((axis[si] <= 0.05) && (axis[si] >= -0.05))
                axis[si] = 0;
        }
       
        return axis;
    }
  
    protected static class button {
        boolean buttonState, otherState, otherState2, joystickState;
        int buttonPin, lowerGrabber, upperGrabber;
       
        public button(int buttonPin, boolean joystickState) {
            this.joystickState = joystickState;
            this.buttonPin = buttonPin;
        }
       
        public boolean getState() {
            if (joystickState) {
                buttonState = ps2Controller.getRawButton(this.buttonPin);
                otherState = ps2Controller.getRawButton(this.lowerGrabber);
                otherState2 = ps2Controller.getRawButton(this.upperGrabber);
                // System.out.println(buttonState);
            }
           
            return buttonState;
        }
    }
}