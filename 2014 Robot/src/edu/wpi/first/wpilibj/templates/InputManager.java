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
