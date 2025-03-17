import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BadEndingImg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BadEndingImg extends Actor
{
    GreenfootImage failure = new GreenfootImage("Bad Ending.png");
    /**
     * Act - do whatever the BadEndingImg wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BadEndingImg()
    {
        failure.scale(520,340);
        setRotation(-10);
        setImage(failure);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
