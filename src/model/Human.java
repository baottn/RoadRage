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
public class Human extends AbstractVehicle {
    
    /** The death time of this Vehicle. */
    private static final int DEATH_TIME = 45;
    
    /** 
     * Initializes the instance fields.
     * 
     * @param theX the x coordinate of this Vehicle
     * @param theY the Y coordinate of this Vehicle
     * @param theDir the direction of this Vehicle
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction chosenDirection = getDirection();
        if ((isWalkable(theNeighbors.get(getDirection().left()))
            || isWalkable(theNeighbors.get(getDirection().right())))
            && theNeighbors.get(getDirection()) != Terrain.CROSSWALK) {
            chosenDirection = generatedDirection(theNeighbors, chosenDirection);
        }
        
        if (!isWalkable(theNeighbors.get(chosenDirection)) 
            && !isWalkable(theNeighbors.get(getDirection().left()))
            && !isWalkable(theNeighbors.get(getDirection().right()))) {
            chosenDirection = getDirection().reverse();
        }
        
        if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            chosenDirection = getDirection().right();
        }
        
        if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            chosenDirection = getDirection().left();
        }
        return chosenDirection;
    }
    
   /**
    * {@inheritDoc}
    */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean passSign = false;
        if (isWalkable(theTerrain) && passableLight(theTerrain, theLight)) {
            passSign = true;
        }
        return passSign;
    }
    
    /**
     * Checks if the terrain is pedestrian friendly or not.
     * 
     * @param theCurrentTerrain the current terrain that this Vehicle is facing
     * @return the pass check of this Vehicle
     */
    private boolean isWalkable(final Terrain theCurrentTerrain) {
        boolean passCheck = false;
        if (theCurrentTerrain == Terrain.GRASS || theCurrentTerrain == Terrain.CROSSWALK) {
            passCheck = true;
        }
        return passCheck;
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
        if (theCurrentTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            passCheck = false;
        }
        return passCheck;
    }
    
    private Direction generatedDirection(final Map<Direction, Terrain> theNeighbors, 
                                         final Direction theDirection) {
        Direction directionForward = theDirection;
        directionForward = Direction.random();
        while (!isWalkable(theNeighbors.get(directionForward)) 
                || directionForward == getDirection().reverse()) {
            directionForward = Direction.random();
        }
        return directionForward;
    }
}
