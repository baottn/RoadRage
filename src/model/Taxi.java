/*
 * TCSS 305 - Winter 2021
 * Assignment 2 - Road Rage
 */

package model;

import java.util.Map;

/**
 * Represents a Bicycle.
 * 
 * @author Bao Nguyen
 * @version Winter 2021
 */
public class Taxi extends AbstractVehicle {
    
    /** The death time of this Vehicle. */
    private static final int DEATH_TIME = 15;
    
    /** The max clock cycle number.  */
    private static final int MAX_CLOCK_CYCLE = 3;
    
    /** The clock cycle. */
    private int myClockCycle;

    /** 
     * Initializes the instance fields.
     * 
     * @param theX the x coordinate of this Vehicle
     * @param theY the Y coordinate of this Vehicle
     * @param theDir the direction of this Vehicle
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
        myClockCycle = 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean passSign = true;
        if (!isOnStreet(theTerrain) || !passableLight(theTerrain, theLight)) {
            passSign = false;
            if (theTerrain == Terrain.CROSSWALK) {
                myClockCycle++;
                if (myClockCycle > MAX_CLOCK_CYCLE) {
                    passSign = true;
                    myClockCycle = 0;
                }
            }
        }
        return passSign;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction chosenDirection = getDirection();
        if (!isOnStreet(theNeighbors.get(getDirection()))) {
            if (isOnStreet(theNeighbors.get(getDirection().left()))) {
                chosenDirection = getDirection().left();
            } else if (isOnStreet(theNeighbors.get(getDirection().right()))) {
                chosenDirection = getDirection().right();
            } else {
                chosenDirection = getDirection().reverse();
            }
        }
        return chosenDirection;
    }
    
    /**
     * Checks if the light allows this Vehicle to pass or not.
     * 
     * @param theCurrentTerrain the current terrain that this Vehicle is facing
     * @param theLight the light of the current terrain
     * @return the pass check of this Vehicle
     */
    private boolean passableLight(final Terrain theCurrentTerrain, final Light theLight) {
        boolean passCheck = true;
        if (theCurrentTerrain == Terrain.LIGHT && theLight == Light.RED
            || theCurrentTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            passCheck = false;
        }
        return passCheck;
    }
}
