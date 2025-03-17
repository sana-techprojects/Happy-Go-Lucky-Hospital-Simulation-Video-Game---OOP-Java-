import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to the sidewalk tile.
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Sidewalk extends Prop
{
    /**
     * Act - do whatever the Sidewalk wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Sidewalk(int xSizer, int ySizer){
        super (xSizer, ySizer);
        img = new GreenfootImage("sidewalk-1.png");
    }
}
