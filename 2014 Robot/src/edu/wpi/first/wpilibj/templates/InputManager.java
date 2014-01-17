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
    protected static button Stop;
    protected static button UnlockR1;
    protected static button UnlockL1;
    protected static button misc9;
    protected static button misc10;
    
    void init(){
        ps2cont = new Joystick(1);
        Stop = new button(4, true);
        UnlockR1 = new button(8, true);
        UnlockL1 = new button(7,true);
        misc9 = new button(9,true);
        misc10 = new button(10, true);
        
        
        
    }
    public double[] getPureAxis() {
        double[] dir = new double[4];
        dir[0] = -ps2cont.getRawAxis(1);// Y1
        dir[1] = ps2cont.getRawAxis(4);// Y2

        dir = deadZone(dir);
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
    
   public static void UpdateButtons(){
       Stop.getState();
   }

    public static class button {

        boolean state;
        boolean laststate;
        boolean bjoystick;
        int bpin;

        public button(int pin, boolean joystick) {
            bjoystick = joystick;
            bpin = pin;
        }

        public boolean getState() {
            // true: first joystick, false: second joystick
            laststate = state;
            if (bjoystick) {
                state = ps2cont.getRawButton(this.bpin);
            } else {
                //state = monoJoystick.getRawButton(this.bpin);
            }
            return state;
        }
    }
}
