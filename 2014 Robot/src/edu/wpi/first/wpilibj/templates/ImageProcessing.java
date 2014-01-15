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
    /** 
     * The view angle and image resolution need to be in compliance with the camera.
     */
    public final int Y_IMAGE_RES = 480; // Need to check if this is resolution on camera.
    public final double VIEW_ANGLE = 37.4; // For the M1011 Axis Camera - need to check model of camera.
    public final double PI = Math.PI;//This is Pi, pretty self explanatory

    //Score limits used for target identification
    final int  RECTANGULARITY_LIMIT = 40;
    final int ASPECT_RATIO_LIMIT = 55;

    //Score limits used for hot target determination
    final int TAPE_WIDTH_LIMIT = 50;
    final int  VERTICAL_SCORE_LIMIT = 50;
    final int LR_SCORE_LIMIT = 50;

    //Minimum area of particles to be considered
    final int AREA_MINIMUM = 150;

    //Maximum number of particles to process
    final int MAX_PARTICLES = 8;
    
    public class Targetreport {
		int verticalLocation;
		int horizontalLocation;
		boolean Hot;
		double totalScore;
		double leftScore;
		double rightScore;
		double tapeWidthScore;
		double verticalScore;
    };
    
    public class Scores {
        double rectangularity;
        double aspectRatioVertical;
        double aspectRatioHorizontal;
    }
    
    void Init(){
        Fcam = AxisCamera.getInstance();  // get an instance of the camera
        Crit = new CriteriaCollection();// create the criteria for the particle filter
        Crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, AREA_MINIMUM, 65535, false);//sets limits for the objects we will keep track of. lower bound is AreaMinimum & upper bound is 65535 pixels^2
    }
    public void autonomous() {
        
    }
}

