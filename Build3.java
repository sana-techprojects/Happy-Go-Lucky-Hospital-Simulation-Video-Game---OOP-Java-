import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to the store, the brown structure.
 * 
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Build3 extends Prop
{
    private SimpleTimer timer = new SimpleTimer();
    public Build3(int xSizer, int ySizer){
        super (xSizer, ySizer);
        timer.mark();
    }
    public void act()
    {
        //this.move(-5);
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
