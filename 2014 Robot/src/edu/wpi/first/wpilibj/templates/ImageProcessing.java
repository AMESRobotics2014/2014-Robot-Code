package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import java.lang.Math.*;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
/**
 * Class for handling massive amounts of image processing code. This may actually end up holding very little if the Raspberry pie is used instead.
 * @author AliNazzal, Mark, & TARUN
 */


public class ImageProcessing {
    /** this is the axis camera we will use */
    AxisCamera camera;
    /** our collection of criteria */
    CriteriaCollection Crit;
    /** Vertical image resolution - needs to be in compliance w/ camera. */
    public final int Y_IMAGE_RES = 480; // Need to check if this is resolution on camera.
    /** Viewing angle - needs to be in compliance w/ camera. */
    public final double VIEW_ANGLE = 37.4; // For the M1011 Axis Camera - need to check model of camera.
    public final double PI = Math.PI;//This is Pi, pretty self explanatory
        //Score limits used for target identification
    final int  RECTANGULARITY_LIMIT = 40;
    final int ASPECT_RATIO_LIMIT = 559;

    //Score limits used for hot target determination
    final int TAPE_WIDTH_LIMIT = 50;
    final int  VERTICAL_SCORE_LIMIT = 50;
    final int LR_SCORE_LIMIT = 50;

    //Minimum area of particles to be considered
    final int AREA_MINIMUM = 150;

    //Maximum number of particles to process
    final int MAX_PARTICLES = 8;

    
    /** Records and reports information about the target tape. */
    public class Targetreport {
                int verticalIndex;
                /** Vertical Location of Tape relative to the robot*/
		int verticalLocation;
                /** Horizontal Location of Tape relative to the robot*/
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
        camera = AxisCamera.getInstance();  // get an instance of the camera
        Crit = new CriteriaCollection();// create the criteria for the particle filter
        Crit.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, AREA_MINIMUM, 65535, false);//sets limits for the objects we will keep track of. lower bound is AreaMinimum & upper bound is 65535 pixels^2
    }
    /** This is called periodically during autonomous mode */
    public void autonomous() throws AxisCameraException {
        Targetreport target = new Targetreport();//make a target and have all the variables in our targetreport class did
	int verticalTargets[] = new int[MAX_PARTICLES];//make an array called verticaltargets and store 8 elements
	int horizontalTargets[] = new int[MAX_PARTICLES];//make an array called horizontaltargets and store 8 elements
	int verticalTargetCount, horizontalTargetCount;
        
        //while (isAutonomous() && isEnabled()) {
            try{
                ColorImage image = camera.getImage();
                 BinaryImage thresholdImage = image.thresholdHSV(105, 137, 230, 255, 133, 183);   // keep only green objects
                //thresholdImage.write("/threshold.bmp");
                BinaryImage filteredImage = thresholdImage.particleFilter(Crit);           // filter out small particles
                //filteredImage.write("/filteredImage.bmp");
               Scores scores[] = new Scores[filteredImage.getNumberParticles()];//goes through each particle and scores to see if it IS a target
                horizontalTargetCount = verticalTargetCount = 0;//setting up how many targets we have initially
                if(filteredImage.getNumberParticles()>0)//If there are any particles detected
                {
                    for(int i=0; i<MAX_PARTICLES && i<filteredImage.getNumberParticles();i++)//start with an variable which starts at 0, goes up until 8 or lower,depending on particles detected, and increment by one every time
                    {
                        ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);//Goes through particles.
                        scores[i] = new Scores();
                        
                        //Score each particle on rectangularity and aspect ratio.
                        // scores[i].rectangularity = scoreRectangularity(report);//check for it's rectangularity with a function we will use later
                        // scores[i].aspectRatioVertical = scoreAspectRatio(filteredImage, report, i, true);//check for it's aspect ratio vertical with a function we will use later
                        // scores[i].aspectRatioHorizontal = scoreASpectRatio(filteredImage, report, i, true);//check for it's horizontal with a function we will use later
                        
                        //Check if the particle is a horizontal target, if not, check if it's a vertical target.
                        /*
                        if(scoreCompare(scores[i],false))
                        {
                            System.out.println("particle: " + i + " is a Horizontal Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);//shows center values of x & y for our particle i
                            horizontalTargets[horizontalTargetCount++] = i; //Adds particle to target array and increment count
                        }
                        else if(scoreCompare(scores[i],true)) {
                              System.out.println("particle: " + i + " is a Vertical Target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);//shows center values of x & y for our particle i
                               verticalTargets[verticalTargetCount++] = i;  //Add particle to target array and increment count
                              
                        } else {
                            System.out.println("particle: " + i + "is not a target centerX: " + report.center_mass_x + "centerY: " + report.center_mass_y);//Tells us it is not a target.
                        }
                        */
                        System.out.println("rect: " + scores[i].rectangularity + "ARHoriz: " + scores[i].aspectRatioHorizontal);
                        System.out.println("ARVert: " + scores[i].aspectRatioVertical);
                    }
                    //Zero out scores and set verticalIndex to first target in case there are no horizontal targets
                    target.totalScore = target.leftScore = target.rightScore = target.tapeWidthScore = target.verticalScore = 0;
                    target.verticalIndex = verticalTargets[0];
                }
                for (int i = 0; i < verticalTargetCount; i++)//takes in account for every vertica targets found
			{
                            ParticleAnalysisReport verticalReport = filteredImage.getParticleAnalysisReport(verticalTargets[i]);//creates a particle analysis report of verticl target
                            for(int j=0;j<horizontalTargetCount; j++)//takes in sccount for all horizontal targets found
                            {
                                
                            }
                
                
                
                
                        }
            } 
            
            
            
            
            
            
            catch (NIVisionException ex) {
            ex.printStackTrace();
        } 
    //}
}
    }
        
          
