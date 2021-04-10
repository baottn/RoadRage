/*
 * TCSS 305 - Winter 2021
 * Assignment 2 - Road Rage
 */

package model;

import java.util.Locale;
import java.util.Map;

/**
 * Represents default behavior for Vehicle subclasses.
 * 
 * @author Bao Nguyen
 * @version Winter 2021
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /** The direction of this Vehicle. */
    private Direction myDirection;
    
    /** The initial direction of this Vehicle. */
    private final Direction myInitialDirection;
    
    /** The x coordinate of this Vehicle. */
    private int myX;
    
    /** The y coordinate of this Vehicle. */
    private int myY;
    
    /** The initial x coordinate of this Vehicle. */
    private final int myInitialX;
    
    /** The initial y coordinate of this Vehicle. */
    private final int myInitialY;
    
    /** The death time of this Vehicle. */
    private final int myDeathTime;
    
    /** The living status of this Vehicle. */
    private boolean myLivingCheck;
    
    /** The number of rounds that the Vehicle laying death. */
    private int myDeathRound;
    
    /**
     * Initialize the instance fields.
     * 
     * @param theX the x coordinate of this Vehicle
     * @param theY the y coordinate of this Vehicle
     * @param theDir the direction of this Vehicle
     * @param theDeathTime the death time of this Vehicle
     */
    protected AbstractVehicle(final int theX, 
                              final int theY, 
                              final Direction theDir,
                              final int theDeathTime) {
        myDirection = theDir;
        myInitialDirection = theDir;
        myX = theX;
        myY = theY;
        myInitialX = theX;
        myInitialY = theY;
        myDeathTime = theDeathTime;
        myLivingCheck = true;
        myDeathRound = 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return Direction.random();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (this.getDeathTime() > theOther.getDeathTime()) {
            myLivingCheck = false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getImageFileName() {
        final String name = getClass().getSimpleName().toLowerCase(Locale.US);
        final StringBuffer imageName = new StringBuffer(name);
        if (!isAlive()) {
            imageName.append("_dead");
        }
        imageName.append(".gif");
        return imageName.toString();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return myDirection;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return myX;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return myY;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        myDeathRound++;
        if (myDeathRound > myDeathTime) {
            myLivingCheck = true;
            myDeathRound = 0;
        }
        return myLivingCheck;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void poke() {
        myDirection = Direction.random();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        setDirection(myInitialDirection);
        setX(myInitialX);
        setY(myInitialY);
        myLivingCheck = true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final int theX) {
        myX = theX;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    
    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName().toLowerCase(Locale.US);
        
    }
    
    /**
     * Checks if the terrain is on the street or not.
     * 
     * @param theCurrentTerrain the current terrain that this Vehicle is facing
     * @return the pass check of this Vehicle
     */
    protected boolean isOnStreet(final Terrain theCurrentTerrain) {
        boolean passCheck = false;
        if (theCurrentTerrain == Terrain.STREET 
            || theCurrentTerrain == Terrain.LIGHT 
            || theCurrentTerrain == Terrain.CROSSWALK) {
            passCheck = true;
        }
        return passCheck;
    }
}
