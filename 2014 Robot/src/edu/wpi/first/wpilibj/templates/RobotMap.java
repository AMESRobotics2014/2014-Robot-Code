package edu.wpi.first.wpilibj.templates;

/**
 * This class should be used to store global variables and values. Sub-classes in this class should almost always
 * be used as a tool for structuring data and treating certain elements of the code as objects. The only methods
 * in this class should be for data management, and maybe a few for robot organization, and control, aka nothing complex
 * or related to the control of the hardware.
 * 
 * @author AliNazzal
 */
public class RobotMap {
    // Motor pins //
    protected static final int firstRightMotor = 9;
    protected static final int secondRightMotor = 10;
    
    protected static final int firstLeftMotor = 1;
    protected static final int secondLeftMotor = 8;
    
    //Placeholders
    /*protected static final int shooterLeftMotor = l;
    protected static final int shooterRightMotor = r;
    */
    protected static final int grabberMotor = 2;
    protected static final int densoMotor = 3;
    /*
    protected static final int elevatorMotor = e;
    */
    protected static final int high = 4;
    protected static final int low = 6;
}
