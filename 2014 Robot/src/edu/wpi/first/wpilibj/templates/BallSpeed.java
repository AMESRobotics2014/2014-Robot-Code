/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author jonah.barber
 */
public class BallSpeed {
    /**
     * 
     * @param pullDistance the distance the sling is pulled from the point of 0 resistance;
     * @return the speed that would be achieved.
     */
    double getSpeed(double pullDistance)  {
        return pullDistance*ELASTIC_CONSTANT;
    }
    /**
     * 
     * @param speed the speed requested.
     * @return the distance from the point of 0 resistance that would achieve the speed.
     */
    double getDistance(double speed)  {
        return speed/ELASTIC_CONSTANT;
    }
    /**the speed of the ball for every inch pulled.
     * 
     */
    double ELASTIC_CONSTANT=0;
}
