import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to the grocery store structure.
 * 
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Build2 extends Prop
{
    private SimpleTimer timer = new SimpleTimer();
    public Build2(int xSizer, int ySizer){
        super (xSizer, ySizer);
        timer.mark();
        img = new GreenfootImage("grocery-store.png");
    }
    
    public void act()
    {
        if(timer.millisElapsed() > 32000)
        {
            this.move(-10);
            if(this.getX() == 0)
            {
                move(0);
            }
        }
    }
}
