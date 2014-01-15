/*Class for handeling massive amounts of image processing code, this may actually end up holding very little if the Raspberry pie is used instead.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import java.lang.Math.*;

/**
 *
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
    
    // Score limits.
    
    final int AreaMinimum = 150;//minnimum of area in pixels that our camera will keep track of
    
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
    
    void Init(){
        Fcam = AxisCamera.getInstance();  // get an instance of the camera
        Crit = new CriteriaCollection();// create the criteria for the particle filter
        Crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, AreaMinimum, 65535, false);//sets limits for the objects we will keep track of. lower bound is AreaMinimum & upper bound is 65535 pixels^2
    }
    public void autonomous() {
        
    }
}

