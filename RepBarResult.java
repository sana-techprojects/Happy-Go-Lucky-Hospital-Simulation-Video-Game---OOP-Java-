import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RepBarResult here.
 * 
 * @sana
 * @version (a version number or a date)
 */
public class RepBarResult extends Actor
{
    GreenfootImage barImg = new GreenfootImage("repResult.png");
    /**
     * Act - do whatever the RepBarResult wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public RepBarResult()
    {
        barImg.scale(280,100);
        setImage(barImg);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
