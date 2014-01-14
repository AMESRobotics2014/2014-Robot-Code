/*Class for handeling massive amounts of image processing code, this may actually end up holding very little if the Raspberry pie is used instead.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import java.lang.Math.*;

/**
 *
 * @author AliNazzal, Mark, & TARUN
 */


public class ImageProcessing {
   
    AxisCamera Fcam;
    /** 
     * The view angle and image resolution need to be in compliance with the camera.
     */
    public final int Y_IMAGE_RES = 480; // Need to check if this is resolution on camera.
    public final double VIEW_ANGLE = 37.4; // For the M1011 Axis Camera - need to check model of camera.
    public final double PI = Math.PI;
    
    // Score limits.
    
    
    void Init(){
        
    }
    
}
