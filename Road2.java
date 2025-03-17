import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to the second road tile.
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Road2 extends Prop
{
    private SimpleTimer timer = new SimpleTimer();
    public Road2(int xSizer, int ySizer){
        super (xSizer, ySizer);
        timer.mark();
        img = new GreenfootImage("concrete-road-2.png");
    }
    
    public void act()
    {
        //this.move(-5);
        if(timer.millisElapsed() > 10000)
        {
            if(this.getX() == 0)
            {
                move(0);
            }
            else
            {
                //this.move(-10);
            }
        }
    }
}
