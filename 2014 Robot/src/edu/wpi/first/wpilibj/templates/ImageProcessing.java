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
    /** this is the axis camera we will use */
    AxisCamera Fcam;
    /** our collection of criteria */
    CriteriaCollection Crit;
    /** Vertical image resolution - needs to be in compliance w/ camera. */
    public final int Y_IMAGE_RES = 480; // Need to check if this is resolution on camera.
    /** Viewing angle - needs to be in compliance w/ camera. */
    public final double VIEW_ANGLE = 37.4; // For the M1011 Axis Camera - need to check model of camera.
    /** Pi (~3.14), as defined in the Math class. */
    public final double PI = Math.PI; 
    /** Minimum area the camera keeps track of, in pixels. */
    final int AreaMinimum = 150;


    //Score limits used for target identification
    /** Rectangularity goal limit. Helps identify target. */
    final int  RECTANGULARITY_LIMIT = 40;
    /** Aspect ratio limit. Helps identify target */
    final int ASPECT_RATIO_LIMIT = 55;

    //Score limits used for hot target determination
    /** Limit for tape width */
    final int TAPE_WIDTH_LIMIT = 50;
    /** Vertical goal limit */
    final int  VERTICAL_SCORE_LIMIT = 50;
    /** Lower goal limit */
    final int LR_SCORE_LIMIT = 50;

    /** Minimum area of particles to be considered */
    final int AREA_MINIMUM = 150;

    //* Maximum number of particles to process */
    final int MAX_PARTICLES = 8;
    
    /** Records and reports information about the target tape. */
    public class Targetreport {
                /** Vertical Location of Tape */
		int verticalLocation;
                /** Horizontal Location of Tape */
		int horizontalLocation;
                /** Is the associated goal hot?  */
		boolean Hot;
                /** Total width of goals */
		double totalScore;
                /** width of left goal */
		double leftScore;
                /** width of right goal */
		double rightScore;
                /** width of goal tape */
		double tapeWidthScore;
                /** vertical length of goal */
		double verticalScore;
    };
    
    /** Stores information as limited in the main class definition */
    public class Scores {
        /** Rectangularity of goal */
        double rectangularity;
        /** Vertical component of goal's aspect ratio */
        double aspectRatioVertical;
        /** Horizontal component of goal's aspect ratio */
        double aspectRatioHorizontal;
    }
    /** Initialize camera-related variables. */
    void Init(){
        Fcam = AxisCamera.getInstance();  // get an instance of the camera
        Crit = new CriteriaCollection();// create the criteria for the particle filter
        Crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, AREA_MINIMUM, 65535, false);//sets limits for the objects we will keep track of. lower bound is AreaMinimum & upper bound is 65535 pixels^2
    }
    /** This is called periodically during autonomous mode */
    public void autonomous() {
        
    }
}

