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
    protected static final int firstRightMotor = 6;
    protected static final int firstLeftMotor = 5;
    protected static final int shooterMotor2 = 10;
    protected static final int PullbackLimit = 7;//=================== +
    protected static final int GrabberLiftLimit = 9;//================ +
    protected static final int ratchet = 3;
    protected static final int RachetLimit = 15;//=====================
    protected static final int clutch = 8;
    protected static final int ClutchEngaged = 11;//=====================
    protected static final int ClutchReleased = 13;//=====================
    protected static final int grabberMotor = 1;
    protected static final int densoMotor = 7;
    protected static final int elevatorMotor = 2;
    protected static final int TopElevator = 1;//==================== +
    protected static final int LowerElevator = 3;//============== +
    protected static final int ElevatronPosition = 5;//========= +
    protected static final int high = 4;
    protected static final int PotenPin = 1;
    
    
    protected static final int expo_ramp = 3;
    protected static final double normalThresh = .05;
    
    
/*
  ___  ___ ___ _   _  ___ 
 |   \| __| _ ) | | |/ __|
 | |) | _|| _ \ |_| | (_ |
 |___/|___|___/\___/ \___|
*/
    protected static final boolean verbose = false;
    protected static final boolean stdDEBUG = false;//Standard debug
    protected static final boolean manualONLY = true;
    protected static final double printdelay = 3;
    
}
