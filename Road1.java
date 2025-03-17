import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to the first road tile.
 * 
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Road1 extends Prop
{
    public Road1(int xSizer, int ySizer){
        super (xSizer, ySizer);
        img = new GreenfootImage("concrete-road-1.png");
    }
}
