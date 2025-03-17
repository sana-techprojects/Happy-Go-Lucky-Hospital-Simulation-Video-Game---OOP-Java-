import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to the grass tile.
 * 
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Grass extends Prop
{
    public Grass(int xSizer, int ySizer){
        super (xSizer, ySizer);
        img = new GreenfootImage("dark-grass.png");
    }
}
