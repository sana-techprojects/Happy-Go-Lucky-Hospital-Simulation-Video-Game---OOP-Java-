import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A subclass of "Prop" that has their image set to a modified variant of the sidewalk tile. This modifed variant is used for activating special methods in other classes.
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class SidewalkB extends Prop
{
    //An object of GreenfootImage that is used for SidewalkB's alternate appearance.
    GreenfootImage roadImage;
    /**
     * Act - do whatever the SidewalkB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SidewalkB(int xSizer, int ySizer){
        super (xSizer, ySizer);
        roadImage = new GreenfootImage("concrete-road-1.png");
    }
    
    //Allows the "changeImage()" method to activate.
    public void act()
    {
        changeImage();
    }
    
    //Causes SidewalkB to change its image to be that of the road once its y value is greater than or equal to 400.
    public void changeImage(){
        if(getY() >= 400){
            setImage(roadImage);
        }
    }
}
