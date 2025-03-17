import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An abstract class that contains all the tiles and structures used in the simulation.
 * 
 * <p> Made By: Harithan Raveendran
 * <p> Borrowed code from: Danpost
 * <p> Last Modified: Nov 10, 2023 
 */
public class Prop extends Actor
{
    //The integers that affect the width and height of all the tiles and structures.
    protected int ySizer;
    protected int xSizer;
    //An object of the class GreenfootImage. This sets the image that determines the appearance of every subclass.
    GreenfootImage img;
    /**
     * @param xSizer modifies the width of the object.
     * @param ySizer modifies the height of the object.
     */
    public Prop(int xSizer, int ySizer){
        //Allows the "xSizer" and "ySizer" integers to be modified through a constructor.
        this.xSizer = xSizer;
        this.ySizer = ySizer;
        //Defines the "img" object so that the subclasses can have their own values of it.
        GreenfootImage img = getImage();
        //The line of code that allows the sizer integers to work.
        img.scale(img.getWidth()*(10+(++xSizer))/60, img.getHeight()*(10+ySizer)/60);
        //Sets the image of the prop/structure.
        setImage(img);
    }
}
