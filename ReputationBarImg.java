import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ReputationBar here.
 * 
 * @sana
 * @version (a version number or a date)
 */
public class ReputationBarImg extends Actor
{
    GreenfootImage repBar = new GreenfootImage("ReputationBar.png");
    /**
     * Act - do whatever the ReputationBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ReputationBarImg()
    {
        repBar.scale(100,300);
        setImage(repBar);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
