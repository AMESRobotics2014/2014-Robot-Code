/*This class should hold all code, classes and methods for managing all inputs into the system, this includes buttons, joysticks, and other
 * user interface devices. The class should handle, and manipulate these inputs into data to be sent to other parts of the robot. In most
 * cases this class should only manage and create instructions for hardware, these instructions should be sent elswhere before being fed to hardware.
 * For example, this class should get input from the joysticks, organize it, prepare it, but never call on an actuall motor to do any action. Instead
 * send it to a motor controlling class that will take that data and use it appropriately
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

public class InputManager {

    protected static Joystick ps2cont;
    protected static button OhDearGodStop;
    
    void init(){
        ps2cont = new Joystick(1);
        OhDearGodStop = new button(1, true);
        
    }
    public double[] getPureAxis() { // Gets, stores, and returns the status of the joysticks on the PS2 Controller
        /* We will use a double dimension arry to hold the joystick data so that everything can be sent to other functions.
         * Both of the first dimensions will hold 2 doulbes, the first is the x & y axis of the first (paning) joystick
         * The second dimension holds the x & y for the second (pivoting) joystick
         */
        // double[] axis = new double[2];// Variable for storing all that data
        double[] dir = new double[4];
        dir[0] = -ps2cont.getRawAxis(1);// X
        dir[1] = ps2cont.getRawAxis(3);// Y
        //dir[2] = monoJoystick.getRawAxis(1);// X
        //dir[3] = monoJoystick.getRawAxis(2);// Y

        dir = deadZone(dir);
        //dir = ramp(dir);
        return (dir); // Returns axis data to the caller.
    }
    
    protected static double[] deadZone(double[] axis) {// Checks for deadzone
        //This is a skeleton of the deadzone funtion. Mark should fill this in.

        for (byte si = 0; si < axis.length; si++) {//loops through the array.
            if (axis[si] <= .05 && axis[si] >= -.05) {
                axis[si] = 0;
            }
        }
        return (axis);
    }

    public static class button {

        boolean state;
        boolean bjoystick;
        int bpin;

        public button(int pin, boolean joystick) {
            bjoystick = joystick;
            bpin = pin;
        }

        public boolean getState() {
            // true: first joystick, false: second joystick
            if (bjoystick) {
                state = ps2cont.getRawButton(this.bpin);
            } else {
                //state = monoJoystick.getRawButton(this.bpin);
            }
            return state;
        }
    }
}
