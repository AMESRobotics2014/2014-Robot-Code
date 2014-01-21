/*
 */

//Blarg

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
    protected static button buttonStop;
    
    public void init() {
        ps2Controller = new Joystick(1);
        buttonStop = new button(4, true);
    }
    
    public static double[] getPureAxis() {
        double[] dir = new double[2];
        dir[0] = -ps2Controller.getRawAxis(1);
        dir[1] = ps2Controller.getRawAxis(2);
        
        dir = deadZone(dir);
        
        // Might need it - we'll see.
        // dir = translate(dir);
        
        return dir;
    }
    
    protected static double[] rampSpeed(double[] axis) {
        for (byte si = 0; si < axis.length; si++) {
            axis[si] = ((0.666) * MathUtils.pow(axis[si], 3)) + ((0.333) * axis[si]);
        }
        
        return axis;
    }
    
    protected static double[] deadZone(double[] axis) {
        for (byte si = 0; si < axis.length; si++) {
            if ((axis[si] <= 0.05) && (axis[si] >= -0.05))
                axis[si] = 0;
        }
        
        return axis;
    }
   
    protected static class button {
        boolean buttonState, joystickState;
        int buttonPin;
        
        public button(int buttonPin, boolean joystickState) {
            this.joystickState = joystickState;
            this.buttonPin = buttonPin;
        }
        
        public boolean getState() {
            if (joystickState)
                buttonState = ps2Controller.getRawButton(this.buttonPin);
            
            return buttonState;
        }
    }
}
