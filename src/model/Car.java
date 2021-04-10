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
public class Car extends AbstractVehicle {
    
    /** The death time of this Vehicle. */
    private static final int DEATH_TIME = 15;
    
    /** 
     * Initializes the instance fields.
     * 
     * @param theX the x coordinate of this Vehicle
     * @param theY the Y coordinate of this Vehicle
     * @param theDir the direction of this Vehicle
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean passSign = true;
        if (!isOnStreet(theTerrain) || !passableLight(theTerrain, theLight)) {
            passSign = false;
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
        if (theCurrentTerrain == Terrain.CROSSWALK) {
            if (theLight == Light.RED || theLight == Light.YELLOW) {
                passCheck = false;
            }
        } else if (theCurrentTerrain == Terrain.LIGHT && theLight == Light.RED) {
            passCheck = false;
        }
        return passCheck;
    }
}
