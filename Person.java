import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author vincent 
 * @version 1.3 (11/10/2023)
 */
public abstract class Person extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    
    /**
     * Constructor for the Person class
     */
    public Person() {
        
        enableStaticRotation();
    }
    
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Following code will not be in final version, added to test
        // features
        //setLocation(getX(), getY() + 5);
        despawn();
    }
    
    /**
     * Removes the person from the world if it goes outside the visible
     * bounderies of the world size, will allow for person will exist slightly
     * outside world to prevent possible bugs
     */
    public void despawn() {
        // Person will be deleted from world
        if(getX() < -10 || getX() > (getWorld().getWidth() + 10)||
            getY() < -10 || getY() > getWorld().getHeight()) {
            getWorld().removeObject(this);
        }
    }
    
}
