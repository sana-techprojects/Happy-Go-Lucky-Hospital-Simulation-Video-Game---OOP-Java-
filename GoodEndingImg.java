import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoodEndingImg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoodEndingImg extends Actor
{
    GreenfootImage victory = new GreenfootImage("Good Ending.png");
    /**
     * Act - do whatever the GoodEndingImg wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public GoodEndingImg()
    {
        victory.scale(getImage().getWidth()/2,getImage().getHeight()/2);
        setImage(victory);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
