/**
 * This class should be used to store global variables and value. Sub-classes in this class should almost always
 * be used as a tool for structuring data and treating certain elements of the code as objects. The only methods
 * in this class should be for data managment, and maybe a few for robot organization, and control, aka nothing complex
 * or related to the control of the hardware.
 */

package edu.wpi.first.wpilibj.templates;
//An example of global values that should be stored in this class are pin assignments.

public class RobotMap {

    //Controller Pins
    protected static final int rb = 8;
    protected static final int lb = 7;
    protected static final int M9 = 9;
    protected static final int M10 = 10;
    protected static final int rf = 6;
    protected static final int lf = 5;
    protected static final int expo_ramp = 3;
    protected static final int topb = 4;
    
    //Motor pins
    protected static final int lm1 = 2;
    protected static final int lm2 = 8;
    protected static final int rm1 = 1;
    protected static final int rm2 = 10;
    
    //Other constants
    protected static final double defaultlimit = .5;
    protected static final double normalThresh = .05;            
    
    
}
