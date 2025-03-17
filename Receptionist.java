import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Receptionist here.
 * 
 * @author vincent
 * @version 1.1 (11/12/2023)
 */
public class Receptionist extends MedStaff
{
    private int xDirection;
    private int yDirection;
    /**
     * Act - do whatever the Receptionist wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // The recepionist can only move within the boundaries of the desk
        if(getY() <= 30 && getY() >= 10) {
            getWorld().removeObject(this);
        }
    }
    
    
}
