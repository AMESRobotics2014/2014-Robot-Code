package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import java.lang.Math.*;

/**
 * Class for handling massive amounts of image processing code. This may actually end up holding very little if the Raspberry pie is used instead.
 * @author AliNazzal, Mark, & TARUN
 */


public class ImageProcessing {
    AxisCamera Fcam;//this is the axis camera we will use
    CriteriaCollection Crit;//our collection of criterias 
    /** Vertical image resolution - needs to be in compliance w/ camera. */
    public final int Y_IMAGE_RES = 480; // Need to check if this is resolution on camera.
    /** Viewing angle - needs to be in compliance w/ camera. */
    public final double VIEW_ANGLE = 37.4; // For the M1011 Axis Camera - need to check model of camera.
    /** Pi (~3.14), as defined in the Math class. */
    public final double PI = Math.PI;
    
    // Score limits.
    /** Minimum area the camera keeps track of, in pixels. */
    final int AreaMinimum = 150;
    /** Records and reports information about the target tape. */
    public class Targetreport {
                /** Vertical Location of Tape */
		int verticalLocation;
                /** Horizontal Location of Tape */
		int horizontalLocation;
                /** Is the associated goal hot?  */
		boolean Hot;
		double totalScore;
		double leftScore;
		double rightScore;
		double tapeWidthScore;
		double verticalScore;
    };
    /** Initialize camera-related variables. */
    void Init(){
        Fcam = AxisCamera.getInstance();  // get an instance of the camera
        Crit = new CriteriaCollection();// create the criteria for the particle filter
        Crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, AreaMinimum, 65535, false);//sets limits for the objects we will keep track of. lower bound is AreaMinimum & upper bound is 65535 pixels^2
    }
    public void autonomous() {
        
    }
}

