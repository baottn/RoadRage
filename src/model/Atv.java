/*
 * TCSS 305 - Winter 2021
 * Assignment 2 - Road Rage
 */

package model;

import java.util.Map;

/**
 * Represents an All-Terrain Vehicle (ATV).
 * 
 * @author Bao Nguyen
 * @version Winter 2021
 */
public class Atv extends AbstractVehicle {
    
    /** The death time of this Vehicle. */
    private static final int DEATH_TIME = 25;
    
    /** 
     * Initializes the instance fields.
     * 
     * @param theX the x coordinate of this Vehicle
     * @param theY the Y coordinate of this Vehicle
     * @param theDir the direction of this Vehicle
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean passSign = true;
        if (theTerrain == Terrain.WALL) {
            passSign = false;
        }
        return passSign;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction chosenDirection = Direction.random();
        while (chosenDirection == getDirection().reverse()) {
            chosenDirection = Direction.random();
        }
        return chosenDirection;
    }
    
    
}
